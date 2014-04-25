package edu.uncc.cs.bridges
import org.scalatest._
import scaffold.Graphl
import java.io.IOException
import scala.collection.JavaConverters._

class ActorGraphTest extends FlatSpec with Matchers {
    
    "actors()" should "complain helpfully about empty responses" in {
        val bridge = new EchoBridge("")
        a [IOException] should be thrownBy {
            bridge.neighbors("provider/screenname")
        }
    }
    
    
    
	"actors()" should "return the actors in a movie" in {
		val bridge = new EchoBridge("""[
	        {
	            "id": "771243352",
	            "title":"Starbuck",
	            "year":2013,
	            "mpaa_rating":"R",
	            "runtime":109,
	            "release_dates":{
	                "theater":"2013-03-22",
	                "dvd":"2013-07-23"
	            },
	            "ratings":{
	                "critics_rating":"Fresh",
	                "critics_score":65,
	                "audience_rating":
	                "Upright","audience_score":80
	            }, 
	            "posters":{
	                "thumbnail":"http://content6.flixster.com/movie/11/16/93/11169352_mob.jpg"
	            }, 
	            "abridged_cast":[
	                {
	                    "name":"Patrick Huard",
	                    "id":"196088490",
	                    "characters":["David Wozniak"]
	                },
	                {
	                    "name":"Julie Le Breton",
	                    "id":"770904316",
	                    "characters":["Valerie"]
	                },
	                {
	                    "name":"Antoine Bertrand",
	                    "id":"770810073",
	                    "characters":["Paul"]
	                }
	            ]
	        }]""")
		val movie_list = bridge.actors("Starbuck")
		movie_list.asScala should be(List("Patrick Huard", "Julie Le Breton", "Antoine Bertrand"))
	}
}