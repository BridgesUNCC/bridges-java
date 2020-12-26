package bridges.connect;

import org.json.JSONObject;

/**
 * An interface to be implemented by everyone interested in "Keypress" events
 */
public interface KeypressListener {
	void keypress(JSONObject keypress);
}
