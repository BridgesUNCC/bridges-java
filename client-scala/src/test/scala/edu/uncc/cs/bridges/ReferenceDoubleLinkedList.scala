import org.scalatest._
import edu.uncc.cs.bridges._

class ReferenceDoubleLinkedListTest extends FlatSpec with Matchers {
    "ReferenceDoubleLinkedList" should "be indexed and mutable" in {
        val dll = new ReferenceDoubleLinkedList[Int]
        dll.push(8)
        dll.push(6)
        dll.get(0) should be (8)
        dll.get(1) should be (6)
        dll.get(-1) should be (6)
        dll.set(1, 2)
        dll.delete(1) should be (2)
    }

    it should "throw an error when accessing out of bounds" in {
        val dll = new ReferenceDoubleLinkedList[Int]
        a [IndexOutOfBoundsException] should be thrownBy {
            dll.get(1)
        }
    }

    it should "serialize as an empty list when empty" in {
        val dll = new ReferenceDoubleLinkedList[Int]
        dll.serialize() should be ("[]")
    }
    
    it should "serialize as a FIFO list when populated" in {
        val dll = new ReferenceDoubleLinkedList[Int]
        dll.push(8)
        dll.push(9)
        dll.serialize() should be ("[8,9]")
    }
}