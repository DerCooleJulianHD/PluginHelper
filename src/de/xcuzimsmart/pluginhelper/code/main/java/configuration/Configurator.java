package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import java.io.File;
import java.util.logging.Logger;

public abstract class Configurator implements Config {

    public static final Logger logger = Logger.getLogger(Configurator.class.getName());

    protected final File dir, file;

    boolean loaded = false;

    public Configurator(File dir, String fileName, boolean loadOnInit) {
        this.dir = dir;
        this.file = new File(dir, fileName);
        if (loadOnInit) load();
    }

    public Configurator(String dir, String fileName, boolean loadOnInit) {
        this(new File(dir), fileName, loadOnInit);
    }

    @Override
    public final boolean hasEnding(String fileName, String ending) {
        if (fileName == null) return false;
        return fileName.endsWith(ending);
    }

    @Override
    public final boolean isLoaded() {
        return loaded;
    }

    @Override
    public final void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    @Override
    public final boolean exists() {
        return dir != null && dir.exists() && file.exists();
    }

    @Override
    public final File getDir() {
        return dir;
    }

    @Override
    public final File getFile() {
        return file;
    }
}
