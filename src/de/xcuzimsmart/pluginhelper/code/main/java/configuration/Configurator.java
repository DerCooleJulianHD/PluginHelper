package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Configurator implements Config {

    public static final Logger logger = Logger.getLogger(Configurator.class.getName());

    protected final SpigotPlugin plugin;

    protected final File dir, file;

    boolean loaded = false;

    public Configurator(SpigotPlugin plugin, File dir, String fileName) {
        this.plugin = plugin;
        this.dir = dir;
        this.file = new File(dir, fileName);
    }

    public Configurator(SpigotPlugin plugin, String dir, String fileName) {
        this(plugin, new File(dir), fileName);
    }

    @Override
    public void createFiles() {
        plugin.createDataFolder(dir);

        if (!file.exists()) {
            saveDefaultConfig();
        }
    }

    public boolean hasEnding(String fileName, String ending) {
        if (fileName == null) return false;
        return fileName.endsWith(ending);
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    void saveDefaultConfig() {
        plugin.getPlugin().saveResource(file.getName(), true);
    }

    public boolean exists() {
        return dir != null && dir.exists() && file.exists();
    }

    @Override
    public File getDir() {
        return dir;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public SpigotPlugin plugin() {
        return plugin;
    }
}
