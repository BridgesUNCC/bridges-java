package edu.uncc.cs.bridges
import org.apache.http.client.fluent
import org.json.simple._
import java.io.IOException

abstract class AnyConnectable() {
    val base: String = "http://localhost:3000"
    val http_connection: fluent.Executor
    val assignment: Int
    
    /** Convert text into a guaranteed non-null JSON format.
        Throws IOException if the result was null
        (meaning that the JSON was empty or malformed) */
    def json(text: String)= {
        Option(JSONValue.parse(text).asInstanceOf[JSONObject]).getOrElse(
            throw new IOException("Received empty JSON response")
        )
    }
    
    /** Execute an Apache Fluent Request.
        Decorates HTTP error tracebacks with urls and server {"error": "..."}
        responses.
        Throws an IOException with URL if the server returns an empty response.
        Returns server response if the status code is >= 400
            but can still throw other exceptions if JSON parsing fails when
            formatting server JSON response
        */
    def http(request: fluent.Request)= {
        // It's possible we need to reimplement this as a ResponseHandler
        
        // Execute the HTTP request
        val response = try {
            http_connection.execute(request).returnResponse()
        } catch {
            // Something happened during the request that we can't handle
            case e: org.apache.http.client.HttpResponseException => {
                println(s"Connection error encountered in processing '$request'\n")
            }
            throw e
        }
            
        /* This is somewhat complicated for getting a string, but:
         *   ...execute(request).returnContent().asString() won't give error codes
         *   ...execute(request).returnResponse().getEntity().asInstanceOf[Stream] won't cast
         */
        var text = io.Source.fromInputStream(
            response.getEntity().getContent()
        ).mkString
        
        // The request succeeded but the server threw an error
        if (response.getStatusLine().getStatusCode() >= 400) {
            println(s"HTTP error encountered in processing '$request'\n")
          
            /* By convention, the server responds {"error": "message"}
             * Try to extract that, but don't obfuscate the real error if
             * parsing json fails */
            
            
            // Try to parse, but if it's null, keep text instead
            text = Option(json(text).asInstanceOf[JSONObject]
                                .get("error").asInstanceOf[String]
                                ).getOrElse(text)
            
            throw new IOException(s"Error on request '$request.'\n" +
                                  s"Server responds: '$text'\n" +
                                  "Consider filing a bug report with this" +
                                  " at http://github.com/SeanTater/bridges")
        }
        
        // Handle empty responses
        if (text == null || text.isEmpty)
            throw new IOException(s"Server returned empty response for '$request'")
        else
            text
    }
    
    /** Execute a simple GET request relative to the server root.
        Omit the leading http://hostname, but include the leading /:
        [good]: /api/followgraph/user/sean
        [bad]: api/followgraph/user/sean
        [bad]: http://myserver:9183/api/followgraph/user/sean  */
    def get(url:String)=
        http(fluent.Request.Get(prepare(url)))

    /** Execute a simple GET request, implicitly converting to non-null JSON.
        See get() and json() for more information. */
    def getjs(url:String)=
        json(get(url))
    
    /** Execute a simple POST request with relative paths, taking a Scala Map()
        of request parameters. */
    def post(url:String, arguments:Map[String, String])= {
        val req = fluent.Request.Post(prepare(url))
        val form = fluent.Form.form()
        arguments.foreach { (pair) => form.add(pair._1, pair._2) }
        http(req.bodyForm(form.build()))
    }
    
    def prepare(url: String)={
      	base + url.replace("$assignment", assignment.toString)
    }
        
}