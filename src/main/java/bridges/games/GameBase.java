package bridges.games;

import bridges.connect.Bridges;
import bridges.base.NamedColor;
import bridges.base.NamedSymbol;
import bridges.base.GameGrid;

import bridges.connect.SocketConnection;

public abstract class GameBase {

    protected boolean debug = true;
    protected boolean gameStarted = false;
    
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

    // Initialize with default grid size 30x30
    public GameBase(int assid, String login, String apikey) {
        gameBaseInit(assid, login, apikey, 30, 30);
    }

    // Initialize with student specified grid size no greater than 30x30
    public GameBase(int assid, String login, String apikey, int cols, int rows) {

        if ((cols*rows)>1024) { // Allows students to create smaller grids if they prefer.
            System.out.println("ERROR: Number of cells in your grid cannot exceed 32x32 or 1024.");
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

    protected void registerKeypress(InputHelper i){
        sock.addListener(i);
    }

    // /blocking and non-blocking games have different start sequences
    public abstract void start();

    // /students can decide how to initialize their game grid
    public abstract void initialize();

    // /What happens at each step of the game
    public abstract void gameLoop();
    
    // /Stops game updates. Student must use start() again to
    // /restart the games updating process.
    public void quit(){
        gameStarted = false;
    }

    // /set the title of the game
    protected void setTitle(String title) {
        bridges.setTitle(title);
    }

    // /give a short discription of the game
    protected void setDescription(String desc) {
        bridges.setDescription(desc);
    }

    // /set background color of cell x, y to c
    protected void setBGColor(int x, int y, NamedColor c) {
        grid.setBGColor(y, x, c);
    }

    // /set symbol and foreground color of cell x, y to s and c
    protected void drawObject(int x, int y, NamedSymbol s, NamedColor c) {
        grid.drawObject(y, x, s, c);
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
