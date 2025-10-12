package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.MCSpigotPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Configurator implements Config {

    public static final Logger logger = Logger.getLogger(Configurator.class.getName());

    protected final MCSpigotPlugin plugin;

    protected final File dir, file;

    boolean loaded = false;

    protected final FilenameEnding endings = getClass().getDeclaredAnnotation(FilenameEnding.class);

    public Configurator(MCSpigotPlugin plugin, File dir, String fileName) {
        this.plugin = plugin;
        this.dir = dir;
        this.file = new File(dir, correctFileName(fileName, endings != null ? endings.endings() : null));
    }

    public Configurator(MCSpigotPlugin plugin, String dir, String fileName) {
        this(plugin, new File(dir), fileName);
    }

    @Override
    public void createFiles() {
        if (dir != null && !dir.exists()) dir.mkdirs();

        if (!file.exists()) {
            try {
                file.createNewFile();

                if (this instanceof Defaultable defaultable) {
                    this.load(); // loading the file first.
                    defaultable.setDefaults();
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
            }
        }
    }

    public boolean exists() {
        return dir != null && dir.exists() && file.exists();
    }

    private String correctFileName(String fileName, String... ending) {
        if (ending == null) return fileName;
        if (ending.length < 2) return fileName.endsWith(ending[0]) ? fileName : fileName + ending[0];

        List<String> list = Arrays.stream(ending).toList();

        for (final String s : list) if (fileName.endsWith(s)) return fileName;
        return fileName + ending[0];
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
    public void setDefaults() {}

    @Override
    public MCSpigotPlugin plugin() {
        return plugin;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
