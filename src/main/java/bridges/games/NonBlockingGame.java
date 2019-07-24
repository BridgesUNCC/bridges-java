package bridges.games;

import bridges.connect.SocketConnection;

/**
 * @brief This class provides the features necessary to implement
 * simple non blocking games.
 *
 * The games that can be created out of NonBlockingGame are based on a
 * simple board grid of at most 1024 cells (e.g., 32x32, or any
 * combinations less than 1024 cells). Each cell has a background
 * color, and a colored symbol.
 *
 * This class is used by having another class derive from it and
 * implement the two functions: initialize() and
 * gameLoop(). initialize() is called exactly once, on the first frame
 * of the game. It is used to make first time initializations of the
 * game state (such as setting the board in its initial position, for
 * instance in chess). However, gameLoop() is called at every frame of
 * the game. The game starts when the function start() is called on
 * the object you created.
 *
 * For this reason the simplest game that can run is created by:
 *
 * \code{.java}
 * import bridges.game.*;
 * import bridges.base.*;
 * class my_game extends NonBlockingGame {
 *   public my_game() { super (1, "myuserid",  "myapikey", 10, 10); }
 *   public void initialize()  { }
 *   public void gameLoop()  { }
 *   public static void  main (String args[]) {
 *     my_game g = new my_game();
 *     g.start();
 *   }
 * }
 * \endcode
 *
 * This game does not do anything, but it is the minimal code that
 * will run a game. Note that the constructor of my_game passes 5
 * parameters to the constructor of NonBlockingGame(). The first three
 * parameters are the classic parameters that the constructor of
 * bridges.connect.Bridges takes (e.g., assignmentID, username, apikey), the
 * last two are the size of the game board.
 *
 * To change the board, two functions are used. setBGColor() change
 * the background color of a particular cell. It takes three
 * parameters, the first two identify the cell of the board to change,
 * and the last one is a color from a color palette provided by
 * NamedColor. drawObject() takes four parameters, the first two
 * identify the cell of the board to change, the third is a symbol
 * from a symbol palette provided by NamedSymbol, the fourth is the
 * color that symbol shold be drawn in and provided by NamedColor.
 *
 * For instance, a very simple initialize() could look like:
 * \code{.java}
 * public void initialize() {
 *   setBGColor(0, 0, NamedColor.lightsalmon);
 *   drawObject(0, 0, NamedSymbol.sword, NamedColor.blue);
 * }
 * \endcode
 *
 * Note that the size of the board was set at 10x10 and that drawing
 * on a cell that does not exist will lead to an error. One can
 * specify a gameboard of a different size by changing the parameters
 * to the NonBlockingGame constructor. However, the game board can not
 * be more than 1024 cells in total, so a 15x15 board is possible, a
 * 32x32 board is the largest square board possible, and a rectangular
 * 64x16 rectangular board is also possible. But a 100x20 board would
 * be 2000 cells and is not possible. For instance a board of 16 rows
 * and 64 columns can be created defining the my_game constructor as:
 *
 * \code{.java}
 *   public my_game() { super(1, "myuserid",  "myapikey", 16, 64); }
 * \endcode
 *
 * The bridges game engine will call the gameLoop() function at each
 * frame of the game. You can write this function to modify the state
 * of the game board using setBGColor() and drawObject(). For
 * instance, the following gameLoop() will color the board randomly
 * one cell at a time.
 *
 * \code{.java}
 * public void gameLoop() {
 *   setBGColor(Math.random()*10, Math.random()*10, NamedColor.lightsalmon);
 * }
 * \endcode
 *
 * The gameLoop can also probe the state of some keys of the keyboard
 * using functions keyUp(), keyLeft(), keyDown(), keyRight(), keyW(),
 * keyA(), keyS(), keyD(), keySpace(), and keyQ(). These functions
 * return a boolean that indicate whether the key is pressed at that
 * frame or not. For instance, the following code will only color the
 * board randomly when the up arrow is pressed.
 *
 * \code{.java}
 * public void gameLoop() {
 *   if (keyUp())
 *     setBGColor(Math.random()*10, Math.rand()*10, NamedColor.lightsalmon);
 * }
 * \endcode
 *
 * @author Erik Saule
 * @date 7/21/19
 *
 **/

public abstract class NonBlockingGame extends GameBase {
    private double fps = 30.;
    
    ///helper class to make Input Management a bit easier.
    private InputHelper ih;

    ///used for fps control
    private long timeoflastframe;

    /// @brief Is the LeftArrow key currently pressed?
    ///
    /// @return true if "left" is pressed
    protected boolean keyLeft() {
        return ih.left();
    }

    /// @brief Is the RightArrow key currently pressed?
    ///
    /// @return true if "right" is pressed
    protected boolean keyRight() {
        return ih.right();
    }

    /// @brief Is the UpArrow key currently pressed?
    ///
    /// @return true if "up" is pressed
    protected boolean keyUp() {
        return ih.up();
    }

    /// @brief Is the DownArrow key currently pressed?
    ///
    /// @return true if "down" is pressed
    protected boolean keyDown() {
        return ih.down();
    }

    /// @brief Is the Q key currently pressed?
    ///
    /// @return true if "q" is pressed
    protected boolean keyQ() {
        return ih.q();
    }

    /// @brief Is the SpaceBar key currently pressed?
    ///
    /// @return true if SpaceBar is pressed
    protected boolean keySpace() {
        return ih.space();
    }

    /// @brief Is the W key currently pressed?
    ///
    /// @return true if "w" is pressed
    protected boolean keyW() {
        return ih.w();
    }

    /// @brief Is the A key currently pressed?
    ///
    /// @return true if "a" is pressed?
    protected boolean keyA() {
        return ih.a();
    }

    /// @brief Is the S key currently pressed?
    ///
    /// @return true if "s" is pressed
    protected boolean keyS() {
        return ih.s();
    }

    /// @brief Is the D key currently pressed?
    ///
    /// @return true if "d" is pressed
    protected boolean keyD() {
        return ih.d();
    }

    /// @brief Construct a NonBlockingGame object. It is expected
    /// students will never directly construct a NonBlockingGame
    /// object but rather derive from it.
    ///
    ///
    /// The created grid can not be larger than 1024 cells in total
    /// (e.g., 32x32, or 2x512 are ok).
    ///
    /// @param assignmentID bridges assignment ID
    /// @param login login on the bridges server
    /// @param apikey apikey of the account specified in login
    /// @param cols number of columns in the game
    /// @param rows number of rows in the game
    public NonBlockingGame(int assignmentID, String login, String apikey, int cols, int rows) {
        super(assignmentID, login, apikey, cols, rows);

	if ((cols * rows) > 1024) { // Allows students to create smaller grids if they prefer.
            System.out.println("ERROR: Number of cells in a non-blocking game grid cannot exceed 32x32 or 1024.");
            System.exit(1);
        }
        
        nonBlockInit();
    }

    ///Initializes specific non-blocking game variables
    private void nonBlockInit() {
        
        timeoflastframe = System.currentTimeMillis();

        // /Create input helpter for non-blocking game.
        ih = new InputHelper();
        registerKeypress(ih);
    }

    /// sleeps so there is time between frames
    private void sleepTimer() {
	sleepTimer(1000);
    }

    /// sleeps so there is time between frames
    private void sleepTimer(long timems) {
        try {
            Thread.sleep(timems); // wait for browser to connect
        } catch (InterruptedException ie) {
            quit();
        }
    }

    /// should be called right before render() Aims at having a fixed
    /// fps of 30 frames per second. This work by waiting until
    /// 1/30th of a second after the last call to this function.
    private void controlFrameRate() {
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

    /// @brief What frame rate is the game running at?
    ///
    /// @return the target framerate. The game could be somewhat
    /// slower depending on how computationally expensive the
    /// gameloop is and on the speed of the network.
    protected double getFrameRate() {
	return fps;
    }
    
    /// Call this function to start the game engine.
    ///
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
