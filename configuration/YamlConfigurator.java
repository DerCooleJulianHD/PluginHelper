package de.code.test.configuration;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class YamlConfigurator extends Configurator {

    YamlConfiguration c = new YamlConfiguration();

    public YamlConfigurator(File file) {
        super(file);
    }

    public YamlConfigurator(File dir, String fileName) {
        super(dir, fileName, new String[]{".yml", ".yaml"});
    }

    public YamlConfigurator(String dir, String fileName) {
        this(new File(dir), fileName);
    }

    public static YamlConfigurator config(File file) {
        return new YamlConfigurator(file.getParentFile(), file.getName());
    }

    // writes an object to a Yaml-Configuration.
    public void write(String k, Object v) {
        createFiles();

        if (!isYamlFile()) return; // does check if our file has the .yml ending

        try {
            if (!isLoaded()) load(); // loading the file
            c.set(k, v);
            save();
        }catch (Exception e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public void write(ConfigurationSection s, String k, Object v) {
        createFiles();

        if (!isYamlFile()) return; // does check if our file has the .yml ending

        try {
            if (!isLoaded()) load(); // loading the file
            // writes the object as value(v) on a configuration section with specific key(k)
            if (s == null) return;

            s.set(k, v);
            save();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public void write(String s, String k, Object v) {
        ConfigurationSection section = c.getConfigurationSection(s);
        if (section == null) section = c.createSection(s);
        this.write(section, k, v);
    }

    // reads an object from a YamlConfiguration from a specific key(k).
    public Object read(String k) {
        if (!file.exists()) return null;
        if (!isYamlFile()) return null; // does check if our file has the .yml ending

        if (!isLoaded()) load(); // loading the file;

        return c.get(k);
    }

    public Object read(ConfigurationSection s, String k) {
        if (!file.exists()) return null;

        if (!isYamlFile()) return null; // does check if our file has the .yml ending
        if (!isLoaded()) load(); // loading the file;
        // reads the object as value(v) on a configuration section with specific key(k)
        if (s == null) return null;

        return s.get(k);
    }

    public Object read(String s, String k) {
        ConfigurationSection section = c.getConfigurationSection(s);
        if (section == null) section = c.createSection(s);

        return read(section, k);
    }

    public int readInt(String k) {
        return (int) read(k);
    }

    public int readInt(ConfigurationSection s, String k) {
        return (int) read(s, k);
    }

    public int readInt(String s, String k) {
        return (int) read(s, k);
    }

    public String readString(String k) {
        return read(k).toString();
    }

    public String readString(ConfigurationSection s, String k) {
        return read(s, k).toString();
    }

    public String readString(String s, String k) {
        return read(s, k).toString();
    }

    public boolean readBoolean(String k) {
        return (boolean) read(k);
    }

    public boolean readBoolean(ConfigurationSection s, String k) {
        return (boolean) read(s, k);
    }

    public boolean readBoolean(String s, String k) {
        return (boolean) read(s, k);
    }

    public List<?> readList(String k) {
        return (List<?>) read(k);
    }

    public List<?> readList(ConfigurationSection s, String k) {
        return (List<?>) read(s, k);
    }

    public List<?> readList(String s, String k) {
        return (List<?>) read(s, k);
    }

    public double readDouble(String k) {
        return (double) read(k);
    }

    public double readDouble(ConfigurationSection s, String k) {
        return (double) read(s, k);
    }

    public double readDouble(String s, String k) {
        return (double) read(s, k);
    }

    public float readFloat(String k) {
        return (float) read(k);
    }

    public float readFloat(ConfigurationSection s, String k) {
        return (float) read(s, k);
    }

    public float readFloat(String s, String k) {
        return (float) read(s, k);
    }

    public long readLong(String k) {
        return (long) read(k);
    }

    public long readLong(ConfigurationSection s, String k) {
        return (long) read(s, k);
    }

    public long readLong(String s, String k) {
        return (long) read(s, k);
    }

    public short readShort(String k) {
        return (short) read(k);
    }

    public short readShort(ConfigurationSection s, String k) {
        return (short) read(s, k);
    }

    public short readShort(String s, String k) {
        return (short) read(s, k);
    }

    public byte readByte(String k) {
        return (byte) read(k);
    }

    public byte readByte(ConfigurationSection s, String k) {
        return (byte) read(s, k);
    }

    public byte readByte(String s, String k) {
        return (byte) read(s, k);
    }

    public Set<String> keys() {
        return c.getKeys(false);
    }

    public Set<String> keys(ConfigurationSection s) {
        return s.getKeys(false);
    }

    public boolean isSet(String k) {
        return c.isSet(k);
    }

    public ItemStack readItem(String k) {
        return (ItemStack) read(k);
    }

    public ItemStack readItem(ConfigurationSection s, String k) {
        return (ItemStack) read(s, k);
    }

    public ItemStack readItem(String s, String k) {
        return (ItemStack) read(s, k);
    }

    @Override
    public void load() {
        try {
            c.load(file);
            setLoaded(true);
        } catch (IOException | InvalidConfigurationException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void save() {
        try {
            c.save(file);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public void createSection(String s) {
        c.createSection(s);
    }

    public ConfigurationSection getSection(String s) {
        return c.getConfigurationSection(s);
    }

    public boolean isYamlFile() {
        return hasFileEnding(endings);
    }
}
