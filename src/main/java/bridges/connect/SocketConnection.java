package bridges.connect;

import io.socket.client.IO;
import io.socket.emitter.Emitter;

import java.util.*;
import org.json.JSONObject;



/**
 *	This class is a wrapper for a socket.io socket connection for BRIDGES
 */
public class SocketConnection {
	private Bridges bridges;

	/**
	 *  Pass the BRIDGES object to this class
	 *	@param b  Bridges object
	 */
	public SocketConnection (Bridges b) {
		bridges = b;
	}

	// This is the actual socket
	private io.socket.client.Socket socket;

	// Keep track of keypress listeners
	private List<KeypressListener> listeners = new ArrayList<KeypressListener>();

	/**
	 * Register listeners to keypress events
	 * @param toAdd keypress listener object
	 */
	public void addListener(KeypressListener toAdd) {
		if (Bridges.getDebugFlag())
			System.out.println("subscribing to keypress events..");
		listeners.add(toAdd);
	}

	/**
	 *	Given username and assignment number, set up a connection to the socket
	 *	server.<br>
	 *  <b>TODO:</b> this will need to be refactored to sit within BRIDGES, but for now
	 *  we pass credentials directly
	 */
	public void setupConnection() {
		try { // connect to the socket server

			IO.Options opts = new IO.Options();
			opts.transports = new String[] {"websocket"};


			String url = "https://bridges-games.herokuapp.com";

			if (Bridges.getDebugFlag()) {
				System.out.println("Connecting to " + url + " using mainly transport " + opts.transports[0]);
			}

			socket = IO.socket(url, opts);

			/*
			 * bind listeners to specific socket events
			 */
			socket.on(io.socket.client.Socket.EVENT_CONNECT,
			new Emitter.Listener() {
				// EVENT_CONNECT events are emitted by the server
				// whenever a socket connection is initiated and seen
				@Override
				public void call(Object... args) {
					/*
							 * Once socket connection is established, pass
							 * bridges credentials to set up channel access
					 */
					socket.emit(
						"credentials",
						"{\"user\":\""
						+ bridges.getUserName()
						+ "\",\"apikey\":\"" + bridges.getKey()
						+ "\",\"assignment\":\""
						+ bridges.getAssignmentID()
						+ "\"}");
					if (Bridges.getDebugFlag())
						System.out.println("passing credentials to socket server...");
				}
			})
			.on("announcement", new Emitter.Listener() {
				// announcement events are emitted by the server
				// whenever another socket
				// subscribes to a channel on which this socket
				// connection is listening
				@Override
				public void call(Object... args) {
					JSONObject obj = (JSONObject) args[0];
					if (Bridges.getDebugFlag())
						System.out.println("announcement: " + obj);
				}
			})
			.on("keydown", new Emitter.Listener() {
				// keydown events are emitted by the server whenever a
				// key is pressed
				// in a channel on which this socket connection is
				// listening
				@Override
				public void call(Object... args) {
					JSONObject obj = (JSONObject) args[0];
					// System.out.println(obj);

					// pass keypresses to all keypress listeners
					for (KeypressListener hl : listeners) {
						hl.keypress(obj);
					}
				}
			})
			.on("keyup", new Emitter.Listener() {
				// keyup events are emitted by the server whenever a key
				// is unpressed
				// in a channel on which this socket connection is
				// listening
				@Override
				public void call(Object... args) {
					JSONObject obj = (JSONObject) args[0];
					// System.out.println(args);

					// pass keypresses to all keypress listeners
					for (KeypressListener hl : listeners) {
						hl.keypress(obj);
					}
				}
			})
			.on(io.socket.client.Socket.EVENT_DISCONNECT,
			new Emitter.Listener() {
				// EVENT_DISCONNECT events are emitted by the
				// server if it crashes or terminates this
				// socket's connection
				@Override
				public void call(Object... args) {
					try {
						System.out.println(args);
						System.out
						.println("The server closed the connection.");
					}
					catch (Exception err) {
						System.out.println(err);
					}
				}
			});

			// Attempt to connect to the socket server
			socket.connect();

		}
		catch (Exception err) {
			System.out.println(err);
		}
	}

	/**
	 *	Send a dataframe string to the socket server <br>
	 *
	 *  <b>TODO:</b> this currently emits a gamegrid:recv event. We may need
	 *	to refactor this to consider a variety of dataframe types
	 */
	public void sendData(String dataframe) {
		if (socket == null) {
			System.out.println("Cannot send data - socket is not connected.");
			return;
		}

		// the server will receive the grid dataframe and attempt to pass it to
		// any other sockets subscribed to the same channel
		socket.emit("gamegrid:recv", dataframe);
	}

	/**
	 *	 @brief close the socket
	 */
	public void close() {
		socket.disconnect();
		socket.off();
		socket.close();
	}
}
