package de.xcuzimsmart.pluginhelper.code.main.java;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;

public final class PluginHelper extends SpigotPlugin {

    @Override
    public void onEnable() {
        this.setPrefix(getPrefix());
        if (config.isEmpty()) config.setPrefix(prefix);
    }

    public static PluginHelper get() {
        return (PluginHelper) getInstance();
    }
}
