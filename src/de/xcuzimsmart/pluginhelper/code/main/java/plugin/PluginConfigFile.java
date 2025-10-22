package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.configuration.YamlConfigurator;

public final class PluginConfigFile extends YamlConfigurator implements Prefixable {

    public PluginConfigFile(boolean loadOnInit) {
        super(SpigotPlugin.getInstance().getDataFolder(), "config.yml", loadOnInit);
    }

    @Override
    // returns the String that is set in plugin config.
    public String getPrefix() {
        return isSet("prefx") ? readString("prefix") : "";
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
