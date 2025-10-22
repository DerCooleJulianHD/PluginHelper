package de.xcuzimsmart.pluginhelper.code.main.java.bundle;

import java.util.Map;

public interface Bundle<T> {

    void add(String name, T t);

    void remove(String name);

    T get(String name);

    boolean contains(String name);

    Map<String, T> getActives();
}
