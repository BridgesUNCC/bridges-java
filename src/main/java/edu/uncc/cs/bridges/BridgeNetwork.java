package edu.uncc.cs.bridges;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.*;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class BridgeNetwork {
    String server_url = "http://edu.uncc.cs.bridges-cs.herokuapp.com";
    Executor http_connection;
    boolean debug = false;
    
    public BridgeNetwork() {
    	http_connection = Executor.newInstance(
    			HttpClientBuilder
    					.create()
    					.setRedirectStrategy(new LaxRedirectStrategy())
    					.build()
    		    );
    }
    
    /* Accessors and Mutators */
    
    /**
     * Get the current  base URL for the Bridges server (with no ending /)
     * @return
     */
	public String getServerURL() {
		return server_url;
	}

	/**
	 * Set the current base URL for the Bridges server (with no ending /)
	 * @param server_url
	 */
	public void setServerURL(String server_url) {
		while (server_url.endsWith("/"))
			server_url = server_url.substring(0, server_url.length()-1);
		if (server_url.length() > 0)
			this.server_url = server_url;
	}
	
	/* JSON management */
	
	/**
	 * Decode a String containing JSON into a JSON Object, or throw an error.
	 * @param text  The input JSON text
	 * @return  A non-null JSON object
	 */
    public JSONObject asJSONObject(String text) throws IOException {
    	JSONObject jo;
    	try {
    		jo = (JSONObject) JSONValue.parse(text);
    	} catch (ClassCastException e) {
    		throw new IOException("Received a malformed JSON response from the"
    				+ " server (expecting a JSON object): " + text);
    	}
    	if (jo == null) {
    		throw new IOException("Received an empty JSON response from the"
    				+ " server. ");
    	}
        if (jo.containsKey("error")) {
        	throw new IOException("Server reported an error while serving"
        			+ " request: " + jo.get("error"));
        }
        return jo;
    }
    
    /**
     * Decode a String containing JSON into a JSON Array, or throw an error.
     * @param text  The JSON Array as a string
     * @return a non-null JSONArray
     */
    public JSONArray asJSONArray(String text) throws IOException {
    	JSONArray ja;
    	try {
    		ja = (JSONArray) JSONValue.parse(text);
    	} catch (ClassCastException e) {
    		// We didn't get an array.
    		// If it is an object-with-error, then asJSON will error:
    		asJSONObject(text);
    		// asJSON didn't error so we just received the wrong type.
    		throw new IOException("Received a malformed JSON response from the"
    				+ " server. We were expecting a JSON array, but received"
    				+ " a JSON object: " + text);
    	}
    	if (ja == null) {
    		throw new IOException("Received an empty JSON response from the"
    				+ " server: " + text);
    	}
    	return ja;
    }
    
    /**
     * Form and throw a helpful error report as part of JSON traversal.
     * @param sequence   The traversal we wanted to make
     * @param cursor     Index into `sequence`, how far in the traversal we are
     * @param any_json   Any JSON object
     * @param report     Statement of what went wrong.
     * @throws IOException
     */
    private void safeJSONTraverseErrorReport(
    		String sequence,
    		int cursor,
    		Object original_obj,
    		String report
    		) throws IOException {
    	// This is not an array. Try to spit out a helpful error message.
    	System.err.println("Received a malformed JSON response. An "
    			+ "IOException follows. The error was near here: ");
    	System.err.println(sequence);
    	for (int i=0; i<cursor; i++)
    		System.err.print(" ");
    	System.err.println("^");
    	System.err.println("It occured while processing the following JSON:");
    	System.err.println(original_obj);
		throw new IOException(report);
    }
    
    /**
     * Traverse JSON in a type-safe manner.
     * This is somewhat complicated, but comes with the advantage that the
     * debugging reports are far clearer when you know the whole path you are
     * searching for.
     * 
     * Use this syntax:
     * [ number ] to access an array element,
     * ["quoted string"] to get an object attribute
     * 
     * @param sequence
     * @param o
     */
    public Object safeJSONTraverse(
    		String sequence,
    		Object original,
    		Class<?> target) throws IOException {
    	// Make sure the syntax is right
    	if (!sequence.matches("(\\[(\\d+|'[^']+')\\])*")) {
    		throw new IOException("Could not understand JSON traversal \""
    				+ sequence + "\". Please file a bug report with this"
					+ " error to the Bridges developers.");
    	}
    	// Make sure the input is not null
    	if (original == null)
    		safeJSONTraverseErrorReport(sequence, 0, original, 
    				"Input JSON is null.");
    	
    	// The cursor is where we are in `sequence`
    	int cursor = 0;
    	// This holds the object we will be running the next operation on.
    	Object any_json = original;
    	
    	// Parse `sequence`
    	Pattern array_index_p = Pattern.compile("\\[(\\d+)\\]");
		Matcher array_index_m = array_index_p.matcher(sequence.substring(cursor));
		Pattern object_p = Pattern.compile("\\['([^']+)'\\]");
		Matcher object_m = object_p.matcher(sequence.substring(cursor));
		
		// startsWith()..
		while (cursor < sequence.length()) {
	    	if (array_index_m.find() && array_index_m.start() == 0) {
	    		// Get an array index
	    		int index = Integer.parseInt(array_index_m.group(1));
	    		JSONArray ja = null;
	    		try {
	        		ja = (JSONArray) any_json;
	        	} catch (ClassCastException e) {
	        		// This is not an array.
	        		safeJSONTraverseErrorReport(sequence, cursor, original, 
	        				"Cannot access array element: not an array.");
	        	}
	    		if (ja == null) {
	    			// This array is null
	    			safeJSONTraverseErrorReport(sequence, cursor, original, 
	        				"Cannot access array element: array is null.");
	    		} else {
		    		try { 
		    			any_json = ja.get(index);
		    		} catch (IndexOutOfBoundsException e) {
		    			// This array does not have enough elements.
		        		safeJSONTraverseErrorReport(sequence, cursor, original, 
		        				"Cannot get element " + index + " from array.");
		    		}
	    		}
	    		cursor += array_index_m.end();
	    	} else if (object_m.find() && object_m.start() == 0) {
	    		// Get an object attribute
	    		String attribute_name = object_m.group(1);
	    		JSONObject jo = null;
	    		try {
	        		jo = (JSONObject) any_json;
	        	} catch (ClassCastException e) {
	        		// This is not an object.
	        		safeJSONTraverseErrorReport(sequence, cursor, original, 
	        				"Cannot get an object attribute: not an object.");
	        	}
	    		if (jo == null) {
	    			// This object is null
	    			safeJSONTraverseErrorReport(sequence, cursor, original, 
	        				"Cannot access object attribute: "
	        				+ "object is null.");
	    		} else {
	    			any_json = jo.get(attribute_name);
		    		if (any_json == null) {
		    			// The object doesn't have this attribute.
		        		safeJSONTraverseErrorReport(sequence, cursor, original, 
		        				"Cannot get attribute "+ attribute_name +
		        				" from object");
		    		}
	    		}
	    		cursor += object_m.end();
	    	} else {
	    		throw new RuntimeException("BUG: Malformed traversal " + 
	    				sequence + " passed regex.");
	    	}
		}
		if (any_json == null) {
			safeJSONTraverseErrorReport(
					sequence,
					sequence.length()-1,
					original,
					"Traversal returned null.");
		}
		if (any_json.getClass().equals(target)) {
			return any_json;
		} else {
			safeJSONTraverseErrorReport(
					sequence,
					sequence.length()-1,
					original,
					"Traversal expected a " + target.getSimpleName() +
					" but received a " + any_json.getClass().getSimpleName());
			// Impossible return to make javac happy:
			return null;
		}	
    }
    
    /**
     * Execute an Apache Fluent Request.
     * Decorates HTTP error tracebacks with urls and server {"error": "..."}
     * responses.
     * Throws an IOException with URL if the server returns an empty response.
     * Returns server response if the status code is >= 400
     *     but can still throw other exceptions if JSON parsing fails when
     *     formatting server JSON response
     * @param request
     * @return the requested string from the server
     * @throws ClientProtocolException
     * @throws IOException
     * @throws RateLimitException
     */
    public String executeHTTPRequest(Request request)
    		throws ClientProtocolException, IOException, RateLimitException {
        // It's possible we need to reimplement this as a ResponseHandler
        
        // Execute the HTTP request
        HttpResponse response;
        if (debug)
        	System.out.println("Sending request: " + request);
        try {
            response = http_connection.execute(request).returnResponse();
        } catch (HttpResponseException e) {
            // Something happened during the request that we can't handle
            System.out.println("Connection error encountered in processing '"
            		+ request + "\n");
            throw e;
        }
            
        /* This is somewhat complicated for getting a string, but:
         *   ...execute(request).returnContent().asString()
         *   	won't give error codes
         *   (String) ...execute(request).returnResponse().getEntity()
         *   	won't cast
         */
        String text = EntityUtils.toString(response.getEntity());
        
        if (response.getStatusLine().getStatusCode() == 503) {
        	throw new RateLimitException("Server responds Service Temporarily"
        			+ " Unavailable. You have probably reached your quota."
        			+ " Try again after waiting at least 15 minutes.");
        } else if (response.getStatusLine().getStatusCode() >= 400) {
        	// The request succeeded but the server threw an error
            System.err.println("Server returned error response: HTTP " +
            		response.getStatusLine().getStatusCode() + " while"
    				+ " processing the request " + request);
          
            /* By convention, the server responds {"error": "message"} */
            
            // Parsing it as an object will throw if the server gave an error.
            asJSONObject(text);
            
            // But otherwise, throw something less helpful.
            throw new IOException("Server errored, but gave an invalid"
            		+ " report: " + text + ". Consider filing a bug report"
    				+ " about this at http://github.com/SeanTater/edu.uncc.cs.bridges.");
        }
        
        // Handle empty responses
        if (text == null || text.isEmpty())
            throw new IOException("Server returned empty response for '"
        			+ request + "'");
        return text;
    }
    
    /** Execute a simple GET request relative to the server root.
        Omit the leading http://hostname, but include the leading /:
        [good]: /api/followgraph/user/sean
        [bad]: api/followgraph/user/sean
        [bad]: http://myserver:9183/api/followgraph/user/sean  */
    public String get(String url) throws RateLimitException, IOException {
        return executeHTTPRequest(Request.Get(prepare(url)));
    }
    
    /** Execute a simple POST request with relative paths, taking a Scala Map()
        of request parameters. */
    public void post(String url, Map<String, String> arguments)
    		throws IOException, RateLimitException {
        Request req = Request.Post(prepare(url));
        Form form = Form.form();
        for (Entry<String, String> e : Bridge.sorted_entries(arguments)) {
        	form.add(e.getKey(), e.getValue());
        }
        executeHTTPRequest(req.bodyForm(form.build()));
    }
    
    public void post(String url, String data)
    		throws IOException, RateLimitException {
        executeHTTPRequest(Request.Post(prepare(url))
        		.bodyString(data, ContentType.TEXT_PLAIN));
    }
    
    /**
     * Escape the URL and prepend the base URL.
     * @returns the new url as a String
     */
    public String prepare(String url) {
    	String out = server_url;
    	out += url
    			.replace(" ", "%20")
      			.replace("$assignment", Integer.toString(
      					Bridge.getAssignment()));
    	out += "?apikey=" + Bridge.getKey();
      	return out;
    }    
}
