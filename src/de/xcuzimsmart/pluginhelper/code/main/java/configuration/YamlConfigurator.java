package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.AbstractUsage;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

@AbstractUsage
public class YamlConfigurator extends Configurator {

    FileConfiguration config = new YamlConfiguration();

    public YamlConfigurator(SpigotPlugin plugin, File dir, String fileName) {
        super(plugin, dir, fileName);
    }

    public YamlConfigurator(SpigotPlugin plugin, String dir, String fileName) {
        this(plugin, new File(dir), fileName);
    }

    public boolean isSet(String k) {
        return config.isSet(k);
    }

    public ConfigurationSection getConfigurationSection(String name) {
        return config.getConfigurationSection(name);
    }

    public void createSection(String name) {
        config.createSection(name);
    }

    // writes an object to a Yaml-Configuration.
    public void write(String k, Object v) {
        if (!isYamlFile()) return;
        if (!isLoaded()) return;

        try {
            config.set(k, v);
            save();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to write object to: " + file.getName(), e);
        }
    }

    // writes a String to YamlConfiguration
    public void writeString(String k, String v) {
        this.write(k, v);
    }

    // writes an Integer to YamlConfiguration
    public void writeInt(String k, int v) {
        this.write(k, v);
    }

    // writes a Double to YamlConfiguration
    public void writeDouble(String k, double v) {
        this.write(k, v);
    }

    // writes a Float to YamlConfiguration
    public void writeFloat(String k, float v) {
        this.write(k, v);
    }

    // writes a Boolean to YamlConfiguration
    public void writeBoolean(String k, boolean v) {
        this.write(k, v);
    }

    // writes a Short to YamlConfiguration
    public void writeShort(String k, short v) {
        this.write(k, v);
    }

    // writes a Long to YamlConfiguration
    public void writeLong(String k, long v) {
        this.write(k, v);
    }

    // writes a Byte to YamlConfiguration
    public void writeByte(String k, byte v) {
        this.write(k, v);
    }

    // writes a List to YamlConfiguration
    public void writeList(String k, List<?> v) {
        this.write(k, v);
    }

    // writes an ItemStack to YamlConfiguration
    public void writeItem(String k, ItemStack v) {
        this.write(k, v);
    }

    // writes a Color to YamlConfiguration
    public void writeColor(String k, Color v) {
        this.write(k, v);
    }

    // writes a ChatColor to YamlConfiguration
    public void writeChatColor(String k, ChatColor v) {
        this.writeString(k, v.name());
    }

    // writes a Character to YamlConfiguration
    public void writeCharacter(String k, char v) {
        this.write(k, v);
    }

    // writes a Location to YamlConfiguration
    public void writeLocation(String k, Location v) {
        this.write(k, v);
    }

    // returns an Object from YamlConfiguration
    public Object read(String k) {
        if (!isLoaded()) return null;
        if (!file.exists()) return null;

        if (!isYamlFile()) return null;

        try {
            return config.get(k);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to read object from: " + file.getName(), e);
        }

        return null;
    }

    // returns a String
    public String readString(String k) {
        return (String) read(k);
    }

    // returns an Integer
    public int readInt(String k) {
        return (int) read(k);
    }

    // returns a Double
    public double readDouble(String k) {
        return (double) read(k);
    }

    // returns a Float
    public float readFloat(String k) {
        return (float) read(k);
    }

    // returns a Boolean
    public boolean readBoolean(String k) {
        return (boolean) read(k);
    }

    // returns a Short
    public short readShort(String k) {
        return (short) read(k);
    }

    // returns a Long
    public long readLong(String k) {
        return (long) read(k);
    }

    // returns a Byte
    public byte readByte(String k) {
        return (byte) read(k);
    }

    // returns a List
    public List<?> readList(String k) {
        return (List<?>) read(k);
    }

    // returns all Keys
    public Set<String> keySet(boolean deep) {
        return config.getKeys(deep);
    }

    // returns an ItemStack
    public ItemStack readItem(String k) {
        return (ItemStack) read(k);
    }

    // returns a Color
    public Color readColor(String k) {
        return (Color) read(k);
    }

    // returns a ChatColor
    public ChatColor readChatColor(String k) {
        return ChatColor.valueOf(readString(k).toUpperCase());
    }

    // returns a Character
    public char readCharacter(String k) {
        return (char) read(k);
    }

    // returns a Location
    public Location readLocation(String k) {
        return (Location) read(k);
    }

    public boolean isEmpty() {
        return keySet(false) != null && keySet(false).isEmpty();
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to save: " + file.getName(), e);
        }
    }

    public boolean isYamlFile() {
        return hasEnding(file.getName(), ".yml") || hasEnding(file.getName(), ".yaml");
    }

    @Override
    public void load() {
        if (!isYamlFile()) throw new RuntimeException("file must be corect type.");

        try {
            if (config == null) this.config = new YamlConfiguration();

            if (!exists()) save();

            config.load(file);
            this.setLoaded(true);
        } catch (InvalidConfigurationException | IOException e) {
            logger.log(Level.SEVERE, "Unable to load: " + file.getName(), e);
        }
    }
}
