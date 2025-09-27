package de.xcuzimsmart.pluginhelper.code.git.branch.main.configuration;

import com.google.gson.Gson;
import org.apache.commons.lang.Validate;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

@JsonProperties() /* <-- by default */
public class JsonConfigurator extends Configurator {  // cannot be final, because of abstract usages.

    final Gson gson;
    final JsonProperties properties = getClass().getDeclaredAnnotation(JsonProperties.class);

    FileWriter writer;
    FileReader reader;

    public JsonConfigurator(File dir, String fileName, boolean replace) {
        super(dir, fileName, ".json", replace);
        Validate.notNull(properties, getClass().getName() + " misses " + JsonProperties.class.getSimpleName() + " Annotation!");

        this.gson = JsonConfigurationBuilder.build(properties);
    }

    public JsonConfigurator(String dir, String fileName, boolean replace) {
        this(new File(dir), fileName, replace);
    }

    // writes an object to a Json-Configuration.
    public void write(Object o) {
        createFiles();

        if (!isJsonFile()) return;  // does check if our file has the .json ending

        try {
            if (!isLoaded()) load(); // loading the file
            this.writer = new FileWriter(file);
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
            this.writer = null;
            this.setLoaded(false);
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
        return hasFileEnding(file.getName(), ".json");
    }
}
