package me.xcuzimsmart.utils.storages;

import me.xcuzimsmart.utils.file.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;

public class YamlStorage {
    public final String ending = ".yaml";

    private final File file;
    private YamlConfiguration config;

    public YamlStorage(File file) {
        file.getName().replaceAll(".yml", ending);
        if (!file.exists()) FileManager.createFile(file);
        this.file = file;
    }

    public YamlStorage(File destination, String fileName) {
        fileName.replaceAll(".yml", ending);
        this.file = new File(destination, (fileName.contains(ending) ? fileName : fileName  + ending));
        if (!file.exists()) FileManager.createFile(file);
    }
	

    public void set(String path, Object o) {
        this.initializeConfig();
        config.set(path, o);
    }


    public Object parse(String path) {
        this.initializeConfig();
        return config.get(path);
    }
	

    public void save() {
        try {
            config.save(file);
            setConfig(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	

    private void initializeConfig() {
        if (config == null)
            setConfig(YamlConfiguration.loadConfiguration(file));
    }
	

    private void setConfig(YamlConfiguration yamlConfiguration) {
        this.config = yamlConfiguration;
    }


    public File getFile() {
        return file;
    }
	

    public YamlConfiguration configuration() {
        return config;
    }


    public static YamlStorage getYamlStorage(File file) {
        final YamlStorage storage = new YamlStorage(file);
        storage.initializeConfig();
        return storage;
    }
}
