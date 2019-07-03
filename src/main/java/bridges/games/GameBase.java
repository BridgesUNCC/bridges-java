package bridges.games;

import bridges.connect.Bridges;
import bridges.base.NamedColor;
import bridges.base.NamedSymbol;
import bridges.base.GameGrid;

import bridges.connect.SocketConnection;

public abstract class GameBase {

    protected boolean debug = true;
    
    // /Game map
    protected GameGrid grid;

    // /this stores the JSON representation that will be sent to the BRIDGES server.
    protected String gridJSON;
    protected String gridState;

    // /Bridges Connection
    protected Bridges bridges;
    protected SocketConnection sock;

    // /Checks for first time rendering grid
    protected boolean firsttime;

    // Initialize with default grid size 30x30
    public GameBase(int assid, String login, String apikey) {
        gameBaseInit(assid, login, apikey, 30, 30);
    }

    // Initialize with student specified grid size no greater than 30x30
    public GameBase(int assid, String login, String apikey, int cols, int rows) {

        if (cols > 30 || rows > 30) { // Allows students to create smaller grids if they prefer.
            System.out.println("ERROR: Number of rows or columns cannot exceed 30.");
            System.exit(1);
        }

        gameBaseInit(assid, login, apikey, cols, rows);

    }

    private void gameBaseInit(int id, String log, String key, int c, int r) {
        firsttime = true;

        // /bridges-sockets account (you need to make a new account:
        // /https://bridges-sockets.herokuapp.com/signup)
        bridges = new Bridges(id, log, key);

	if (debug) {
	    Bridges.setDebugFlag(true);
	    bridges.setVisualizeJSON(true);
	}
	
        // /make sure the bridges connects to the game version of the web app
	bridges.setServer("games");

        // /create a new color grid with random color
        grid = new GameGrid(c, r);
	grid.setEncoding("rle");
	
        // /set up socket connection to receive and send data
        sock = new SocketConnection();
        sock.setupConnection(bridges.getUserName(), bridges.getAssignment());
    }

    // /blocking and non-blocking games have different start sequences
    public abstract void start();

    // /students can decide how to initialize their game grid
    public abstract void initialize();

    // /What happens at each step of the game
    public abstract void GameLoop();

    
    // /closes the connection
    protected void quit() {
        sock.close();
    }

    // /set the title of the game
    protected void SetTitle(String title) {
        bridges.setTitle(title);
    }

    // /give a short discription of the game
    protected void SetDescription(String desc) {
        bridges.setDescription(desc);
    }

    // /set background color of cell x, y to c
    protected void SetBGColor(int x, int y, NamedColor c) {
        grid.setBGColor(y, x, c);
    }

    // /set foreground color of cell x, y to c
    protected void SetFGColor(int x, int y, NamedColor c) {
        grid.setFGColor(y, x, c);
    }

    // /set symbol of cell x, y to s
    protected void SetSymbol(int x, int y, int s) {
        grid.drawObject(y, x, s);
    }

    // /set symbol of cell x, y to s
    protected void DrawObject(int x, int y, NamedSymbol s) {
        grid.drawObject(y, x, s);
    }

    // /set symbol and foreground color of cell x, y to s and c
    protected void DrawObject(int x, int y, NamedSymbol s, NamedColor c) {
        grid.drawObject(y, x, s, c);
    }

    // /get background color of cell x, y
    protected int GetBGColor(int col, int row) {
        int color = grid.get(col, row).getBGColor();
        return color;
    }

    protected void render() {
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
}
