package de.xcuzimsmart.pluginhelper.code.main.java;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.MCSpigotPlugin;

public class PluginHelper extends MCSpigotPlugin {

    static PluginHelper instance;

    @Override
    public void onPluginEnable() {
        instance = this;

        this.setPrefix(getPrefix());
        if (config.isEmpty()) this.setConfigPrefix(prefix);
    }

    public static PluginHelper getPluginHelper() {
        return instance;
    }
}
