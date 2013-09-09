Authentication
--------------

- via Web
 - Optionally login on browser
 - Make a way to add private streams (e.g. Twitter)
  - preferably only requiring logging into each one once

- via IDE
 - Student 
  - Does some coding
  - Adds bridges to the classpath
  - Runs client by calling Retrieve()
  - Optionally logs in to get better data
 - Client
  - contacts server, requests a session
  - Client says "open http://.. in a browser for visualization and private streams"
 - Server
  - gives client a url for the session (redirect)
  - Log in or visit the page while logged in to get private streams
  - Serves public streams either way
 - Session expires after n minutes (of inactivity?)
 - The student can pick a stream; default to a public one if it doesn't work



Parsing
-------

- Server
 - Downloads public streams often
 - Downloads private streams on demand
 - Parses them into individual entries
  - Each entry should have a sequential id
   - query entries since id= ...
   - query most recent n entries ...

- Client
 - Queries server for most recent n entries..
 - Paginates cache; queries for entries since id={most recent id}
 - Deserializes JSON array from server
  - Some question over what types these should be (for future parsing projects)
  - Pushes them to student structure



Visualization
-------------

- Client
 - Generates a serialization of the student structure
  - Stack becomes a JSON Array
 - Sends the JSON serialization to a specific url along with the session id (may be connected)

- Server
 - Updates the visualization
 - Pushes update to web browser via Websockets (or maybe something better?)
  - https://developer.mozilla.org/en-US/docs/WebSockets
  - lots more on Google "web sockets"
    
    
