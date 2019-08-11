package bridges.connect;

import java.io.File;
import java.lang.Exception;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LRUCache {
    int maxCacheSize;
    ArrayList<String> lru = new ArrayList<String>();

    public LRUCache(int maxCacheSize){
        this.maxCacheSize = maxCacheSize;

        File cache_dir = new File("./cache");
        if (!cache_dir.exists()) {
            if (!cache_dir.mkdir()) {
                System.err.println("Error creating cache directory");
            }
        }
    }

    public boolean inCache(String hash){
        String file_name = "./cache/" + hash;
        File file = new File(file_name);
        return file.exists();
    }

    public String get(String hash) {
        String file_name = "./cache/" + hash;
        File file = new File(file_name);
        try {
            if (file.exists()) {
                String data = new String(Files.readAllBytes(file.toPath()));
                loadLRU();
                updateLRU(hash);
                return data;
            }
        } catch (Exception e) {}
        return null;
    }

    public void put(String hash, String data) {
        try {
            Files.write(Paths.get("./cache/" + hash), data.getBytes());
            loadLRU();
            updateLRU(hash);
            saveLRU();
        } catch (Exception e) {}
    }

    private void updateLRU(String hash){
        //Updates the LRU
        try {
            lru.remove(new String(hash));
        } catch (Exception e) {
            //Its fine if its not found in LRU
        }
        lru.add(0, hash); // pushes the hash to the front of LRU

        //checks lru size
        while (lru.size() >= maxCacheSize + 1){  //Purges old maps from the cache and lru
            String dir = "./cache";
            File old_map = new File(dir);
            for (File file : old_map.listFiles()) {
                if (file.getName().replaceAll("[^a-zA-Z0-9]", "").equals(lru.get(lru.size()-1).replaceAll("[^a-zA-Z0-9]", ""))) { //regexs out any non alphanumeric character
                    file.delete();
                    lru.remove(lru.size()-1);
                    break;
                }
            }
        }
    }

    private void saveLRU() {
        String output = "";
        int count = 1;
        for (String y : lru) {
            output = output + y.replaceAll("[^a-zA-Z0-9]", ""); //regexs out any non alphanumeric character
            if (lru.size() != count){
                output = output + ",";
            }
            count++;
        }
        try {
            Files.write(Paths.get("./cache/lru.txt"), output.getBytes());
        } catch (Exception e) {
            System.err.println("Error saving LRU array");
        }
    }

    private void loadLRU() {
        File lru_file = new File("./cache/lru.txt");
        try {
            if (lru_file.exists()) {
                String lruImport = new String(Files.readAllBytes(lru_file.toPath()));
                String[] x = lruImport.split(",");
                for (String val: x){
                    lru.add(val);
                }
            }
        } catch (Exception e) {}
    }
}
