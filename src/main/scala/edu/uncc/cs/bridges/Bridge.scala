package edu.uncc.cs.bridges
import java.util
import java.util.ArrayDeque
import org.json.simple._
import scala.collection._
import scala.collection.JavaConverters._
import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

/**
 * Network-enabled sample data aggregator.
 * Bridges offers connectivity for students to more easily use interesting real
 * world data for introductory projects.
 */
class Bridge(val assignment: Int) extends KeyConnectable {
    
    /** 
     * Connect to a streaming data source such as a social network feed.
     * This feature is not yet complete. Use at your own peril.
     * 
     * @unimplemented
     * @param name  The name of the stream to receive
     */
    def stream(name: String)= {
        new BStream(this, name)
    }
    
    /**
     * Upload visualization data.
     * Note that only one contiguous group will be shown.
     * So if you only see half of the graph you expected, make sure that there
     * is a path between the center node and the missing nodes.
     *  
     *  @param graph: a Graph or a subclass of Graph
     *  @param center: the central node of the visualization
     *  
     *  @return JSON-encoded String
     */
    def displayGraph(center: String, graph: Graph)= {
    	// Create and upload JSON
        post("/assignments/$assignment", generateGraphJson(center, graph))
        // Return a URL to the user
        s"/assignments/${assignment}/YOUR_USERNAME"
    }
    
    /**
     * Split an identifier into its constituent parts.
     * An identifier should look like "example.com/username".
     * @private
     * @param identifier
     * @return a length-2 String Array of [provider, username]
     */
    def splitIdentifier(identifier: String, provider_optional: Boolean=false)= {
    	if (! identifier.contains("/")) {
    		if (provider_optional)
    			Array("", identifier)
    		else
    			throw new RuntimeException(s"Provider or screenname missing in $identifier. Should look like: example.com/username")
    	} else {
    		identifier.split("/", 2)
    	}
    	 
    }
    
    /**
     * Generate the JSON to be uploaded.
     * It uses a BFS, and expects one contiguous group of nodes.
     * 
     * So if you only see half of the graph you expected, make sure that there
     * is a path between the center node and the missing nodes.
     * 
     *  This method is not idempotent; it will edit the graph and will not
     *  produce an accurate graph the second time.
     *  
     *  This method can be useful for debugging. But you should usually prefer
     *  to use displayGraph.
     *  
     *  @param graph: a Graph or a subclass of Graph
     *  @param center: the central node of the visualization
     * 
     */
    def generateGraphJson(center: String, graph: Graph)= {
    	val VISITED=1
    	val open = new ArrayDeque[String]();
    	graph.clearMarks();
    	graph.setMark(center, VISITED);
    	open.add(center);
    	
    	// These maps will be JSON objects
    	var edges = ListBuffer[Map[String, String]]()
    	var nodes = ListBuffer[Map[String, String]]()
    	// Gives the nodes a natural order
    	var names = ListBuffer[String]()
    	  
    	// Traverse the graph, as a BFS (although a DFS would work well too) 
    	while (! open.isEmpty()) {
    	  val local = open.remove()
    	  val name = splitIdentifier(local, true)(1)
    	  nodes += Map(
    	      "name" -> name,
    	      "color" -> Option(graph.getNodeColor(local)).getOrElse("")
	      )
	      names += name
    	  var neighbor = graph.first(local)
    	  while (neighbor != null) {
    	      edges += Map(
    	          "source" -> name,
    	          "target" -> splitIdentifier(neighbor, true)(1),
    	          "color" -> Option(graph.getEdgeColor(local, neighbor)).getOrElse(""),
    	          "value" -> graph.getEdge(local, neighbor).toString()
	          )
    	      if (graph.getMark(neighbor) != VISITED) {
    	    	  graph.setMark(neighbor, VISITED)
    	    	  open.add(neighbor);
    	      }
    	      neighbor = graph.next(local)
    	  }
    	}
    	
    	// Convert the JSON-like maps into real JSON
    	var node_string = nodes
			.toList.sortBy( _("name") )  // Improve testability
			.map {
    			_
    				.map({case (k, v) => s""""$k":"$v""""})
    				.reduceOption(_+","+_)
    				.getOrElse("")
    		}
			.map(a => s"{$a}")
			.reduceOption(_+","+_)
			.map(a => s"[$a]")
			.getOrElse("[]")
		
		// Repeat for edges
		names = names.sorted
    	var edge_string = edges
			.map { m =>
				s"""{"source":${names.indexOf(m("source"))},"target":${names.indexOf(m("target"))},"color":"${m("color")}","value":${m("value")}}"""
			}
			.reduceOption(_+","+_)
			.map(a => s"[$a]")
			.getOrElse("[]")
    	s"""{"nodes":$node_string,"links":$edge_string}"""
    }
    
    /** Automatically choose whether to open a node identifier with:
     *  Twitter via followers(),
     *  TMDb with movies(), or
     *  RottenTomatoes with actors()
     *  
     *  @returns an array of identifiers
     */
    def neighbors(identifier: String, max: Integer=100)= {
    	val Array(service, screen_name) = splitIdentifier(identifier) 
    	if (service.equals("twitter.com"))
    		followers(identifier, max)
		else if (service.equals("actor"))
			movies(identifier, max)
		else if (service.equals("movie"))
			actors(identifier, max)
		else
		    //List[String]().asJava
			throw new RuntimeException(s"Unrecognized service $service. Choose from twitter.com, actor, or movie")
    }
    
    /** List the user's followers as more FollowGraphNodes.
        Limit the result to `max` followers. Note that results are batched, so
        a large `max` (as high as 200) _may_ only count as one request.
        See Bridges.followgraph() for more about rate limiting. */
    def followers(screen_name_identifier: String, max: Integer=100)= {
    	val Array(service, screen_name) = splitIdentifier(screen_name_identifier, true)
        (try {
	        val response = getjs(s"/streams/twitter.com/followers/$screen_name/$max")
	        val users = response.get("followers").asInstanceOf[util.List[JSONValue]]
	        if (users == null) {
	            // Twitter sometimes gives us garbage
	    		List()
	        } else {
	        	(0 until users.size()).map {
	        		i => s"twitter.com/${users.get(i).asInstanceOf[String]}"
	        	}
	        }
        } catch {
        	case e: RateLimitException => List()
    	}).asJava
    }
    
    /**
     * Return a list of movies an actor played in.
     * 
     * The data comes courtesy of RottenTomatoes.
     * 
     * The quota for this resource is about 10k actors/day but is shared by all
     * students. So if you consume all 10k, it will be a bad day. Please make
     * sure you limit your queries appropriately.
     * 
     */
    def movies(actor_identifier: String, max: Integer=100)= {
    	val Array(service, actor) = splitIdentifier(actor_identifier, true) 
        (try {
	        val response = getjsarray(s"/streams/actors/$actor")
	        val movies_json = response.asInstanceOf[util.List[JSONValue]]
	        // Get (in JS) movies_json.map(function(m) { return m.title; }) 
        	(0 until movies_json.size()).take(max).map {
        		i => movies_json
    				.get(i).asInstanceOf[JSONObject]
    				.get("title").asInstanceOf[String]
        	}.map { "movie/" + _ }
        } catch {
        	case e: RateLimitException => List()
        	// TODO: Decide whether to warn, error, or eat cast exceptions
        	case e: ClassCastException => List()
    	}).asJava
    }
    
    /**
     * Return the actors that played in a movie.
     * 
     * The data comes courtesy of TMDb.
     * 
     * This resource has unlimited queries but has caveats. Not every extra
     * that played in every movie ever is listed in the database and some
     * movies are documented rather sparsely. Expect some to be missing.
     */
    def actors(movie_identifier: String, max: Integer=100)= {
    	val Array(service, movie) = splitIdentifier(movie_identifier, true) 
        (try {
	        val response = getjsarray(s"/streams/rottentomatoes.com/$movie")
	        // We will assume that the first movie is the right one
	        val actors_json = response.asInstanceOf[util.List[JSONValue]]
	        if (actors_json.isEmpty()) {
	        	// There wasn't a first movie.
	        	List()
        	} else {
        		// Get (in JS) movies[0].abridged_cast.map(function(a) {return a.name;})
	        	val abridged_cast = actors_json
	        		.get(0).asInstanceOf[JSONObject]
    				.get("abridged_cast").asInstanceOf[JSONArray];
	        	(0 until abridged_cast.size()).take(max).map {
	        		i => abridged_cast
	    				.get(i).asInstanceOf[JSONObject]
	    				.get("name").asInstanceOf[String]
	        	}.map { "actor/" + _ }
        	}
        } catch {
        	case e: RateLimitException => List()
        	// TODO: Decide whether to warn, error, or eat cast exceptions
        	case e: ClassCastException => List()
    	}).asJava
    }
}