package bridges.games;

import bridges.connect.KeypressListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Queue;
import java.util.ArrayDeque;

public abstract class BlockingGame extends GameBase implements KeypressListener{

    protected Queue<String> keyqueue;

    // Sorts keypresses into keyqueue
    public void keypress(JSONObject keypress) {
        String type = "";
        String key = "";

        // get keypress details
        try {
            type = (String) keypress.get("type");
            key = (String) keypress.get("key");

            // System.out.println(type + ": " + key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (type.equals("keydown")) {
            synchronized (keyqueue) {
                // System.err.println(type+" "+key);
                keyqueue.add(key);
                keyqueue.notify();
            }
        }

    }

    // Returns a string corrisponding to the users keypress
    public String getKeyPress() {
        String ret = "";
        synchronized (keyqueue) {
            try {
                keyqueue.wait();
            } catch (InterruptedException ie) {

            }
            ret = keyqueue.poll();
        }
        return ret;
    }

    public BlockingGame(int assid, String login, String apikey) {
        super(assid, login, apikey);
        blockingInit();
    }

    public BlockingGame(int assid, String login, String apikey, int cols, int rows) {
        super(assid, login, apikey, cols, rows);
        blockingInit();
    }

    // Initializes specific blocking game variables
    private void blockingInit() {
        keyqueue = new ArrayDeque<String>();
         // /Create listener for non-blocking game
    }

    // / calling this function starts the game engine.
    public void start() {

        initialize();

        gameStarted = true;

	    while (gameStarted) {
	        gameLoop();
	        render();
	    }
    }
}
