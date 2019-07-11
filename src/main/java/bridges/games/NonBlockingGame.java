package bridges.games;

import bridges.connect.SocketConnection;

public abstract class NonBlockingGame extends GameBase {

    // /helper class to make Input Management a bit easier.
    private InputHelper ih;

    // /used for fps control
    private long timeoflastframe;

    // / @return true if "left" is pressed
    protected boolean keyLeft() {
        return ih.left();
    }

    // / @return true if "right" is pressed
    protected boolean keyRight() {
        return ih.right();
    }

    // / @return true if "up" is pressed
    protected boolean keyUp() {
        return ih.up();
    }

    // / @return true if "down" is pressed
    protected boolean keyDown() {
        return ih.down();
    }

    // / @return true if "button1 - q" is pressed
    protected boolean keyQ() {
        return ih.q();
    }

    // / @return true if "button2 - p" is pressed
    protected boolean keySpace() {
        return ih.space();
    }

    // / @return true if "w" is pressed
    protected boolean keyW() {
        return ih.w();
    }

    // / @return true if "a" is pressed
    protected boolean keyA() {
        return ih.a();
    }

    // / @return true if "s" is pressed
    protected boolean keyS() {
        return ih.s();
    }

    // / @return true if "d" is pressed
    protected boolean keyD() {
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

        // /Create input helpter for non-blocking game.
        ih = new InputHelper();
        registerKeypress(ih);
    }

    // /sleeps so there is time between frames
    private void sleepTimer() {
        try {
            Thread.sleep(5 * 1000); // wait for browser to connect
        } catch (InterruptedException ie) {
            quit();
        }
    }

    // /sleeps so there is time between frames
    private void sleepTimer(long timems) {
        try {
            Thread.sleep(timems); // wait for browser to connect
        } catch (InterruptedException ie) {
            quit();
        }
    }

    // /should be called right before render() Aims at having a fixed
    // /fps of 30 frames per second. This work by waiting until
    // /1/30th of a second after the last call to this function.
    private void controlFrameRate() {
        int fps = 30;
        double hz = 1. / fps;

        long currenttime = System.currentTimeMillis();
        long theoreticalnextframe = timeoflastframe + (int) (hz * 1000);
        long waittime = theoreticalnextframe - currenttime;

        //System.out.println(waittime);
        if (waittime > 0) {
            sleepTimer(waittime);
        }
        timeoflastframe = System.currentTimeMillis();
    }

    // /calling this function starts the game engine.
    public void start() {

        sleepTimer();
        // visualize the grid
        render();
        initialize();

        gameStarted = true;

        while (gameStarted) {
            gameLoop();
            render();
            controlFrameRate();
            // System.out.println("rendered");
        }
    }
}
