package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

public interface Prefixable {

    String getPrefix();

    String getPrefixFromConfig(Configurator config);

    void setPrefix(String prefix);
}


