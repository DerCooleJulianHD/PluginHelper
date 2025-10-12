package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.MCSpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Loadable;

import java.io.File;

public interface Config extends Loadable {

    void createFiles();

    File getDir();

    File getFile();

    void setDefaults();

    MCSpigotPlugin plugin();
}
