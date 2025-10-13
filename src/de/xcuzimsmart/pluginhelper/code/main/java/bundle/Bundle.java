package de.xcuzimsmart.pluginhelper.code.main.java.bundle;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public abstract class Bundle<T> {

    public final Map<String, String> BY_NAME = new HashMap<>();

    protected final SpigotPlugin plugin;
    // this map holds all added objects
    protected final Map<String, T> actives = new HashMap<>();

    protected final String name = getClass().getSimpleName();

    public Bundle(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    // adds a new object to the bundle.
    public void register(String k, String name, T o) {
        if (isRegistered(k)) return;

        BY_NAME.put(k, name);
        actives.put(k, o);

        onRegisterObject(o);
        Bukkit.getConsoleSender().sendMessage(this.getName() + ChatColor.GREEN + " Registered " +
                ChatColor.AQUA + name + "(" + o.getClass().getSimpleName() + ") " + ChatColor.GRAY + " on key: " + k);
    }

    public abstract void onRegisterObject(T object);

    // removes the object from the bundle.
    public void unregister(String k) {
        if (!isRegistered(k)) return;

        final T o = get(k);
        final String name = BY_NAME.get(k);
        if (o == null) return;
        actives.remove(k);

        if (name != null) BY_NAME.remove(name);

        onUnregisterObject(o);
        Bukkit.getConsoleSender().sendMessage(this.getName() + ChatColor.RED + " Unregistered " +
                ChatColor.AQUA + name + "(" + o.getClass().getSimpleName() + ") " + ChatColor.GRAY + " on key: " + k);
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

    public SpigotPlugin getPlugin() {
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

        for (String s : BY_NAME.values()) {
            T o = get(s);
            if (o == null) continue;
            if (name.equals(s)) return o;
        }

        return null;
    }
}