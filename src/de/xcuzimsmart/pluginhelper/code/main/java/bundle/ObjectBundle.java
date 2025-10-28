package de.xcuzimsmart.pluginhelper.code.main.java.bundle;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.interfaces.MinecraftPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class ObjectBundle<T> implements Bundle<T> {

    protected final MinecraftPlugin plugin;

    // this is the map, all objects of type T will be stored in.
    protected final Map<String, T> actives = new HashMap<>();

    public ObjectBundle(MinecraftPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    // returs the object of type T
    public final T get(String k) {
        return actives.get(k);
    }

    @Override
    // returns true when object is in map.
    public final boolean contains(String k) {
        return actives.containsKey(k);
    }

    @Override
    public final Map<String, T> getActives() {
        return actives;
    }

    @Override
    // returns true when map is empty
    public final boolean isEmpty() {
        return actives.isEmpty();
    }

    @Override
    // unregisteres all objects without removing it from the map
    public final void unregisterAll() {
        if (!isEmpty()) actives.values().forEach(this::unregister);
    }

    @Override
    // loop
    public final void forEach(BiConsumer<String, ? super T> action) {
        if (isEmpty()) return;
        if (action == null) return;

        actives.forEach(action);
    }

    @Override
    // unregisters and removes each object
    public final void clear() {
        unregisterAll();
        actives.clear();
    }

    @Override
    // returns how many objects of type T the bundle is holding.
    public final int getSize() {
        return actives.size();
    }
}
