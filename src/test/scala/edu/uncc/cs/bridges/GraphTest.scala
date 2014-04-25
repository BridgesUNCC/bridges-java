package edu.uncc.cs.bridges
import org.scalatest._
import org.apache.http.client.fluent
import scaffold.Graphl
import java.io.IOException

class GraphTest extends FlatSpec with Matchers {
	"Graphl" should "blank instantiate and test membership" in {
		val g = new Graphl()
		g should not be(null)
		g.has("Aaron") should be(false)
	}

	it should "instantiate with names and test membership" in {
		// There isn't a way to request a node so we can't verify they exist.
		val g = new Graphl("Aaron", "Bill")
		g should not be(null)
		g.has("Aaron") should be(true)
		g.has("Alexis") should be(false)
	}

	it should "set and get edges" in {
		val g = new Graphl("Aaron", "Bill")
		// Set and check
		g.setEdge("Aaron", "Bill", 6)
		g.getEdge("Aaron", "Bill") should be(6)

		// Now the other way, but don't change the first!
		g.setEdge("Bill", "Aaron", 9)
		g.getEdge("Bill", "Aaron") should be(9)
		g.getEdge("Aaron", "Bill") should be(6)
		
		// Now allow setting it a second time
		g.setEdge("Aaron", "Bill", 7)
		g.getEdge("Aaron", "Bill") should be(7)
	}

	it should "handle dead edges" in {
		var g = new Graphl()
		g.getEdge("Alexis", "Betty") should be(0)
		// You can't set it because they don't exist.
		g.setEdge("Alexis", "Betty", 1)
		g.getEdge("Alexis", "Betty") should be(0)

		g = new Graphl("Aaron", "Bill")
		g.getEdge("Alexis", "Betty") should be(0)
		g.getEdge("Aaron", "Betty") should be(0)
		g.getEdge("Betty", "Aaron") should be(0)
		// Backwards doesn't count
		g.setEdge("Aaron", "Bill", 6)
		g.getEdge("Bill", "Aaron") should be(0)
	}

	it should "set and get node colors" in {
		val g = new Graphl("Aaron", "Bill")

		g.setNodeColor("Aaron", "Amber")
		g.setNodeColor("Bill", "Blue")
		g.getNodeColor("Aaron") should be("Amber")
		g.getNodeColor("Bill") should be("Blue")
	}
	
	it should "handle dead node colors" in {
		var g = new Graphl()
		g.getNodeColor("Aaron") should be(null)
		
		g = new Graphl("Aaron", "Bill")
		g.getNodeColor("Aaron") should be(null)
		g.setNodeColor("Aaron", "Amber")
		g.getNodeColor("Aaron") should be("Amber")	
	}
	
	it should "get and set node marks" in {
		val g = new Graphl()
		g.getMark("nobody") should be(0)
		g.add("Aaron")
		g.getMark("nobody") should be(0)
		g.getMark("Aaron") should be(0)
		g.setMark("nobody", 1)
		g.getMark("nobody") should be(0)
		g.setMark("Aaron", 1)
		g.getMark("Aaron") should be(1)
		g.setMark("Aaron", 2)
		g.getMark("Aaron") should be(2)
	}
	
	it should "set and get edge colors" in {
		val g = new Graphl("Aaron", "Bill")
		g.setEdge("Aaron", "Bill", 1);
		g.setEdgeColor("Aaron", "Bill", "Red")
		g.setEdge("Bill", "Aaron", 1);
		g.setEdgeColor("Bill", "Aaron", "Blue")
		g.getEdgeColor("Aaron", "Bill") should be("Red")
		g.getEdgeColor("Bill", "Aaron") should be("Blue")
		
	}

	it should "handle dead edge colors" in {
		var g = new Graphl()
		g.getEdgeColor("Alexis", "Betty") should be(null)
		// You can't set it because they don't exist.
		g.setEdgeColor("Alexis", "Betty", "Green") should be(false)
		g.getEdgeColor("Alexis", "Betty") should be(null)

		g = new Graphl("Aaron", "Bill")
		g.getEdgeColor("Alexis", "Betty") should be(null)
		g.getEdgeColor("Aaron", "Betty") should be(null)
		g.getEdgeColor("Betty", "Aaron") should be(null)
		
		// Backwards doesn't count
		g.setEdge("Aaron", "Bill", 1);
		g.setEdgeColor("Aaron", "Bill", "Red")
		g.getEdgeColor("Bill", "Aaron") should be(null)
		
		// Edges without color should have the color "" not null.
		g.setEdge("Bill", "Aaron", 1);
		g.getEdgeColor("Bill", "Aaron") should be("")
	}
	
	it should "add nodes" in {
		val g = new Graphl()
		g.has("Aaron") should be(false)
		g.add("Aaron")
		g.has("Aaron") should be(true)
		g.has("Bill") should be(false)
	}
	
	it should "iterate neighbors, even of null nodes and orphans" in {
	  val g = new Graphl()
	  // 0 members
	  g.first("Aaron") should be(null)
	  g.next("Aaron") should be(null)
	  g.next("Bill") should be(null) // Next without first()
	  
	  // 1 member, 0 neighbors
	  g.add("Aaron")
	  g.first("Aaron") should be(null)
	  g.next("Aaron") should be(null)
	  
	  // 2 members, 1 neighbor
	  g.add("Bill")
	  g.setEdge("Aaron", "Bill", 1)
	  g.first("Aaron") should be("Bill")
	  g.next("Aaron") should be(null)
	  g.next("Aaron") should be(null) // Just making sure!
	  
	  // 3 members, 2 neighbors
	  g.add("Carson")
	  g.setEdge("Aaron", "Carson", 2)
	  g.first("Aaron") should be("Bill")
	  g.next("Aaron") should be("Carson")
	  g.next("Aaron") should be(null)
	  
	}
}