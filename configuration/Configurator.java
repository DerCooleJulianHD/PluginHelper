package de.xcuzimsmart.pluginhelper.code.configuration;

import de.xcuzimsmart.pluginhelper.code.interfaces.Loadable;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Configurator implements Loadable {

    protected static final Logger logger = Logger.getLogger(Configurator.class.getName());

    protected final File dir, file;
    protected final String[] endings;

    boolean loaded = false;

    public Configurator(File dir, String fileName, String ending) {
        this.dir = dir;
        this.endings = new String[]{ending};
        this.file = new File(dir, correctFileName(fileName, ending));
    }

    public Configurator(File dir, String fileName, String[] endings) {
        this.dir = dir;
        this.endings = endings;
        this.file = new File(dir, correctFileName(fileName, endings));
    }

    public String correctFileName(String fileName, String ending) {
        return hasFileEnding(fileName, ending) ? fileName : fileName + ending;
    }

    public String correctFileName(String fileName, String[] endings) {
        if (endings.length > 0) return this.correctFileName(fileName, endings[0]);
        return fileName;
    }

    public abstract void save();

    protected void createFiles() {
        if (dir != null) if (!dir.exists()) dir.mkdirs();
        if (file.exists()) return;
        try {
            file.createNewFile();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    protected boolean hasFileEnding(String fileName, String ending) {
        return fileName.endsWith(ending);
    }

    protected boolean hasFileEnding(String fileName, String[] endings) {
        if (endings.length == 0) throw new RuntimeException("No such Ending to compare");

        for (String s : endings) {
            if (!(hasFileEnding(fileName, s))) continue;
            return true;
        }

        return false;
    }

    @Override
    public void unload() {
        save();
        setLoaded(false);
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
