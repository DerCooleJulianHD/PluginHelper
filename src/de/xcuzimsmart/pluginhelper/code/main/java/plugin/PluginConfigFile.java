package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.configuration.YamlConfigurator;

public final class PluginConfigFile extends YamlConfigurator {

    public PluginConfigFile(boolean loadOnInit) {
        super(SpigotPlugin.getInstance().getDataFolder(), "config.yml", loadOnInit);
    }

    public String getPrefix() {
        return isSet("prefx") ? readString("prefix") : "";
    }

    public void setPrefix(String s) {
        writeString("prefix", s);
    }
}
