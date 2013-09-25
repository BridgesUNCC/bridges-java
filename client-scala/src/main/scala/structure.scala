package bridges
import net.liftweb.json
import scala.reflect.BeanProperty

/** Abstract base of student-implemented structures except Nodes.
  *
  * This would be a trait, but it needs to be inherited in Java
  */
abstract class StudentStructure[T] {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    def push(element: T)
    def serialize() : String
}


/** Base node for structures with one link.
  * 
  * Students should use it
  */
case class OneNode[T](@BeanProperty var next: OneNode[T],
                      @BeanProperty val element: T)



/** Base node for structures with two links.
  * 
  * Students should use it
  */
case class TwoNode[T](@BeanProperty var left: TwoNode[T],
                      @BeanProperty var right: TwoNode[T],
                      @BeanProperty val element: T)