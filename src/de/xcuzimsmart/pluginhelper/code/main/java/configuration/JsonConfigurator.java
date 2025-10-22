package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Abstract;
import org.apache.commons.lang.Validate;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

@Abstract
@JsonProperties() /* <-- by default */
public class JsonConfigurator extends Configurator {  // cannot be final, because of abstract usages.

    final Gson gson;

    final JsonProperties properties = getClass().getDeclaredAnnotation(JsonProperties.class);

    public JsonConfigurator(File dir, String fileName, boolean loadOnInit) {
        super(dir, fileName, loadOnInit);
        Validate.notNull(properties, getClass().getName() + " misses JsonProperties Annotation!");

        this.gson = JsonConfigurationBuilder.build(properties);
    }

    // writes an object to a Json-Configuration.
    public final void write(Object o) {
        if (!isLoaded()) return;
        if (!isJsonFile()) return;

        try {
            final FileWriter writer = new FileWriter(file);

            writer.write(gson.toJson(o));
            writer.close();
        } catch (IOException e) {
            SpigotPlugin.getPluginLogger().log(Level.SEVERE, "Unable to write object to: " + file.getName(), e);
        }
    }

    @Nullable
    public final Object read(Class<?> classOfT) {
        if (!isLoaded()) return null;
        if (!file.exists()) return null;

        if (!isJsonFile()) return null;

        try {
           final FileReader reader = new FileReader(file);
           final Object o = gson.fromJson(reader, classOfT);
           reader.close();
           return o;
        } catch (IOException e) {
            SpigotPlugin.getPluginLogger().log(Level.SEVERE, "Unable to read object from: " + file.getName(), e);
        }

        return null;
    }

    @Override
    public final void load() {
        if (!isJsonFile()) throw new RuntimeException("file must be corect type.");

        try {
            if (!exists()) {
                file.createNewFile();
            }

            this.setLoaded(true);
        } catch (IOException e) {
            SpigotPlugin.getPluginLogger().log(Level.SEVERE, "Unable to load: " + file.getName(), e);
        }
    }

    public final boolean isJsonFile() {
        return hasEnding(file.getName(), ".json");
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
