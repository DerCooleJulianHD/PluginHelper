package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.configuration

import de.xcuzimsmart.pluginhelper.code.main.kotlin.plugin.SpigotPlugin
import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.Abstract
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import java.io.File
import java.io.IOException
import java.util.*
import java.util.logging.Level

@Abstract
open class YamlConfigurator(dir: File?, fileName: String?, loadOnInit: Boolean) : Config(dir, fileName, loadOnInit) {
    var config: FileConfiguration? = YamlConfiguration()

    override fun load() {
        if (!file.exists()) return
        if (!this.isYamlFile) throw RuntimeException("file must be correct type.")

        try {
            if (config == null) this.config = YamlConfiguration()

            if (!exists()) save()

            config!!.load(file)
            setLoaded(true)
        } catch (e: InvalidConfigurationException) {
            SpigotPlugin.Companion.getPluginLogger().log(Level.SEVERE, "Unable to load: " + file.getName(), e)
        } catch (e: IOException) {
            SpigotPlugin.Companion.getPluginLogger().log(Level.SEVERE, "Unable to load: " + file.getName(), e)
        }
    }

    // writes an object to a Yaml-Configuration.
    fun write(k: String?, v: Any?) {
        if (!isLoaded()) load()

        try {
            config!!.set(k, v)
            save()
        } catch (e: Exception) {
            SpigotPlugin.Companion.getPluginLogger()
                .log(Level.SEVERE, "Unable to write object to: " + file.getName(), e)
        }
    }

    // returns an Object from YamlConfiguration
    fun read(k: String?): Any? {
        if (!file.exists()) return null
        if (!isLoaded()) load()

        try {
            return config!!.get(k)
        } catch (e: Exception) {
            SpigotPlugin.Companion.getPluginLogger()
                .log(Level.SEVERE, "Unable to read object from: " + file.getName(), e)
        }

        return null
    }

    // saves all the data, this is called recorsivly for you when calling the 'write()' method.
    fun save() {
        try {
            config!!.save(file)
        } catch (e: IOException) {
            SpigotPlugin.Companion.getPluginLogger().log(Level.SEVERE, "Unable to save: " + file.getName(), e)
        }
    }

    // reads a keyed location from section with sub paths.
    fun readLocation(k: String?): Location? {
        if (config!!.get(k) is Location) return readSerializedLocation(k)

        val location = readBlockLocation(k)

        if (isSet(k + ".yaw") && isSet(k + ".pitch")) {
            location.setYaw(readFloat(k + ".yaw"))
            location.setPitch(readFloat(k + ".pitch"))
        }

        return location
    }

    fun readBlockLocation(k: String?): Location {
        val worldName = readString(k + ".world")
        var world = Bukkit.getWorld(worldName)

        if (world == null) world = Bukkit.createWorld(WorldCreator.name(worldName))

        val x = readDouble(k + ".x")
        val y = readDouble(k + ".y")
        val z = readDouble(k + ".z")

        return Location(world, x, y, z)
    }

    fun writeLocation(k: String?, location: Location) {
        writeString(k + ".world", location.getWorld().getName())
        writeDouble(k + ".x", location.getX())
        writeDouble(k + ".y", location.getX())
        writeDouble(k + ".z", location.getX())

        if (location.getYaw().toDouble() == 0.0 && location.getPitch().toDouble() == 0.0) return

        writeFloat(k + ".yaw", location.getYaw())
        writeFloat(k + ".pitch", location.getPitch())
    }

    fun writeBlockLocation(k: String?, block: Block) {
        val location = block.getLocation()

        writeString(k + ".world", block.getWorld().getName())
        writeDouble(k + ".x", location.getX())
        writeDouble(k + ".y", location.getX())
        writeDouble(k + ".z", location.getX())
    }

    // writes a String to YamlConfiguration
    fun writeString(k: String?, v: String?) {
        this.write(k, v)
    }

    // writes an Integer to YamlConfiguration
    fun writeInt(k: String?, v: Int) {
        this.write(k, v)
    }

    // writes a Double to YamlConfiguration
    fun writeDouble(k: String?, v: Double) {
        this.write(k, v)
    }

    // writes a Float to YamlConfiguration
    fun writeFloat(k: String?, v: Float) {
        this.write(k, v)
    }

    // writes a Boolean to YamlConfiguration
    fun writeBoolean(k: String?, v: Boolean) {
        this.write(k, v)
    }

    // writes a Short to YamlConfiguration
    fun writeShort(k: String?, v: Short) {
        this.write(k, v)
    }

    // writes a Long to YamlConfiguration
    fun writeLong(k: String?, v: Long) {
        this.write(k, v)
    }

    // writes a Byte to YamlConfiguration
    fun writeByte(k: String?, v: Byte) {
        this.write(k, v)
    }

    // writes a List to YamlConfiguration
    fun writeList(k: String?, v: MutableList<*>?) {
        this.write(k, v)
    }

    // writes an ItemStack to YamlConfiguration
    fun writeItem(k: String?, v: ItemStack?) {
        this.write(k, v)
    }

    // writes a Color to YamlConfiguration
    fun writeColor(k: String?, v: Color?) {
        this.write(k, v)
    }

    // writes a ChatColor to YamlConfiguration
    fun writeChatColor(k: String?, v: ChatColor) {
        this.writeString(k, v.name)
    }

    // writes a Character to YamlConfiguration
    fun writeCharacter(k: String?, v: Char) {
        this.write(k, v)
    }

    // writes a Location to YamlConfiguration
    fun writeSerializedLocation(k: String?, v: Location?) {
        this.write(k, v)
    }

    // returns a String
    fun readString(k: String?): String {
        return read(k) as String
    }

    // returns an Integer
    fun readInt(k: String?): Int {
        return read(k) as Int
    }

    // returns a Double
    fun readDouble(k: String?): Double {
        return read(k) as Double
    }

    // returns a Float
    fun readFloat(k: String?): Float {
        return read(k) as Float
    }

    // returns a Boolean
    fun readBoolean(k: String?): Boolean {
        return read(k) as Boolean
    }

    // returns a Short
    fun readShort(k: String?): Short {
        return read(k) as Short
    }

    // returns a Long
    fun readLong(k: String?): Long {
        return read(k) as Long
    }

    // returns a Byte
    fun readByte(k: String?): Byte {
        return read(k) as Byte
    }

    // returns a List
    fun readList(k: String?): MutableList<*>? {
        return read(k) as MutableList<*>?
    }

    // returns an ItemStack
    fun readItem(k: String?): ItemStack? {
        return read(k) as ItemStack?
    }

    // returns a Color
    fun readColor(k: String?): Color? {
        return read(k) as Color?
    }

    // returns a ChatColor
    fun readChatColor(k: String?): ChatColor {
        return ChatColor.valueOf(readString(k).uppercase(Locale.getDefault()))
    }

    // returns a Character
    fun readCharacter(k: String?): Char {
        return read(k) as Char
    }

    // returns a Serialized Location from bukkit.
    fun readSerializedLocation(k: String?): Location? {
        return read(k) as Location?
    }

    // returns all Keys
    fun keySet(deep: Boolean): MutableSet<String?>? {
        return config!!.getKeys(deep)
    }

    // returns true when config contains path.
    fun isSet(k: String?): Boolean {
        return config!!.isSet(k)
    }

    val isEmpty: Boolean
        // returns true if the config file is empty, which means config doesn't contain any data.
        get() = keySet(false) != null && keySet(false)!!.isEmpty()

    // returns a section
    fun getConfigurationSection(name: String?): ConfigurationSection? {
        return config!!.getConfigurationSection(name)
    }

    // creates a section
    fun createSection(name: String?) {
        config!!.createSection(name)
    }

    val isYamlFile: Boolean
        // returns true when filr does end with '.yml' or '.yaml'
        get() = hasEnding(file.getName(), ".yml") || hasEnding(file.getName(), ".yaml")
}
