package de.xcuzimsmart.pluginhelper.code.main.java.bundle;

import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Bundle<T> {

    public final List<String> BY_NAME = new ArrayList<>();

    protected final Plugin plugin;
    // this map holds all added objects
    protected final Map<String, T> actives = new HashMap<>();

    protected final String name = getClass().getSimpleName();

    public Bundle(Plugin plugin) {
        this.plugin = plugin;
    }

    // adds a new object to the bundle.
    public void register(String k, String name, T o) {
        if (isRegistered(k)) return;

        BY_NAME.add(name);
        actives.put(k, o);

        onRegisterObject(o);
    }

    public abstract void onRegisterObject(T object);

    // removes the object from the bundle.
    public void unregister(String k) {
        if (!isRegistered(k)) return;
        T o = get(k);
        if (o == null) return;

        BY_NAME.remove(name);
        actives.remove(k);

        onUnregisterObject(o);
    }

    public abstract void onUnregisterObject(T object);

    // unregisters all registered objects
    public void unregisterAll() {
        if (actives.isEmpty()) return;

        for (String k : actives.keySet()) {
            if (this.get(k) == null) continue;
            // unregister
            this.unregister(k);
        }

        if (!actives.isEmpty()) actives.clear(); // sets actives empty
    }

    @Nullable
    public T get(String k) {
        return !isRegistered(k) ? null : actives.get(k);
    }

    public boolean isRegistered(String k) {
        return (!actives.isEmpty()) && actives.containsKey(k);
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Map<String, T> getActives() {
        return actives;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public T getByName(String name) {
        if (this.actives.isEmpty()) return null;
        if (this.BY_NAME.isEmpty()) return null;
        if (!this.BY_NAME.contains(name)) return null;

        for (String s : BY_NAME) {
            T o = get(s);
            if (o == null) continue;
            if (name.equals(s)) return o;
        }

        return null;
    }
}