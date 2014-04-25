package edu.uncc.cs.bridges
import org.scalatest._
import java.io.IOException
import scaffold._
    
class FollowGraphTest extends FlatSpec with Matchers {
    val bridge = new EchoBridge("")
    
    "neighbors()" should "complain helpfully about empty responses" in {
        a [IOException] should be thrownBy {
            bridge.neighbors("provider/screenname")
        }
    }
    
    it should "load from a screenname via JSON" in {
        val fbridge = new EchoBridge("""{"followers": []}""")
        
        fbridge.neighbors("provider/screenname").size() should be(0)
        
    }
    
    it should "retrieve followers" in {
        val fbridge = new EchoBridge("""{"followers": ["FredElmquist", "AltonIsenhour"]}""")
        
        val followers = fbridge.neighbors("provider/screenname")
        followers.size() should be(2)
        followers.get(0) should be("provider/FredElmquist")
        followers.get(1) should be("provider/AltonIsenhour")
    }
    
    "generateGraphJson" should "generate JSON for empty graphs" in {
        val graph = new Graphl()
        val json = bridge.generateGraphJson("provider/Aaron", graph)
        json should be("""{"nodes":[{"name":"Aaron","color":""}],"links":[]}""")
    }
    
    it should "generate JSON for one node graphs" in {
        val graph = new Graphl("provider/Aaron")
        val json = bridge.generateGraphJson("provider/Aaron", graph)
        json should be("""{"nodes":[{"name":"Aaron","color":""}],"links":[]}""")
    }
    it should "generate JSON for one colored node graphs" in {
        val graph = new Graphl("provider/Aaron")
        graph.setNodeColor("provider/Aaron", "green")
        val json = bridge.generateGraphJson("provider/Aaron", graph)
        json should be("""{"nodes":[{"name":"Aaron","color":"green"}],"links":[]}""")
    }
    
    it should "generate JSON for isolated graph segments in two node graphs" in {
        // Is this good or bad?
        val graph = new Graphl("provider/Aaron", "provider/Bill")
        val json = bridge.generateGraphJson("provider/Aaron", graph)
        json should be("""{"nodes":[{"name":"Aaron","color":""}],"links":[]}""")
    }
    
    it should "generate JSON for links between two nodes" in {
        val graph = new Graphl("provider/Aaron", "provider/Bill")
        graph.setEdge("provider/Aaron", "provider/Bill", 19)
        graph.setEdge("provider/Bill", "provider/Aaron", 1)
        val json = bridge.generateGraphJson("provider/Aaron", graph)
        json should be("""{"nodes":[{"name":"Aaron","color":""},{"name":"Bill","color":""}],"links":[{"source":0,"target":1,"color":"","value":19},{"source":1,"target":0,"color":"","value":1}]}""")
    }
    
    it should "generate JSON for colored links between two nodes" in {
        val graph = new Graphl("provider/Aaron", "provider/Bill")
        graph.setEdge("provider/Aaron", "provider/Bill", 19)
        graph.setEdgeColor("provider/Aaron", "provider/Bill", "green")
        val json = bridge.generateGraphJson("provider/Aaron", graph)
        json should be("""{"nodes":[{"name":"Aaron","color":""},{"name":"Bill","color":""}],"links":[{"source":0,"target":1,"color":"green","value":19}]}""")
    }
    
    it should "generate the same complicated JSON multiple times" in {
        val graph = new Graphl()
        graph.add("provider/Aaron")
        graph.add("provider/Bill")
        graph.setNodeColor("provider/Aaron", "yellow")
        graph.setNodeColor("provider/Bill", "gray")
        graph.setEdge("provider/Aaron", "provider/Bill", 19)
        graph.setEdge("provider/Bill", "provider/Aaron", 1)
        graph.setEdgeColor("provider/Aaron", "provider/Bill", "green")
        graph.setEdgeColor("provider/Bill", "provider/Aaron", "orange")
        bridge.generateGraphJson("provider/Aaron", graph) should be("""{"nodes":[{"name":"Aaron","color":"yellow"},{"name":"Bill","color":"gray"}],"links":[{"source":0,"target":1,"color":"green","value":19},{"source":1,"target":0,"color":"orange","value":1}]}""")
        bridge.generateGraphJson("provider/Aaron", graph) should be("""{"nodes":[{"name":"Aaron","color":"yellow"},{"name":"Bill","color":"gray"}],"links":[{"source":0,"target":1,"color":"green","value":19},{"source":1,"target":0,"color":"orange","value":1}]}""")
        bridge.generateGraphJson("provider/Aaron", graph) should be("""{"nodes":[{"name":"Aaron","color":"yellow"},{"name":"Bill","color":"gray"}],"links":[{"source":0,"target":1,"color":"green","value":19},{"source":1,"target":0,"color":"orange","value":1}]}""")
    }
}