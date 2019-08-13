package bridges.cache;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LRUCache extends Cache {
    int maxCacheSize;
    ArrayList<String> lru = new ArrayList<String>();
    SimpleCache cache;

    public LRUCache(String cacheDir, int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
        this.cache = new SimpleCache(cacheDir);
    }

    public LRUCache(String cacheDir) {
        this(cacheDir, 30);
    }

    public LRUCache(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
        cache = new SimpleCache();
    }

    public LRUCache() {
        this(30);
    }

    @Override
    public boolean inCache(String docName) {
        return cache.inCache(docName);
    }

    @Override
    public String getDoc(String docName) throws IOException {
        String content = null;
        getLRU();
        content = cache.getDoc(docName);
        updateLRU(docName);
        saveLRU();

        return content;
    }

    @Override
    public void putDoc(String docName, String content) throws IOException {
        getLRU();
        cache.putDoc(docName, content);
        updateLRU(docName);

        if (lru.size() > maxCacheSize) {
            cache.removeDoc(lru.get(lru.size() - 1));
            lru.remove(lru.size() - 1);
        }
        saveLRU();
    }

    @Override
    void removeDoc(String docName) throws IOException {
        getLRU();
        cache.removeDoc(docName);
        lru.remove(docName);
        saveLRU();
    }

    private void getLRU() throws IOException {
        lru.clear();
        if (cache.inCache("lru")) {
            String lruString = cache.getDoc("lru");
            // push all entries to lru
            lru.addAll(Arrays.asList(lruString.split(",")));
        }
    }

    private void updateLRU(String docName) {
        //remove doc from lru and push to front
        lru.remove(docName);
        lru.add(0, docName);
    }

    private void saveLRU() throws IOException {
        StringBuilder out = null;
        boolean first = true;
        for (String entry : lru) {
            if (first) {
                out = new StringBuilder(entry);
                first = false;
            } else {
                out.append(",").append(entry);
            }
        }
        if (out != null)
            cache.putDoc("lru", out.toString());
    }
}
