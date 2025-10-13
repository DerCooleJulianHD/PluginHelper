package de.xcuzimsmart.pluginhelper.code.main.java;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.MCSpigotPlugin;

public class PluginHelper extends MCSpigotPlugin {

    @Override
    public void onPluginEnable() {
        this.setPrefix(getPrefix());
        if (config.isEmpty()) this.setConfigPrefix(prefix);
    }
}
