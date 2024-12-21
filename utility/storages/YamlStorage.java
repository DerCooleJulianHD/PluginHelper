package me.xcuzimsmart.utils.storages;

import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;

public class YamlStorage {
    private final File file;
    private YamlConfiguration config;

    public YamlStorage(File file) {
		this.file = file;
		this.file.getName().replaceAll(".yml", ".yaml")
    }

    public YamlStorage(File destination, String fileName) {
        String ending = ".yaml";
        if (fileName.contains(ending)) {
            this.file = new File(destination, fileName.replaceAll(".yml", ending));
        } else {
            this.file = new File(destination, fileName + ending);
        }
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
	

    public YamlConfiguration getConfiguration() {
        return config;
    }


    public static YamlConfiguration getStorageConfiguration(File file) {
        final YamlStorage storage = new YamlStorage(file);
        storage.initializeConfig();
        return storage.getConfiguration();
    }
}
