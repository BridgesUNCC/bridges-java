package bridges;


	/**
	 * @author mihai mehedint
	 * @date 05/02/2014
	 * @Description: implementation of Dijkstra’s shortest path
	 *              algorithm using the graph ADT
	 * class Graphl was modified after Shaffer's
	 */

	import java.util.*;

	class GraphList extends DLListNodeDijkstra{
	    GraphList(String identifier, GraphVisualizer graph){
	    		super(identifier, graph);
	    }
	}

	/** Adjacency list graph implementation */
	class GraphDijkstra extends GraphVisualizer {
	    private final int VISITED=1;
	    private final int UNVISITED=0;
	    private HashMap<String, GraphList> vertex;
	    private int numEdge;
	    private HashMap<String, Integer> Mark;

	    //constructors
	    public GraphDijkstra() {
	        Mark = new HashMap();
	        numEdge = 0;
	        vertex = new HashMap();
	    }
	    public GraphDijkstra(String ... args){
	        Init(args);
	    }//end of constructors

	    /**
	     Init initializes mark and vertex hashmaps
	     * as well as the queue for BFS
	     */ 
	    public void Init(String[] args) {
	      Mark = new HashMap(args.length);
	      for (String ar: args)
	        Mark.put(ar, UNVISITED);

	      numEdge = 0;
	      vertex = new HashMap(args.length);
	      for (String ar: args)
	        vertex.put(ar, new GraphList(ar, graph));
	    }//end of Init

	     public int n() { 
	    	 	return Mark.size(); } // # of vertices
	     public int e() { 
	    	 	return numEdge; }     // # of edges

	     /** @return the beginning node of the vertex */
	     public String Beginning() {
	          Set setVertex=vertex.entrySet();
	          Set setMark=Mark.entrySet();
	          Iterator aVertexIterator=setVertex.iterator();
	          Iterator aMarkIterator=setMark.iterator();
	          Map.Entry me = (Map.Entry)aVertexIterator.next();

	          return me.getKey().toString();
	    }//end of the Beginning method

	    /** @return the next node of the vertex */
	     public String nextInVertex(String aCurrent) {
	          Set setVertex=vertex.entrySet();
	          Set setMark=Mark.entrySet();
	          Iterator aVertexIterator=setVertex.iterator();
	          Iterator aMarkIterator=setMark.iterator();
	          while(aVertexIterator.hasNext()){
	                Map.Entry ve = (Map.Entry)aVertexIterator.next();
	                Map.Entry ma= (Map.Entry)aMarkIterator.next();
	                if (ve.getKey()==aCurrent){
	                    return ((Map.Entry)aVertexIterator.next()).getKey().toString();
	                } 
	          }

	          return null;
	    }//end of the nextInVertex method


	     /* @return the location from the D int array
	      * corresponding to the next node 
	      */
	     public int intLocation(String aCurrent) {
	          Iterator<String> vertexName = vertex.keySet().iterator();
	          int i=-1;
	          while(vertexName.hasNext()){
	            String aKey = vertexName.next();
	            i++;
	            if (aKey.equals(aCurrent)){
	                return i;
	            }
	          }
	          
	          return n();
	    }//end of the intLocation method


	     /** @return the next node of the vertex */
	     public String vertexInt(int aCurrent) {
	          Set setVertex=vertex.entrySet();
	          Set setMark=Mark.entrySet();
	          Iterator aVertexIterator=setVertex.iterator();
	          Iterator aMarkIterator=setMark.iterator();
	          int i=-1;
	          while(aVertexIterator.hasNext()){
	                i++;
	                Map.Entry ve = (Map.Entry)aVertexIterator.next();
	                Map.Entry ma= (Map.Entry)aMarkIterator.next();
	                if (i==aCurrent){        
	                    return ve.getKey().toString();
	            }    
	          }
	          
	          return null;
	    }//end of the vertexInt method

	     /** @return the vertex first neighbor */
	     public String first(String aName) {
	       GraphList aList= (GraphList)vertex.get(aName);
	       if (aList.length() == 0)
	         return (null);   // No neighbor
	       aList.moveToStart();
	       Edge it = (Edge)aList.getValue();

	       return it.vertex();
	    }//end of the first method

	    //this method returns the name of the current vertex
	    public String current(String aName) {
	        GraphList aList= (GraphList)vertex.get(aName);
	       if (aList.length() == 0)
	         return (null);   // No neighbor
	       Edge it = (Edge)aList.getValue();
	       return it.vertex();
	    }//end of current 

	    //this method returns the next child of aName
	    public String next(String aName) {
	        GraphList aList= (GraphList)vertex.get(aName);
	       if (aList.length() == 0)
	         return (null);   // No neighbor
	       aList.next();
	       Edge it = (Edge)aList.getValue();
	       return it.vertex();
	    }//end of next

	    /** Either add a vertex, returning true, or skip it and return false */
	    public boolean add(String name) {
	            if (vertex.containsKey(name)) {
	                    return false;
	            } else {
	                    vertex.put(name, new GraphList());
	                    setMark(name, UNVISITED);
	                    return true;
	            }	
	    }//end of add method

	    /** @return null of the edge if exists
	     * neighbor after w */
	     public String next(String aName, String nextName) {
	       Edge it = null;
	       GraphList aList=vertex.get(aName);
	       if (isEdge(aName, nextName)) {
	         aList.next();
	         it = (Edge)aList.getValue();
	       }
	       if (it != null)
	         return it.vertex();
	       return null; // No neighbor
	     }//end of next method

	     /** Set the weight for an edge */
	      public void setEdge(String i, String j, int weight) {
	        assert weight != 0 : "May not set weight to 0";
	        Edge currIJEdge = new Edge(j, weight);
	        Edge currJIEdge = new Edge(i, weight); 
	        GraphList aIList= (GraphList)(vertex.get(i));
	        GraphList aJList= (GraphList)(vertex.get(j));
	        if (isEdge(i, j)) { // Edge already exists in graph
	          aIList.remove();
	          aIList.insert(currIJEdge);
	        }
	        else { // Keep neighbors sorted by vertex index
	          numEdge++;
	          for (aIList.moveToStart();
	               aIList.currPos() < aIList.length();
	               aIList.next())
	            if ((((Edge)aIList.getValue()).vertex()).equals(j)) break;
	          aIList.insert(currIJEdge);
	        }
	        
	    }//end of setEdge

	      /** Delete an edge */
	      public void delEdge(String i, String j)
	        { 
	            GraphList aList= (GraphList)vertex.get(i);
	            if (isEdge(i, j)) { aList.remove(); numEdge--; } 
	        }//end of delEdge method

	      /** Determine if an edge is in the graph */
	      public boolean isEdge(String v, String w) {

	          GraphList aList= (GraphList)vertex.get(v);
	          Edge it = (Edge)aList.getValue();
	        // Check if j is the current neighbor in the list
	        if ((it != null) && (it.vertex().equals(w))) return true;
	        for (aList.moveToStart();
	             aList.currPos() < aList.length();
	             aList.next())              // Check whole list
	          if (((Edge)aList.getValue()).vertex().equals(w)) return true;
	        return false;
	      }//end of isEdge

	      /** @return edge's weight */
	      public int weight(String i, String j) {
	        GraphList aList= (GraphList)vertex.get(i);
	          if (isEdge(i, j)) return ((Edge)aList.getValue()).weight();
	            return 0; 
	      }//end of weight

	      /** Set/Get the mark value for a vertex */
	      public void setMark(String v, int val) { 
	          Mark.put(v,val); 
	      }//end of setMark

	    /**
	     * getMark retrieves the mark for a vertex
	     */  
	    public int getMark(String v) { 
	            return Mark.get(v); 
	    }//end of getmark


	    /**
	      * the Previsit method ensures all marks are
	      * reset for a new search
	    */
	    public void PreVisit(String v){
	            Set set = Mark.entrySet();
	              // Get an iterator
	              Iterator i = set.iterator();
	              while(i.hasNext()) {
	                 Map.Entry me = (Map.Entry)i.next();
	                     Mark.put((me.getKey()).toString(), UNVISITED);
	              }
	    }//end of PreVisit

	    /** Dijkstra Computes shortest path distances from s, store them in D
	     * 
	     * @param G 
	     * @param s
	     * @param D stores the cost of the path to various vertices
	     * @param paths stores the shortest path to each vertex, given s
	     */
	    public void Dijkstra(String aSource) {
	        int s=intLocation(aSource);
	        int[] D = new int[n()]; 
	        String[] paths = new String[n()];
	        PreVisit(vertexInt(s));
	        System.out.println(String.format("\n%-15s%-15s%-15s%-15s",
	                "Source", "Dest.", "Short. Dist", "Short. Path"));
	        for (int i=0; i<n(); i++){    // Initialize both arrays
	            D[i] = Integer.MAX_VALUE;
	            paths[i]=null;
	        }
	      D[s] = 0;
	      for (int i=0; i<n(); i++) {  // Process the vertices
	        int v = minVertex(D);     // Find next-closest vertex
	        setMark(vertexInt(v), VISITED);
	        if (D[v] == Integer.MAX_VALUE) return; // Unreachable

	        for (int w = intLocation(first(vertexInt(v))); w < (n());
	                w = intLocation(next(vertexInt(v), vertexInt(w)))){

	                if (D[w] > (D[v] + weight(vertexInt(v), vertexInt(w)))){
	                        D[w] = D[v] + weight(vertexInt(v), vertexInt(w)); //update cost
	                        if (paths[v]==null)
	                            paths[w]="["+vertexInt(v)+","+vertexInt(w)+"]";
	                        else
	                            paths[w]=paths[v]+",["+vertexInt(v)+","+vertexInt(w)+"]"; //update pathway
	                }
	        }
	        paths[s]="[]";
	        System.out.println(String.format("%-15s%-15s%-15s%-15s", vertexInt(s),vertexInt(v),D[v],paths[v]));
	      }
	    }//end of Dijkstra method

	    /*
	     * The minVertex method scans through the list
	     * of vertices determining the next minimum
	     * value
	     */
	    public int minVertex(int[] D) {
	              int v = 0;  // Initialize v to any unvisited vertex;

	              for (int i=0; i<n(); i++) 
	                if (getMark(vertexInt((i))) == UNVISITED) { 
	                    v = i;
	                    break; 
	                }

	              for (int i=0; i<n(); i++){  // Now find smallest value 
	                if ((getMark(vertexInt(i)) == UNVISITED) && (D[i] < D[v]))
	                  v = i;
	              }
	        return v; }// end of minVertex method

	}//end of Graphl class

}
