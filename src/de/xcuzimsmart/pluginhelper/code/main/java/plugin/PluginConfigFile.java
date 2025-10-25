package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.configuration.yaml.YamlConfigFile;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.interfaces.Prefixable;

public final class PluginConfigFile extends YamlConfigFile implements Prefixable {

    public PluginConfigFile(SpigotPlugin plugin, boolean loadOnInit) {
        super(plugin, plugin.getDataFolder(), "config.yml", loadOnInit);
    }

    @Override
    // returns the String that is set in plugin config.
    public String getPrefix() {
        return isSet("prefix") ? readString("prefix") : "";
    }

    @Override
    // sets the String value in plugin config on key 'prefix'
    public void setPrefix(String s) {
        writeString("prefix", s);
    }
    public void setPrefix() {
        this.setPrefix("");
    }
}
