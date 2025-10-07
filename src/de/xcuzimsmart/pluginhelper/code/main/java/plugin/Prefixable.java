package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

public interface Prefixable {

    String getPrefix();

    String getPrefixFromConfig(Configurator config, String defaultValue);

    void setPrefix(String prefix);
}


