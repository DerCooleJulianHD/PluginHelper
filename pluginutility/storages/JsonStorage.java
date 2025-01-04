package pluginutility.storages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pluginutility.file.FileManager;
import java.io.*;

public class JsonStorage {
    // this is the filename ending the file will have
    public final String ending = ".json";
    // creating a gson builder to save objects nice formated
    private final Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create();

    // this is the file which will be edited
    private final File file;
    private FileWriter writer;
    private FileReader reader;

    public JsonStorage(File file) {
        if (!file.exists()) FileManager.createFile(file);
        this.file = file;
    }

    public JsonStorage(File destination, String fileName) {
        this.file = new File(destination, (fileName.contains(ending) ? fileName : fileName  + ending));
        if (!file.exists()) FileManager.createFile(file);
    }

    // writes an object to file
    public void set(Object o) {
        String s = gson.toJson(o);
        try {
            this.writer.write(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // loads the object from file
    public Object parseTo(Class<?> objectClazz) {
        try {
            Object o = gson.fromJson(reader, objectClazz); // parsing object from the json file
            this.reader.close();
            return o;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // saves file data
    public void save() {
        try {
            this.writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // loads the file data
    private void load() {
        try {
            this.reader = new FileReader(file);
            this.writer = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile() {
        return file;
    }

    public FileWriter getWriter() {
        return writer;
    }

    public FileReader getReader() {
        return reader;
    }

    public Gson getGson() {
        return gson;
    }
}
