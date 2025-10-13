package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.configuration.YamlConfigurator;

public final class PluginConfigFile extends YamlConfigurator {

    public PluginConfigFile(SpigotPlugin plugin) {
        super(plugin, plugin.getPlugin().getDataFolder(), "config.yml");

        load();
    }
}
