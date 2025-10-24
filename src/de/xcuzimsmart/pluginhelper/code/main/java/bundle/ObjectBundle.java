package de.xcuzimsmart.pluginhelper.code.main.java.bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class ObjectBundle<T> implements Bundle<T> {

    final Map<String, T> actives = new HashMap<>();

    public ObjectBundle() {}

    @Override
    public final T get(String k) {
        return actives.get(k);
    }

    @Override
    public final boolean contains(String k) {
        return actives.containsKey(k);
    }

    @Override
    public final Map<String, T> getActives() {
        return actives;
    }

    @Override
    public final boolean isEmpty() {
        return actives.isEmpty();
    }

    @Override
    public final void unregisterAll() {
        if (!isEmpty()) actives.values().forEach(this::unregister);
    }

    @Override
    public final void forEach(BiConsumer<String, ? super T> action) {
        if (isEmpty()) return;
        if (action == null) return;

        actives.forEach(action);
    }

    @Override
    public final void clear() {
        unregisterAll();
        actives.clear();
    }

    @Override
    public final int size() {
        return actives.size();
    }
}
