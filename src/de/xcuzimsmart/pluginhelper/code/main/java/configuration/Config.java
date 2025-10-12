package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.MCSpigotPlugin;

import java.io.File;

public interface Config {

    void createFiles();

    File getDir();

    File getFile();

    MCSpigotPlugin plugin();
}
