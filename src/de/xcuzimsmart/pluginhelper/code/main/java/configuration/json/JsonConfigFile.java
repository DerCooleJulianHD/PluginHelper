package de.xcuzimsmart.pluginhelper.code.main.java.configuration.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.xcuzimsmart.pluginhelper.code.main.java.configuration.ConfigFile;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.interfaces.MinecraftPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.annotations.Abstract;
import org.apache.commons.lang.Validate;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

@Abstract
@JsonProperties() /* <-- by default */
public class JsonConfigFile extends ConfigFile {  // cannot be final, because of abstract usages.

    final Gson gson;

    final JsonProperties properties = getClass().getDeclaredAnnotation(JsonProperties.class);

    public JsonConfigFile(MinecraftPlugin plugin, File dir, String fileName, boolean loadOnInit) {
        super(plugin, dir, fileName, loadOnInit);
        Validate.notNull(properties, getClass().getName() + " misses JsonProperties Annotation!");

        this.gson = JsonConfigurationBuilder.build(properties);
    }

    // writes an object to a Json-Configuration.
    public final void write(Object o) {
        if (!file.exists())
        if (!isLoaded()) load();

        try {
            final FileWriter writer = new FileWriter(file);

            writer.write(gson.toJson(o));
            writer.close();
        } catch (IOException e) {
            plugin.getPluginLogger().log(Level.SEVERE, "Unable to write object to: " + file.getName(), e);
        }
    }

    @Nullable
    public final Object read(Class<?> classOfT) {
        if (!file.exists()) return null;
        if (!isJsonFile()) return null;
        if (!isLoaded()) return null;

        try {
           final FileReader reader = new FileReader(file);
           final Object o = gson.fromJson(reader, classOfT);
           reader.close();
           return o;
        } catch (IOException e) {
            plugin.getPluginLogger().log(Level.SEVERE, "Unable to read object from: " + file.getName(), e);
        }

        return null;
    }

    @Override
    public final void load() {
        if (!isJsonFile()) throw new RuntimeException("file must be correct type.");

        try {
            if (!exists()) {
                file.createNewFile();
            }

            this.setLoaded(true);
        } catch (IOException e) {
            plugin.getPluginLogger().log(Level.SEVERE, "Unable to load: " + file.getName(), e);
        }
    }

    public final boolean isJsonFile() {
        return hasEnding(file.getName(), ".json");
    }

    private static final class JsonConfigurationBuilder {

        public static Gson build(JsonProperties properties) {
            final GsonBuilder builder = new GsonBuilder();

            if (properties.prettyPrinting()) builder.setPrettyPrinting();
            if (!properties.htmlEscaping()) builder.disableHtmlEscaping();
            if (!properties.innerClazzSerialisation()) builder.disableInnerClassSerialization();

            return builder.create();
        }
    }
}
