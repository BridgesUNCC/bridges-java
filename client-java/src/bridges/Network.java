package bridges;
import java.io.IOException;

import org.apache.http.client.fluent.*;

public class Network {
	// Network connection abstraction and convenience methods
	// TODO: Move this to a real server
	String base_url = "http://seantater.is-a-linux-user.org";
	Executor executor; 
	Network(String username, String password) {
		// Login
		executor = Executor.newInstance().auth(username, password);
	}
	
	public String exec(Request request) {
		return exec(request, 3);
	}
	
	public String exec(Request request, int retries) {
		try {
			return executor.execute(request).returnContent().asString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (retries > 0) {
				System.out.println("Warning: Failed to contact bridges server. Retrying.");
				try {
					Thread.sleep(5);
				} catch (InterruptedException e1) {
					Thread.currentThread().interrupt();
				}
				return exec(request, retries-1);
			} else {
				System.out.println("Fatal: Failed to contact bridges server. Network connected?");
				e.printStackTrace();
				System.exit(1);
				return null; // impossible but required
			}
		}
	}
	
	public void rebase(String base) {
		base_url = url(base);
	}
	
	public String url(String end) {
		return base_url + end;
	}
}
