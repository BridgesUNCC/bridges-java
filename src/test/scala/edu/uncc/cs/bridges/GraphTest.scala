package edu.uncc.cs.bridges
import org.scalatest._
import org.apache.http.client.fluent
import scaffold.Graphl
import java.io.IOException

class GraphTest extends FlatSpec with Matchers {
	"Graphl" should "blank instantiate" in {
		val g = new Graphl()
		g should not be(null)
	}

	it should "instantiate with names" in {
		// There isn't a way to request a node so we can't verify they exist.
		val g = new Graphl("Aaron", "Bill")
		g should not be(null)
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
	}

	it should "handle dead edges" {
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

		g.setColor("Aaron", "Amber")
		g.setColor("Bill", "Blue")
		g.getColor("Aaron") should be("Amber")
		g.getColor("Bill") should be("Blue")
	}
	
	it should "handle dead node colors" in {
		var g = new Graphl()
		g.getColor("Aaron") should be(null)
		
		g = new Graphl("Aaron", "Bill")
		g.getColor("Aaron") should be(null)
		g.setColor("Aaron", "Amber")
		g.getColor("Aaron") should be("Amber")
		
	}
	
	it should "set and get edge colors" in {
		val g = new Graphl("Aaron", "Bill")
		g.setEdgeColor("Aaron", "Bill", "Turquoise")
		g.setEdgeColor("Bill", "Aaron", "Fuchsia")
		g.getEdgeColor("Aaron", "Bill") should be ("Turquoise")
		g.getEdgeColor("Aaron", "Bill") should be ("Fuchsia")
		
	}

	it should "handle dead edge colors" in {
		var g = new Graphl()
		g.getEdgeColor("Alexis", "Betty") should be(null)
		// You can't set it because they don't exist.
		g.setEdgeColor("Alexis", "Betty") should be(false)
		g.setEdgeColor("Alexis", "Betty") should be(null)

		g = new Graphl("Aaron", "Bill")
		g.getEdgeColor("Alexis", "Betty") should be(null)
		g.getEdgeColor("Aaron", "Betty") should be(null)
		g.getEdgeColor("Betty", "Aaron") should be(null)
		// Backwards doesn't count
		g.setEdgeColor("Aaron", "Bill", "Turquoise")
		g.getEdgeColor("Bill", "Aaron") should be(null)
	}
}