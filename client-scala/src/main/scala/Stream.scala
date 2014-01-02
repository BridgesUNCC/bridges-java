package bridges

/** Frontend to automate structure filling and interactive updates.
  * 
  * Students should use this class as part of a driver for their structure
  */
class Stream(stream: String, structure: StudentStructure[Any],
              assignment: Int, username: String, password: String) {
    val session = StreamSession.load(username, password, assignment)
    var most_recent_get = -1;
    
    /** Fill the structure with all of the live entries.
      *
      * Don't call this more than once unless you want multiple copies
      */
    def get_multiple(interactive: Boolean = true) {
        for (entry <- session.entries(stream)) {
            structure.push(entry)
            if (interactive) draw()
        }
        session.save()
        draw()
    }
    
    /** Get the next most recent entry.
      * 
      * Only considers other get() calls, not get_multiple().
      * Consider calling draw() at some point.
      */
    def get()= {
        most_recent_get += 1
        session.entries(stream)(most_recent_get)
    }
    
    /** Send serialization to the server for visualization.
      *
      * Call this after making a change to the structure, so that you can see
      * what effect this had graphically.
      */
    def draw() {
        session.send_state(structure.serialize())
    }
}