package de.xcuzimsmart.pluginhelper.code.main.java;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;

public final class PluginHelper extends SpigotPlugin {

    private static PluginHelper plugin;

    @Override
    public void onInitialize() {
        plugin = this;

        this.setPrefix(getPrefix());
        if (config.isEmpty()) config.setPrefix(prefix);
    }

    public static PluginHelper get() {
        return plugin;
    }
}
