package de.xcuzimsmart.pluginhelper.code.git.branch.main.interfaces;

public interface Loadable {

    void load();

    void unload();

    boolean isLoaded();

    void setLoaded(boolean loaded);
}

