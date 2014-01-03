package bridges
import org.json.simple._

/** Adds serialize() functionality to double linked lists.
  *
  * This would be a trait, but it needs to be inherited in Java.
  */
abstract class StudentDoubleLinkedList[T] extends StudentStructure[T] {
    /** "val top" implies you need to create a public TwoNode[T] top() */
    val top: TwoNode[T]
    
    
    def serialize()= {
        val out = collection.mutable.ListBuffer[T]()
        if (top != null) {
            var node = top
            do {
                out += node.element
                node = node.right
            } while (!(node eq top));
        }
        JSONValue.toJSONString(out.result);
    }
}