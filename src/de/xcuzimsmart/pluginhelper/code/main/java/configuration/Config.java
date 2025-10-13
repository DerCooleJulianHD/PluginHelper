package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Loadable;

import java.io.File;

public interface Config extends Loadable {

    File getDir();

    File getFile();

    SpigotPlugin plugin();

    boolean hasEnding(String fileName, String ending);

    boolean exists();
}
