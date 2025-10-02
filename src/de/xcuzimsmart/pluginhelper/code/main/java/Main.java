package de.xcuzimsmart.pluginhelper.code.main.java;

import de.xcuzimsmart.pluginhelper.code.main.java.command.DisablePluginCommand;
import de.xcuzimsmart.pluginhelper.code.main.java.command.EnablePluginCommand;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.MCSpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.test.Test;

public class Main extends MCSpigotPlugin {

    private static Test test;

    @Override
    public void onEnable() {
        new DisablePluginCommand(this);
        new EnablePluginCommand(this);

        test = new Test();

        this.setPrefix("&8[&bPluginHelper&8] &r");
    }

    public static Test getTest() {
        return test;
    }
}
