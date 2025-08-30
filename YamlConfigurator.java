package code.config.yaml;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YamlConfigurator {

    static final Logger logger = Logger.getLogger(YamlConfigurator.class.getName());

    final File dir, file;
    YamlConfiguration c = new YamlConfiguration();

    boolean loaded = false;

    public YamlConfigurator(File file) {
        this.dir = null;
        this.file = file;
    }

    public YamlConfigurator(File dir, String fileName) {
        this.dir = dir;
        this.file = new File(dir, hasYamlEnding(fileName) ? fileName : fileName + ".yml");
    }

    public YamlConfigurator(String dir, String fileName) {
        this.dir = new File(dir);
        this.file = new File(dir, hasYamlEnding(fileName) ? fileName : fileName + ".yml");
    }

    // writes an object to a Yaml-Configuration.
    public void write(String k, Object v) {
        createFiles();

        if (!isYamlFile(file)) return; // does check if our file has the .yml ending

        try {
            if (!isLoaded()) load(); // loading the file
            c.set(k, v);
            save(false);
        }catch (IOException | InvalidConfigurationException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public void write(ConfigurationSection section, String k, Object v) {
        createFiles();

        if (!isYamlFile(file)) return; // does check if our file has the .yml ending

        try {
            if (!isLoaded()) load(); // loading the file
            // writes the object as value(v) on a configuration section with specific key(k)
            if (section == null) return;

            section.set(k, v);
            save(false);
        } catch (IOException | InvalidConfigurationException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public void write(String sectionName, String k, Object v) {
        ConfigurationSection section = c.getConfigurationSection(sectionName);
        if (section == null) section = c.createSection(sectionName);
        this.write(section, k, v);
    }

    // reads an object from a YamlConfiguration from a specific key(k).
    public Object read(String k) {
        if (!file.exists()) return null;
        if (!isYamlFile(file)) return null; // does check if our file has the .yml ending

        try {
            if (!isLoaded()) load(); // loading the file;
        } catch (IOException | InvalidConfigurationException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }

        return c.get(k);
    }

    public Object read(ConfigurationSection section, String k) {
        if (!file.exists()) return null;

        if (!isYamlFile(file)) return null; // does check if our file has the .yml ending
        try {
            if (!isLoaded()) load(); // loading the file;
            // reads the object as value(v) on a configuration section with specific key(k)
            if (section == null) return null;
        } catch (IOException | InvalidConfigurationException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }

        return section.get(k);
    }

    public Object read(String sectionName, String k) {
        ConfigurationSection section = c.getConfigurationSection(sectionName);
        if (section == null) section = c.createSection(sectionName);

        return read(section, k);
    }

    public int readInt(String k) {
        return (int) read(k);
    }

    public int readInt(ConfigurationSection section, String k) {
        return (int) read(section, k);
    }

    public int readInt(String section, String k) {
        return (int) read(section, k);
    }

    public String readString(String k) {
        return read(k).toString();
    }

    public String readString(ConfigurationSection section, String k) {
        return read(section, k).toString();
    }

    public String readString(String section, String k) {
        return read(section, k).toString();
    }

    public boolean readBoolean(String k) {
        return (boolean) read(k);
    }

    public boolean readBoolean(ConfigurationSection section, String k) {
        return (boolean) read(section, k);
    }

    public boolean readBoolean(String section, String k) {
        return (boolean) read(section, k);
    }

    public List<?> readList(String k) {
        return (List<?>) read(k);
    }

    public List<?> readList(ConfigurationSection section, String k) {
        return (List<?>) read(section, k);
    }

    public List<?> readList(String section, String k) {
        return (List<?>) read(section, k);
    }

    public double readDouble(String k) {
        return (double) read(k);
    }

    public double readDouble(ConfigurationSection section, String k) {
        return (double) read(section, k);
    }

    public double readDouble(String section, String k) {
        return (double) read(section, k);
    }

    public float readFloat(String k) {
        return (float) read(k);
    }

    public float readFloat(ConfigurationSection section, String k) {
        return (float) read(section, k);
    }

    public float readFloat(String section, String k) {
        return (float) read(section, k);
    }

    public long readLong(String k) {
        return (long) read(k);
    }

    public long readLong(ConfigurationSection section, String k) {
        return (long) read(section, k);
    }

    public long readLong(String section, String k) {
        return (long) read(section, k);
    }

    public short readShort(String k) {
        return (short) read(k);
    }

    public short readShort(ConfigurationSection section, String k) {
        return (short) read(section, k);
    }

    public short readShort(String section, String k) {
        return (short) read(section, k);
    }

    public byte readByte(String k) {
        return (byte) read(k);
    }

    public byte readByte(ConfigurationSection section, String k) {
        return (byte) read(section, k);
    }

    public byte readByte(String section, String k) {
        return (byte) read(section, k);
    }

    public Set<String> readKeys(String k) {
        return c.getKeys(false);
    }

    public Set<String> readKeys(ConfigurationSection section) {
        return section.getKeys(false);
    }

    public boolean isSet(String k) {
        return c.isSet(k);
    }

    public ItemStack readItem(String k) {
        return (ItemStack) read(k);
    }

    public ItemStack readItem(ConfigurationSection section, String k) {
        return (ItemStack) read(section, k);
    }

    public ItemStack readItem(String section, String k) {
        return (ItemStack) read(section, k);
    }

    void load() throws IOException, InvalidConfigurationException {
        c.load(file);
        setLoaded(true);
    }

    public void unload() {
        try {
            save(true);
            this.c = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(boolean unload) throws IOException {
        c.save(file);
        if (unload) setLoaded(false);
    }

    public void createSection(String s) {
        c.createSection(s);
    }

    public void getSection(String s) {
        c.getConfigurationSection(s);
    }

    public boolean isLoaded() {
        return loaded;
    }

    void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    boolean isYamlFile(File file) {
        return hasYamlEnding(file.getName());
    }

    boolean hasYamlEnding(String fileName) {
        return fileName.endsWith(".yml") || fileName.endsWith(".yaml");
    }

    public File getFile() {
        return file;
    }

    void createFiles() {
        if (dir != null) if (!dir.exists()) dir.mkdirs();

        try {
             file.createNewFile();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }
}
