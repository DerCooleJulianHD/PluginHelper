package de.xcuzimsmart.pluginhelper.code.git.branch.main.bundle;

import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public abstract class Bundle <T> {

    protected final Plugin plugin;
    // this map holds all added objects
    protected final Map<String, T> actives = new HashMap<>();

    protected final String name = getClass().getSimpleName();

    public Bundle(Plugin plugin) {
        this.plugin = plugin;
    }

    // adds a new object to the bundle.
    public abstract void register(T o);

    // removes the object from the bundle.
    public abstract void unregister(String k);

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
}
