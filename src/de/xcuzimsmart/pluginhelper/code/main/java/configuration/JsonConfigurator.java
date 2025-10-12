package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.MCSpigotPlugin;
import org.apache.commons.lang.Validate;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

@FilenameEnding(endings = {".json"})
@JsonProperties() /* <-- by default */
public class JsonConfigurator extends Configurator {  // cannot be final, because of abstract usages.

    final Gson gson;

    final JsonProperties properties = getClass().getDeclaredAnnotation(JsonProperties.class);

    public JsonConfigurator(MCSpigotPlugin plugin, File dir, String fileName) {
        super(plugin, dir, fileName);
        Validate.notNull(properties, getClass().getName() + " misses JsonProperties Annotation!");

        this.gson = JsonConfigurationBuilder.build(properties);
    }

    public JsonConfigurator(MCSpigotPlugin plugin, String dir, String fileName) {
        this(plugin, new File(dir), fileName);
    }

    // writes an object to a Json-Configuration.
    public void write(Object o) {
        createFiles();

        try {
            final FileWriter writer = new FileWriter(file);

            writer.write(gson.toJson(o));
            writer.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to write object to: " + file.getName(), e);
        }
    }

    @Nullable
    public Object read(Class<?> classOfT) {
        if (!file.exists()) return null;

        try {
           final FileReader reader = new FileReader(file);
           final Object o = gson.fromJson(reader, classOfT);
           reader.close();
           return o;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to read object from: " + file.getName(), e);
        }

        return null;
    }

    public static final class JsonConfigurationBuilder {

        public static Gson build(JsonProperties properties) {
            final GsonBuilder builder = new GsonBuilder();

            if (properties.prettyPrinting()) builder.setPrettyPrinting();
            if (!properties.htmlEscaping()) builder.disableHtmlEscaping();
            if (!properties.innerClazzSerialisation()) builder.disableInnerClassSerialization();

            return builder.create();
        }
    }
}
