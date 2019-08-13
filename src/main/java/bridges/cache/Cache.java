package bridges.cache;

import java.io.IOException;

abstract class Cache {
    abstract boolean inCache(String docName);
    abstract String getDoc(String docName) throws IOException;
    abstract void putDoc(String docName, String content) throws IOException;
    abstract void removeDoc(String docName) throws IOException;
}
