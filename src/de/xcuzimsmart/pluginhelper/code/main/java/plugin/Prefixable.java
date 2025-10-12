package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

public interface Prefixable {

    String getPrefix();

    String getPrefixFromConfig();

    void setPrefix(String prefix);

    void setConfigPrefix(String prefix);

    void setConfigPrefix();
}


