package de.code.test;

public interface Loadable {

    void load();

    void unload();

    boolean isLoaded();

    void setLoaded(boolean loaded);
}

