package edu.uncc.cs.bridges_vs2;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.omg.CosNaming.NamingContextExtPackage.URLStringHelper;

import edu.uncc.cs.bridgesV2.connect.Connector;
import edu.uncc.cs.brdigesV2.validation.*;
import junit.framework.TestCase;

public class ConnectorTest extends TestCase {
	static Connector con;
	static String url = "http://bridges-cs.herokuapp.com";

	/** Setup static variables for use in later tests */
	protected void setUp() throws Exception {
		super.setUp();
		con = new Connector();
	}

	/** Test whther getServerURL() returns correct url address */
	public void testGetServerURL() {
		assertEquals("getServerURL() does not return correct server address",
				url, con.getServerURL());
	}

	/** Test whether setServerURL() sets correct url address */
	public void testSetServerURL() {
		con.setServerURL("abcd");
		assertEquals("setServerURL() did not set url to correct address",
				"abcd", con.getServerURL());

		con.setServerURL(url);
	}

	/** Test asJSONObject() with valid JSON */
	public void testAsJSONObjectWithValidJSONObjectString() throws IOException {
		String s = "{\"id\": \"1\"}";

		JSONObject o = new JSONObject();
		o.put("id", "1");

		assertTrue("asJSONObject did not return correct representation",
				o.equals(con.asJSONObject(s)));

	}

	/** Test asJSONObject() with invalid JSON */
	public void testAsJSONObjectWithInvalidJSONObjectString() {
		String s = "{\"id\" : \"1\"";

		JSONObject o = new JSONObject();
		o.put("id", "1");

		try {
			con.asJSONObject(s);
		} catch (IOException e) {
			assertTrue("asJSONObject did not return correct representation",
					e instanceof IOException);
		}

	}

	/** Test asJSONArray() with valid JSON array */
	public void testAsJSONArrayWithValidJSONArrayString() throws IOException {
		String s = "[{\"id\":\"a\"},{\"id\":\"b\"}]";

		JSONObject o1 = new JSONObject();
		JSONObject o2 = new JSONObject();

		o1.put("id", "a");
		o2.put("id", "b");

		JSONArray array = new JSONArray();
		array.add(o1);
		array.add(o2);

		assertTrue("asJSONArray did not return correct representation",
				array.equals(con.asJSONArray(s)));
	}

	/** Test asJSONArray() with invalid JSON array */
	public void testAsJSONArrayWithInvalidJSONArrayString() {
		String invalidJSON = "[{]}";

		try {
			con.asJSONArray(invalidJSON);
		} catch (IOException e) {
			assertTrue("Invalid JSON did not throw IOException",
					e instanceof IOException);
		}
	}

	/** NOT TESTED */
	public void testSafeJSONTraverse() {
		System.out.println("connector.safeJSONTraverse() not tested");
	}

	/** NOT TESTED */
	public void testExecuteHTTPRequest() {
		System.out.println("connector.executeHTTPRequest() not tested");
	}

	/** NOT TESTED */
	public void testGet() throws RateLimitException, IOException {
		System.out.println("connector.get() not tested");
	}

	/** NOT TESTED */
	public void testPostStringMapOfStringString() {
		System.out.println("connector.post(HashMap) not tested");
	}

	/** NOT TESTED */
	public void testPostStringString() {
		System.out.println("connector.post(HashMap) not tested");	
	}

	/** NOT TESTED */
	public void testPrepare() {
		System.out.println("connector.post(String) not tested");	
	}

}
