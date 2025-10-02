package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import java.io.File;

public interface Config {

    void createFiles();

    File getDir();

    File getFile();

    void setDefaults();
}
