package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.configuration.YamlConfigurator;
import de.xcuzimsmart.pluginhelper.code.main.java.test.TestItem;
import org.bukkit.plugin.Plugin;

public class PluginConfigFile extends YamlConfigurator {

    public PluginConfigFile(Plugin plugin) {
        super(plugin.getDataFolder(), "config.yml");

        createFiles();
    }

    @Override
    public void setDefaults() {
        write("test-message", "Hello! This is a Test.");
        write("test-item", new TestItem());
    }
}
