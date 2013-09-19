import bridges.ReferenceStack
import bridges.ReferenceStackDriver
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
}

class ReferenceStackDriverTest extends FlatSpec with Matchers {
    "ReferenceStackDriver" should "run ReferenceStack without error" in {
        ReferenceStackDriver.main()
    }
}