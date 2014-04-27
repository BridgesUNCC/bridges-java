package edu.uncc.cs.bridges
import org.scalatest._
import java.io.IOException
import scala.collection.JavaConverters._

class MovieTest extends FlatSpec with Matchers {
    
    "movies()" should "complain helpfully about empty responses" in {
        val bridge = new EchoBridge("")
        a [IOException] should be thrownBy {
            bridge.neighbors("actor/name")
        }
    }
    
    
    
	"movies()" should "return the movies an actor played in" in {
		val bridge = new EchoBridge("""
		    [
			    {
			        "adult": false,
			        "character": "Joe Taylor",
			        "credit_id": "52fe422ac3a36847f80092b1",
			        "id": 237,
			        "original_title": "Young Adam",
			        "poster_path": "/zruee1cUYJXMuGFWJfJ1o0WE7Wm.jpg",
			        "release_date": "2003-09-26",
			        "title": "Young Adam"
			    },
			    {
			        "adult": false,
			        "character": "Younger Ed Bloom",
			        "credit_id": "52fe4258c3a36847f8016fff",
			        "id": 587,
			        "original_title": "Big Fish",
			        "poster_path": "/6DRFdlNZpAaEt7eejsbAlJGgaM7.jpg",
			        "release_date": "2003-12-10",
			        "title": "Big Fish"
			    },
			    {
			        "adult": false,
			        "character": "Renton",
			        "credit_id": "52fe4260c3a36847f8019913",
			        "id": 627,
			        "original_title": "Trainspotting",
			        "poster_path": "/p1O3eFsdb0GEIYu87xlwV7P4jM1.jpg",
			        "release_date": "1996-07-19",
			        "title": "Trainspotting"
			    },
			    {
			        "adult": false,
			        "character": "Christian",
			        "credit_id": "52fe427cc3a36847f802277d",
			        "id": 824,
			        "original_title": "Moulin Rouge!",
			        "poster_path": "/zsphaxX7NaZyFTYpdlo2yz7q7wy.jpg",
			        "release_date": "2001-05-16",
			        "title": "Moulin Rouge!"
			    }
		    ]""")
		bridge.movies("Ewan McGregor").asScala should be(List("Young Adam", "Big Fish", "Trainspotting", "Moulin Rouge!"))
	}
}