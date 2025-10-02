package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Configurator implements Config {

    public static final Logger logger = Logger.getLogger(Configurator.class.getName());

    protected final File dir, file;

    public Configurator(File dir, String fileName, String... ending) {
        this.dir = dir;
        this.file = new File(dir, correctFileName(fileName, ending));
    }

    @Override
    public void createFiles() {
        if (dir != null && !dir.exists()) dir.mkdirs();

        if (!file.exists()) {
            try {
                file.createNewFile();

                this.setDefaults();
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
            }
        }
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
}
