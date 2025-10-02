package de.xcuzimsmart.pluginhelper.code.main.java.utils;

public interface Loadable {

    void load();

    void unload();

    boolean isLoaded();

    void setLoaded(boolean loaded);
}

