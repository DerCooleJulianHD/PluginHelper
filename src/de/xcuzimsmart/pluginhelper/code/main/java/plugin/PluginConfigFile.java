package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.configuration.Defaultable;
import de.xcuzimsmart.pluginhelper.code.main.java.configuration.YamlConfigurator;

public class PluginConfigFile extends YamlConfigurator implements Defaultable {

    public PluginConfigFile(MCSpigotPlugin plugin) {
        super(plugin, plugin.getDataFolder(), "config.yml");

        createFiles();
    }

    @Override
    public void setDefaults() {
        plugin.setConfigPrefix();
    }
}
