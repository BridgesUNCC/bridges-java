package bridges.connect;

import org.json.JSONObject;

/**
 * @brief This is an internal class to BRIDGES that is not intended to be seen by the end user.
 *
 * An interface to be implemented by everyone interested in "Keypress" events. Used for the live connections provided by SocketConnection.
 */
public interface KeypressListener {
	void keypress(JSONObject keypress);
}
