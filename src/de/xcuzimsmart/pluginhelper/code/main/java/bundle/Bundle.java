package de.xcuzimsmart.pluginhelper.code.main.java.bundle;

import java.util.Map;
import java.util.function.BiConsumer;

public interface Bundle<T> {

    void add(String k, T t);

    void remove(String k);

    T get(String k);

    boolean contains(String k);

    Map<String, T> getActives();

    boolean isEmpty();

    void unregisterAll();

    void unregister(T t);

    void forEach(BiConsumer<String, ? super T> action);

    void clear();

    int size();
}
