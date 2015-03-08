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
import edu.uncc.cs.bridgesV2.validation.RateLimitException;
import junit.framework.TestCase;

public class ConnectorTest extends TestCase {
	static Connector con;
	static String url = "http://bridges-cs.herokuapp.com";

	protected void setUp() throws Exception {
		super.setUp();
		con = new Connector();
	}

	public void testGetServerURL() {
		assertEquals("getServerURL() does not return correct server address",
				url, con.getServerURL());
	}

	public void testSetServerURL() {
		con.setServerURL("abcd");
		assertEquals("setServerURL() did not set url to correct address",
				"abcd", con.getServerURL());

		con.setServerURL(url);
	}

	public void testAsJSONObjectWithValidJSONObjectString() throws IOException {
		String s = "{\"id\": \"1\"}";

		JSONObject o = new JSONObject();
		o.put("id", "1");

		assertTrue("asJSONObject did not return correct representation",
				o.equals(con.asJSONObject(s)));

	}

	public void testAsJSONObjectWithInvalidJSONObjectString() {
		String s = "{\"id\" : \"1\"";

		JSONObject o = new JSONObject();
		o.put("id", "1");

		
		try {
			con.asJSONObject(s);
		} catch (IOException e) {
			assertTrue("asJSONObject did not return correct representation", e instanceof IOException);
		}

	}

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

	public void testAsJSONArrayWithInvalidJSONArrayString() {
		String invalidJSON = "[{]}";

		try {
			con.asJSONArray(invalidJSON);
		} catch (IOException e) {
			assertTrue("Invalid JSON did not throw IOException",
					e instanceof IOException);
		}
	}

	public void testSafeJSONTraverse() {
		
		
		fail("Not yet implemented");
	}

	public void testExecuteHTTPRequest() {
		fail("Not yet implemented");
	}

	public void testGet() throws RateLimitException, IOException {
		fail("Not yet implemented");
	}

	public void testPostStringMapOfStringString() {
		fail("Not yet implemented");
	}

	public void testPostStringString() {
		fail("Not yet implemented");
	}

	public void testPrepare() {
		fail("Not yet implemented");

	}

}
