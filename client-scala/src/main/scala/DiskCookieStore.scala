package bridges
import org.apache.http.impl.client._
import org.apache.http.cookie._
import java.io._
import java.util.prefs.Preferences

/** Provides cookie persistence via preferences.
    Preferences are limited to 8K. Watch cookie size. **/
class DiskCookieStore() extends BasicCookieStore {
    val prefs = Preferences.userRoot()
    load()
    
    /** Load Session Cookies from user preferences */
    def load() {
        try {
            val bytes = prefs.getByteArray("cookies", new Array[Byte](1))
            val bais = new ByteArrayInputStream(bytes)
            val ois = new ObjectInputStream(bais)
            super.clear()
            addCookies( ois.readObject().asInstanceOf[Array[Cookie]])
            //cookies =//
            ois.close()
        } catch {
        case e: EOFException => None
            // No cookies: no problem
        }
        
    }
    
    /** Save Session Cookies to user preferences */
    def save() {
        val cookies = getCookies()
        val baos = new ByteArrayOutputStream()
        val oos = new ObjectOutputStream(baos)
        oos.writeObject(getCookies())
        oos.close()
        val bytes = baos.toByteArray()
        println("Cookies take: " + bytes.length)
        prefs.putByteArray("cookies", bytes)
    }
    
    override def addCookie(cooky: Cookie) {
        super.addCookie(cooky)
        save()
    }
}