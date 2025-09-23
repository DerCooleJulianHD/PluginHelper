package de.code.test.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.Validate;

import java.io.*;
import java.util.logging.Level;

public final class JsonConfigurator extends Configurator {

    final Gson gson;
    final JsonProperties properties = getClass().getAnnotation(JsonProperties.class);

    FileWriter writer;
    FileReader reader;

    public JsonConfigurator(File dir, String fileName) {
        super(dir, fileName, ".json");
        Validate.notNull(properties, getClass().getName() + " misses GsonProperties Annotation!");

        final GsonBuilder builder = new GsonBuilder();

        if (!properties.innerClazzSerialisation()) builder.disableInnerClassSerialization();
        if (!properties.htmlEscaping()) builder.disableHtmlEscaping();
        if (properties.prettyPrinting()) builder.setPrettyPrinting();

        this.gson = builder.create();
    }

    public JsonConfigurator(String dir, String fileName) {
        this(new File(dir), fileName);
    }

    public static JsonConfigurator config(File file) {
        return new JsonConfigurator(file.getParentFile(), file.getName());
    }

    // writes an object to a Json-Configuration.
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
