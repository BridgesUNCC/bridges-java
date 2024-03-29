package bridges.games;

import bridges.connect.Bridges;
import bridges.base.NamedColor;
import bridges.base.NamedSymbol;
import bridges.base.GameGrid;

import bridges.connect.SocketConnection;
import bridges.connect.KeypressListener;


public abstract class GameBase {

    protected boolean debug = false;
    protected boolean gameStarted = true;

    // /Game map
    private GameGrid grid;

    // /this stores the JSON representation that will be sent to the BRIDGES server.
    private String gridJSON;
    private String gridState;

    // /Bridges Connection
    private Bridges bridges;
    private SocketConnection sock;

    // /Checks for first time rendering grid
    private boolean firsttime;

    // Initialize with student specified grid size no greater than 30x30
    public GameBase(int assid, String login, String apikey, int rows, int cols) {
        gameBaseInit(assid, login, apikey, rows, cols);
    }


    public GameBase(int assid, String login, String apikey, int rows, int cols, boolean d) {
	debug = d;
        gameBaseInit(assid, login, apikey, rows, cols);
    }

    
    private void gameBaseInit(int id, String log, String key, int rows, int cols) {
        firsttime = true;

        // /bridges-sockets account (you need to make a new account:
        // /https://bridges-sockets.herokuapp.com/signup)
        bridges = new Bridges(id, log, key);

        if (debug) {
            Bridges.setDebugFlag(true);
            bridges.setJSONFlag(true);
        }

        // /make sure the bridges connects to the game version of the web app
        bridges.setServer("games");

        // /create a new color grid with random color
        grid = new GameGrid(rows, cols);
        grid.setEncoding("rle");

        // /set up socket connection to receive and send data
        sock = new SocketConnection(bridges);
        sock.setupConnection();
        // This will listen for any shutdown call including sigterm and make sure
        // to cleanup our socket io client
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Thread.sleep(200);
                    System.out.println("Shutting down ...");
                    //some cleaning up code...
                    terminateNetwork();

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
    }

    /// @brief register a new KeypressListener
    ///
    /// Students should not have to call this function directly.  The
    /// KeypressListener listener will get notified of all keypresses
    /// (up and down) that happens in the game.
    ///
    /// @param kl a KeypressListener to register
    void registerKeypress(KeypressListener kl) { //having the function at package level prevents user code from calling this function directly
        sock.addListener(kl);
    }

    /// @brief Call this function from main to start the game.
    public abstract void start();

    /// @brief This function is called once when the game starts.
    ///
    /// Students write this function.
    /// It will be called once at the begining of the game.
    protected abstract void initialize();

    /// @brief This function is called once per frame of the game.
    ///
    /// Students write this function.
    /// It will be called at each frame of the game.
    protected abstract void gameLoop();

    /// @brief Call this function to stop the game. 
    protected void quit() {
        gameStarted = false;
    }

    /// @brief Set the title of the game
    ///
    /// @param title Title of the game
    protected void setTitle(String title) {
        bridges.setTitle(title);
    }

    /// @brief Set a short description of the game
    ///
    /// @param desc Description of the game
    protected void setDescription(String desc) {
        bridges.setDescription(desc);
    }

    /// @brief Change the background color of a cell
    ///
    /// @param row -  row of the cell to set
    /// @param col - column of the cell to set
    /// @param c NamedColor to set
    protected void setBGColor(int row, int col, NamedColor c) {
        grid.setBGColor(row, col, c);
    }

    /// @brief What color is this cell?
    ///
    /// @param row -  row of the cell 
    /// @param col -  column of the cell 
    protected NamedColor getBGColor(int row, int col) {
        return grid.getBGColor(row, col);
    }

    /// @brief What object is in this cell?
    ///
    /// @param row -  row of the cell 
    /// @param col -  column of the cell 
    protected NamedSymbol getSymbol(int row, int col) {
        return grid.getSymbol(row, col);
    }

    /// @brief What color is object in this cell?
    ///
    /// @param row -  row of the cell 
    /// @param col -  column of the cell 
    protected NamedColor getSymbolColor(int row, int col) {
        return grid.getSymbolColor(row, col);
    }
    
    /// @brief Draw a symbol on the game
    ///
    /// @param row - row of the cell to draw the object on
    /// @param col -  column of the cell to draw the object on
    /// @param s symbol representing the object
    /// @param c color of the object
    protected void drawSymbol(int row, int col, NamedSymbol s, NamedColor c) {
        grid.drawSymbol(row, col, s, c);
    }


    /// @brief Renders the game
    ///
    /// Student should not have to call this function directly. It is
    /// called automatically by Bridges.
    void render() { //having this function at package level prevent user code from calling it by mistake
        if (firsttime) {
            firsttime = false;
            // associate the grid with the Bridges object
            bridges.setDataStructure(grid);

            // visualize the grid
            try {
                bridges.visualize();
            } catch (Exception err) {
                System.out.println(err);
            }
        }

        gridState = grid.getDataStructureRepresentation();
        gridJSON = '{' + gridState;
        // System.out.println(gridJSON);

        // send valid JSON for grid into the socket
        sock.sendData(gridJSON);
    }

    /// @brief How wide is the Game Board?
    ///
    /// @return the number of columns of the board
    protected int getBoardWidth() {
	int size[] = grid.getDimensions();
	return size[1];
    }

    /// @brief How tall is the Game Board?
    ///
    /// @return the number of rows of the board
    protected int getBoardHeight() {
	int size[] = grid.getDimensions();
	return size[0];
    }

    /// @brief terminal all network connections
    /// Note that it takes a minute for all threads to gracefully exit
    protected void terminateNetwork() {
        sock.close();
    }
}
