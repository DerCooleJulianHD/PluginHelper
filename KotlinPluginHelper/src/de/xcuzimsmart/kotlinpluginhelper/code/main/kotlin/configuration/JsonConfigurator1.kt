package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.configuration

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import de.xcuzimsmart.pluginhelper.code.main.kotlin.plugin.SpigotPlugin
import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.Abstract
import org.apache.commons.lang.Validate
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.util.logging.Level

@Abstract
@JsonProperties /* <-- by default */
class JsonConfigurator(dir: File?, fileName: String?, loadOnInit: Boolean) : Config(dir, fileName, loadOnInit) {
    // cannot be final, because of abstract usages.
    val gson: Gson

    val properties: JsonProperties? = javaClass.getDeclaredAnnotation<JsonProperties?>(JsonProperties::class.java)

    init {
        Validate.notNull(properties, javaClass.getName() + " misses JsonProperties Annotation!")

        this.gson = JsonConfigurationBuilder.build(properties!!)
    }

    // writes an object to a Json-Configuration.
    fun write(o: Any?) {
        if (!file.exists()) if (!isLoaded()) load()

        try {
            val writer = FileWriter(file)

            writer.write(gson.toJson(o))
            writer.close()
        } catch (e: IOException) {
            SpigotPlugin.Companion.getPluginLogger()
                .log(Level.SEVERE, "Unable to write object to: " + file.getName(), e)
        }
    }

    fun read(classOfT: Class<*>): Any? {
        if (!file.exists()) return null
        if (!this.isJsonFile) return null
        if (!isLoaded()) return null

        try {
            val reader = FileReader(file)
            val o: Any? = gson.fromJson(reader, classOfT)
            reader.close()
            return o
        } catch (e: IOException) {
            SpigotPlugin.Companion.getPluginLogger()
                .log(Level.SEVERE, "Unable to read object from: " + file.getName(), e)
        }

        return null
    }

    override fun load() {
        if (!this.isJsonFile) throw RuntimeException("file must be correct type.")

        try {
            if (!exists()) {
                file.createNewFile()
            }

            this.setLoaded(true)
        } catch (e: IOException) {
            SpigotPlugin.Companion.getPluginLogger().log(Level.SEVERE, "Unable to load: " + file.getName(), e)
        }
    }

    val isJsonFile: Boolean
        get() = hasEnding(file.getName(), ".json")

    private object JsonConfigurationBuilder {
        fun build(properties: JsonProperties): Gson {
            val builder = GsonBuilder()

            if (properties.prettyPrinting) builder.setPrettyPrinting()
            if (!properties.htmlEscaping) builder.disableHtmlEscaping()
            if (!properties.innerClazzSerialisation) builder.disableInnerClassSerialization()

            return builder.create()
        }
    }
}
