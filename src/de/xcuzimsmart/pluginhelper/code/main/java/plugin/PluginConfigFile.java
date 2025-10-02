package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.configuration.YamlConfigurator;
import org.bukkit.plugin.Plugin;

public class PluginConfigFile extends YamlConfigurator {

    public PluginConfigFile(Plugin plugin) {
        super(plugin.getDataFolder(), "plugin");
    }

    @Override
    public void setDefaults() {
        write("test", "Hello! This is a Test.");
    }
}
