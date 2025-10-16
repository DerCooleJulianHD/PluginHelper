package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Loadable;

import java.io.File;

public interface Config extends Loadable {

    // the folder the config file is in.
    File getDir();

    // returns the config file.
    File getFile();

    // returns the plugin.
    SpigotPlugin plugin();

    // returns true if config file does end with 'filename.{ending}'
    boolean hasEnding(String fileName, String ending);

    // returns true when 'getDir()' does not return null and file is exist.
    boolean exists();
}
