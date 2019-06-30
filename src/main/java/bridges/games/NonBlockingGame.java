package bridges.games;

public abstract class NonBlockingGame extends GameBase {

    // /used for fps control
    private long timeoflastframe;

    // /helper class to make Input Management a bit easier.
    private InputHelper ih;

    // / @return true if "left" is pressed
    protected boolean KeyLeft() {
        return ih.left();
    }

    // / @return true if "right" is pressed
    protected boolean KeyRight() {
        return ih.right();
    }

    // / @return true if "up" is pressed
    protected boolean KeyUp() {
        return ih.up();
    }

    // / @return true if "down" is pressed
    protected boolean KeyDown() {
        return ih.down();
    }

    // / @return true if "button1 - q" is pressed
    protected boolean Keyq() {
        return ih.q();
    }

    // / @return true if "button2 - p" is pressed
    protected boolean Keyp() {
        return ih.p();
    }

    // / @return true if "w" is pressed
    protected boolean Keyw() {
        return ih.w();
    }

    // / @return true if "a" is pressed
    protected boolean Keya() {
        return ih.a();
    }

    // / @return true if "s" is pressed
    protected boolean Keys() {
        return ih.s();
    }

    // / @return true if "d" is pressed
    protected boolean Keyd() {
        return ih.d();
    }

    // /takes bridges credential and information as a parameter. Default 30x30 grid size.
    public NonBlockingGame(int assid, String login, String apikey) {
        super(assid, login, apikey);
        nonBlockInit();
    }

    // /takes bridges credential and information as a parameter. Student grid size.
    // /no greater than 30x30
    public NonBlockingGame(int assid, String login, String apikey, int cols, int rows) {
        super(assid, login, apikey, cols, rows);
        nonBlockInit();
    }

    // /Initializes specific non-blocking game variables
    private void nonBlockInit() {
        timeoflastframe = System.currentTimeMillis();
        ih = new InputHelper(sock);
    }

    // /sleeps so there is time between frames
    private void sleepTimer() {
        try {
            Thread.sleep(5 * 1000); // wait for browser to connect
        } catch (InterruptedException ie) {
            quit();
        }
    }

    // /should be called right before render() Aims at having a fixed
    // /fps of 30 frames per second. This work by waiting until
    // /1/30th of a second after the last call to this function.
    private void controlFrameRate() {
        int fps = 20;
        double hz = 1. / fps;

        long currenttime = System.currentTimeMillis();
        long theoreticalnextframe = timeoflastframe + (int) (hz * 1000);
        long waittime = theoreticalnextframe - currenttime;

        if (waittime > 0) {
            sleepTimer();
        }
        timeoflastframe = System.currentTimeMillis();
    }

    // /calling this function starts the game engine.
    public void start() {

        sleepTimer();
        // associate the grid with the Bridges object
        bridges.setDataStructure(grid);
        // visualize the grid
        render();
        initialize();

        while (true) {
            GameLoop();
            render();
            controlFrameRate();
            // System.out.println("rendered");
        }
    }
}
