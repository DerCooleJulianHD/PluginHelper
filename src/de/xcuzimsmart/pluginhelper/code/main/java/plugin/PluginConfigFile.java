package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.configuration.YamlConfigurator;

public final class PluginConfigFile extends YamlConfigurator {

    public PluginConfigFile(MCSpigotPlugin plugin) {
        super(plugin, plugin.getDataFolder(), "config.yml");

        load();
    }
}
