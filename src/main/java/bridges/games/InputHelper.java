package bridges.games;

import org.json.JSONException;
import org.json.JSONObject;

import bridges.connect.SocketConnection;
import bridges.connect.KeypressListener;

/**
 *  This is a helper class for handling input events and is part of the 
 *  BRIDGES Game API.
 */
class InputHelper implements KeypressListener {

    boolean upKey;
    boolean downKey;
    boolean leftKey;
    boolean rightKey;
    boolean wKey;
    boolean aKey;
    boolean sKey;
    boolean dKey;
    boolean qKey; // was button 1
    boolean spaceKey; // was button 2

    public InputHelper() {
        
        upKey = false;
        downKey = false;
        leftKey = false;
        rightKey = false;
        qKey = false;
        spaceKey = false;
        wKey = false;
        aKey = false;
        sKey = false;
        dKey = false;
    }

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

        // currently just ignore keyup events
        boolean set_to_up = type.compareTo("keyup") == 0;
        boolean set_to_down = type.compareTo("keydown") == 0;

        if (key.compareTo("ArrowUp") == 0) {
            if (set_to_up) {
                upKey = false;
            }
            if (set_to_down) {
                upKey = true;
            }
        }
        if (key.compareTo("ArrowDown") == 0) {
            if (set_to_up) {
                downKey = false;
            }
            if (set_to_down) {
                downKey = true;
            }
        }
        if (key.compareTo("ArrowLeft") == 0) {
            if (set_to_up) {
                leftKey = false;
            }
            if (set_to_down) {
                leftKey = true;
            }
        }
        if (key.compareTo("ArrowRight") == 0) {
            if (set_to_up) {
                rightKey = false;
            }
            if (set_to_down) {
                rightKey = true;
            }
        }

        if (key.compareTo("q") == 0) {
            if (set_to_up) {
                qKey = false;
            }
            if (set_to_down) {
                qKey = true;
            }
        }

        if (key.compareTo(" ") == 0) {
            if (set_to_up) {
                spaceKey = false;
            }
            if (set_to_down) {
                spaceKey = true;
            }
        }

        if (key.compareTo("w") == 0) {
            if (set_to_up) {
                wKey = false;
            }
            if (set_to_down) {
                wKey = true;
            }
        }

        if (key.compareTo("a") == 0) {
            if (set_to_up) {
                aKey = false;
            }
            if (set_to_down) {
                aKey = true;
            }
        }

        if (key.compareTo("s") == 0) {
            if (set_to_up) {
                sKey = false;
            }
            if (set_to_down) {
                sKey = true;
            }
        }

        if (key.compareTo("d") == 0) {
            if (set_to_up) {
                dKey = false;
            }
            if (set_to_down) {
                dKey = true;
            }
        }

    }

    public void status() {

        System.out.println("UP:" + up() + " DOWN:" + down() + " LEFT:" + left()
                + " RIGHT:" + right());
    }

    // / @return true if up key is depressed. false otherwise
    public boolean up() {
        return upKey;
    }

    public boolean down() {
        return downKey;
    }

    public boolean left() {
        return leftKey;
    }

    public boolean right() {
        return rightKey;
    }

    public boolean q() {
        return qKey;
    }

    public boolean space() {
        return spaceKey;
    }

    public boolean w() {
        return wKey;
    }

    public boolean a() {
        return aKey;
    }

    public boolean s() {
        return sKey;
    }

    public boolean d() {
        return dKey;
    }
}
