package de.xcuzimsmart.pluginhelper.code.main.java.bundle;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Bundle<T> {

    public final Map<String, String> BY_NAME = new HashMap<>();

    // this map holds all added objects
    protected final Map<String, T> actives = new HashMap<>();

    protected final String name = getClass().getSimpleName();

    public Bundle() {}

    // adds a new object to the bundle.
    public final void register(String k, String name, T o) {
        if (isRegistered(k)) return;

        BY_NAME.put(k, name);
        actives.put(k, o);

        onRegisterObject(o);
    }

    public abstract void onRegisterObject(T object);

    // removes the object from the bundle.
    public final void unregister(String k) {
        if (!isRegistered(k)) return;

        final T o = get(k);
        final String name = BY_NAME.get(k);
        if (o == null) return;
        actives.remove(k);

        if (name != null) BY_NAME.remove(name);

        onUnregisterObject(o);
    }

    public abstract void onUnregisterObject(T object);

    // unregisters all registered objects
    public final void unregisterAll() {
        if (actives.isEmpty()) return;

        for (String k : actives.keySet()) {
            if (this.get(k) == null) continue;
            // unregister
            this.unregister(k);
        }

        if (!actives.isEmpty()) actives.clear(); // sets actives empty
    }

    @Nullable
    public final T getByName(String name) {
        if (this.actives.isEmpty()) return null;
        if (this.BY_NAME.isEmpty()) return null;

        for (String s : BY_NAME.values()) {
            T o = get(s);
            if (o == null) continue;
            if (name.equals(s)) return o;
        }

        return null;
    }

    @Nullable
    public final T get(int id) {
        final LinkedList<String> keys = (LinkedList<String>) List.copyOf(actives.keySet());

        if (keys.isEmpty()) return null;

        final String k = keys.get(id);
        return get(k);
    }

    @Nullable
    public final T get(String k) {
        return !isRegistered(k) ? null : actives.get(k);
    }

    public final boolean isRegistered(String k) {
        return (!actives.isEmpty()) && actives.containsKey(k);
    }

    public final Map<String, T> getActives() {
        return actives;
    }

    public final String getName() {
        return name;
    }
}