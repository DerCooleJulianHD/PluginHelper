package me.xcuzimsmart.utils.storages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public final class JsonStorage {
    private final Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create();

    private final File file;
    private FileWriter writer;
    private FileReader reader;

    public JsonStorage(File file) {
        this.file = file;
    }

    public JsonStorage(File destination, String fileName) {
        String ending = ".json";
        if (fileName.contains(ending)) {
            this.file = new File(destination, fileName);
        } else {
            this.file = new File(destination, fileName + ending);
        }
    }

    public void set(Object o) {
        this.initializeWriter();
        String s = gson.toJson(o);
        try {
            this.writer.write(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object parse(Class<?> objectClazz) {
        this.initializeReader();
        try {
            Object o = gson.fromJson(reader, objectClazz);
            this.reader.close();
            return o;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        try {
            this.writer.flush();
            this.writer.close();
            this.reader.close();
            setWriter(null);
            setReader(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeWriter() {
        if (writer == null) {
            try {
                setWriter(new FileWriter(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initializeReader() {
        if (reader == null) {
            try {
                setReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setReader(FileReader fileReader) {
        this.reader = fileReader;
    }

    private void setWriter(FileWriter fileWriter) {
        this.writer = fileWriter;
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

    public static JsonStorage getStorage(File file) {
        final YamlStorage storage = new JsonStorage(file);
        storage.initializeReader()
        storage.initializeWriter();
        return storage;
    }
}
