package edu.uncc.cs.bridges
import scala.collection.JavaConverters._
import org.json.simple._

/** Adds serialize() functionality to stacks.
  *
  * This would be a trait, but it needs to be inherited in Java.
  */
abstract class StudentStack[T] extends StudentStructure[T] {
    val top: OneNode[T]
    def serialize()= {
        val out = collection.mutable.ListBuffer[T]()
        var node = top
        while (node != null) {
            out += node.element
            node = node.next
        }
        JSONValue.toJSONString(out.result.asJava)
    }
}