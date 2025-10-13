package de.xcuzimsmart.pluginhelper.code.main.java;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.MCSpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.test.Test;

public class Main extends MCSpigotPlugin {

    private static Test test;

    @Override
    public void onPluginEnable() {
        test = new Test();
        final String prefix = "&8[&bPluginHelper&8] &r";

        this.setPrefix(prefix);
        if (config.isEmpty()) this.setConfigPrefix(prefix);
    }

    public static Test getTest() {
        return test;
    }

}
