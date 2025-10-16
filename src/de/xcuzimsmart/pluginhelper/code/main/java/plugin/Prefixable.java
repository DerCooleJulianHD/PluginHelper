package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

public interface Prefixable {

    // returns the prefix.
    String getPrefix();

    // returns the String that is set in plugin config.
    String getPrefixFromConfig();

    // sets the prefix
    void setPrefix(String prefix);

    // sets the String value in plugin config on key 'prefix'
    void setConfigPrefix(String prefix);
    void setConfigPrefix();
}


