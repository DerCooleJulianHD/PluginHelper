package de.xcuzimsmart.pluginhelper.code.main.java.bundle;

import org.bukkit.event.Listener;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Bundle<T> {

    void add(String name, T t);

    void remove(String name);

    T get(String name);

    boolean contains(String name);

    Map<String, T> getActives();

    boolean isEmpty();

    void unregisterAll();

    void unregister(T t);

    void forEach(BiConsumer<String, ? super T> action);
}
