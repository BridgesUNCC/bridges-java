package bridges
import net.liftweb.json

/** Abstract base of student-implemented structures except Nodes
  *
  * This would be a trait, but it needs to be inherited in Java
  */
abstract class StudentStructure[T] {
    // Students need to subclass this
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    def push(element: T)
    def serialize() : String
}