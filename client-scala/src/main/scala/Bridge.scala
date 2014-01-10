package bridges

/** Network-enabled sample data aggregator.
  * Bridges offers connectivity for students to more easily use interesting real
  * world data for introductory projects. */
class Bridge(val username: String, val password: String, val assignment: Int) extends FormConnectable {
    
    /** Connect to a streaming data source such as a social network feed.
        This feature is not yet complete. Use at your own peril. */
    def stream(name: String)= {
        new BStream(this, name)
    }
    
    /** Create a network of followers, with this screen_name at the root.
        Followers are loaded on the fly and node expansion is rate limited by
        social network providers. If you hit the rate limit, wait a few minutes
        (up to 15) and try again.
        Results are cached on the server, so your maximum graph size will grow
        with time. */
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