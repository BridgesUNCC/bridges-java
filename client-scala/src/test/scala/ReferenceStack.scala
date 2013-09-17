import bridges.ReferenceStack
import org.scalatest._

class ReferenceStackTest extends FlatSpec with Matchers {

  "ReferenceStack" should "pop values in last-in-first-out order" in {
    val stack = new ReferenceStack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }

  it should "return null if an empty stack is popped" in {
    // Do we really want to require students to do this?
    val emptyStack = new ReferenceStack[Int]
    emptyStack.pop() should be (null)
  }
}