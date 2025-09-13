package de.code.test.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.logging.Level;

public class JsonConfigurator extends Configurator {

    final Gson gson;

    FileWriter writer;
    FileReader reader;

    public JsonConfigurator(File file) {
        super(file);
        this.gson = new Gson();
    }

    public JsonConfigurator(File dir, String fileName, boolean innerClazzSerialisation, boolean htmlEscaping, boolean prettyPrinting) {
        super(dir, fileName, ".json");
        GsonBuilder builder = new GsonBuilder();
        if (!innerClazzSerialisation) builder.disableInnerClassSerialization();
        if (!htmlEscaping) builder.disableHtmlEscaping();
        if (prettyPrinting) builder.setPrettyPrinting();
        this.gson = builder.create();
    }

    public JsonConfigurator(String dir, String fileName, boolean innerClazzSerialisation, boolean htmlEscaping, boolean prettyPrinting) {
        this(new File(dir), fileName, innerClazzSerialisation, htmlEscaping, prettyPrinting);
    }

    public static JsonConfigurator config(File file, boolean innerClazzSerialisation, boolean htmlEscaping, boolean prettyPrinting) {
        return new JsonConfigurator(file.getParentFile(), file.getName(), innerClazzSerialisation, htmlEscaping, prettyPrinting);
    }

    // writes an object to a Yaml-Configuration.
    public void write(Object o) {
        createFiles();

        if (!isJsonFile()) return;  // does check if our file has the .json ending

        try {
            if (!isLoaded()) load(); // loading the file
            writer.write(gson.toJson(o));
            save();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public Object read(Class<?> classOfT) {
        if (!file.exists()) return null;
        if (!isJsonFile()) return null; // does check if our file has the .json ending

        if (!isLoaded()) load(); // loading the file;

        return gson.fromJson(reader, classOfT);
    }

    @Override
    public void save() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void load() {
        try {
            this.reader = new FileReader(file);
            this.setLoaded(true);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public boolean isJsonFile() {
        return hasFileEnding(".json");
    }
}
