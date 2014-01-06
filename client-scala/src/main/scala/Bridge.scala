package bridges

/** Frontend to automate structure filling and interactive updates.
  * 
  * Students should use this class as part of a driver for their structure
  */
class Bridge(val username: String, val password: String, val assignment: Int) extends FormConnectable {
    
    /** Connect to a stream by name. */
    def stream(name: String)= {
        new BStream(this, name)
    }
    
    /** Create a network-enabled FollowGraph. */
    def followgraph(name: String)= {
        new FollowGraph(this, name)
    }
    
    /** Send serialization to the server for visualization.
      *
      * Call this after making a change to the structure, so that you can see
      * what effect this had graphically.
      *
    def draw() {
        session.send_state(structure.serialize())
    } */
}

class MockBridge(username: String, password: String, assignment: Int) extends Bridge(username, password, assignment) with DummyConnectable {}