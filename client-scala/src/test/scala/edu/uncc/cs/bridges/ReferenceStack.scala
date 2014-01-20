import edu.uncc.cs.bridges._
import org.scalatest._
import java.util.EmptyStackException

class ReferenceStackTest extends FlatSpec with Matchers {
  "ReferenceStack" should "pop values in last-in-first-out order" in {
    val stack = new ReferenceStack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }

  it should "throw an EmptyStackException if an empty stack is popped" in {
    val emptyStack = new ReferenceStack[Int]
    a [EmptyStackException] should be thrownBy {
        emptyStack.pop()
    }
  }
  
  it should "serialize an empty stack as an empty list" in {
    (new ReferenceStack[Int]).serialize() should be ("[]")
  }
  
  it should "serialize stack elements in LIFO order" in {
    val stack = new ReferenceStack[Int]
    stack.push(1)
    stack.top should not be (null)
    stack.push(2)
    stack.serialize() should be ("[2,1]")
  }
}