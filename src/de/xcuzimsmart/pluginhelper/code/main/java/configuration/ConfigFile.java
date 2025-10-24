package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import de.xcuzimsmart.pluginhelper.code.main.java.utils.interfaces.Loadable;

import java.io.File;

public abstract class ConfigFile implements Loadable {

    protected final File dir, file;

    boolean loaded = false;

    public ConfigFile(File dir, String fileName, boolean loadOnInit) {
        this.dir = dir;
        this.file = new File(dir, fileName);
        if (loadOnInit) load();
    }

    public ConfigFile(String dir, String fileName, boolean loadOnInit) {
        this(new File(dir), fileName, loadOnInit);
    }

    // returns true if config file does end with 'filename{".ending"}'
    public final boolean hasEnding(String fileName, String ending) {
        if (fileName == null) return false;
        return fileName.endsWith(ending);
    }

    @Override
    // returns true when config has been loaded.
    public final boolean isLoaded() {
        return loaded;
    }

    @Override
    // sets if config has been loaded or not.
    public final void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    // returns true when 'getDir()' does not return null and file is exist.
    public final boolean exists() {
        return dir != null && dir.exists() && file.exists();
    }

    // the folder the config file is in.
    public final File getDir() {
        return dir;
    }

    // returns the config file.
    public final File getFile() {
        return file;
    }
}
