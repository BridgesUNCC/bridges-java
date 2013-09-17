package bridges
import net.liftweb.json

/** Abstract base of student-implemented structures except Nodes
  *
  * This would be a trait, but it needs to be inherited in Java
  */
case class StackNode[T](next: StackNode[T], element: T)

abstract class StudentStack[T] extends StudentStructure[T] {
    var root : StackNode[T]
    def serialize()= {
        val out = collection.mutable.ListBuffer[T]()
        var node = root
        while(node != null) {
            out += node.element
            node = node.next
        }
        json.Serialization.write(out.result)
    }
}

/** Pointer-based reference example of a student stack
class ReferenceStack[T] extends StudentStack[T] {
    // TODO: Replace this with Java
    var root : StackNode[T] = _
    
    def push(element: T) {
        root = new StackNode[T](root, element)
    }
    
    def pop()= {
        if (root == null)
            null
        val element = root.element
        root = root.next
        element
    }
} */