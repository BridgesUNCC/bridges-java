package bridges
import net.liftweb.json

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
case class OneNode[T](next: OneNode[T], element: T)


/** Base node for structures with two links.
  * 
  * Students should use it
  */
case class TwoNode[T](left: TwoNode[T], right: TwoNode[T], element: T)