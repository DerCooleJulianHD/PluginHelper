package de.xcuzimsmart.pluginhelper.code.main.java;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.MCSpigotPlugin;

public final class PluginHelper extends MCSpigotPlugin {

    static PluginHelper instance;

    @Override
    public void onEnable() {
        instance = this;

        this.setPrefix(getPrefix());
        if (config.isEmpty()) this.setConfigPrefix(prefix);
    }

    public static PluginHelper get() {
        return instance;
    }
}
