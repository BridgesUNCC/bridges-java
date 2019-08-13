package bridges.cache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleCache extends Cache {
    private String cacheDir;

    @Override
    public boolean inCache(String docName) {
        File file = new File(getFileName(docName));
        return file.exists();
    }

    @Override
    public String getDoc(String docName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(getFileName(docName))));
    }

    @Override
    public void putDoc(String docName, String content) throws IOException {
        Files.write(Paths.get(getFileName(docName)), content.getBytes());
    }

    @Override
    public void removeDoc(String docName) throws IOException {
        Files.delete(Paths.get(getFileName(docName)));
    }

    private String getFileName(String docName) {
        return this.cacheDir + docName;
    }

    public SimpleCache(String cacheDir) {
        this.cacheDir = cacheDir;
        File dir = new File(this.cacheDir);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                System.err.println("Error creating cache directory");
            }
        }
    }

    public SimpleCache () {
        this.cacheDir = "./cache/";
        // a reasonable location on unixes
        String home = System.getenv("HOME");
        if (home == null) {
            // a reasonable location on windows
            home = System.getenv("LOCALAPPDATA");
        }
        if (home != null) {
            this.cacheDir = String.format("%s/.cache/", home);
        }
        File dir = new File(cacheDir);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                System.err.println("Error creating cache directory");
            }
        }
        this.cacheDir += "bridges_data/";
        dir = new File(cacheDir);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                System.err.println("Error creating bridges cache directory");
            }
        }
    }
}
