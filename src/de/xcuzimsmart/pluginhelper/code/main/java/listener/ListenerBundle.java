package de.xcuzimsmart.pluginhelper.code.main.java.listener;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ObjectBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.HashMap;
import java.util.Map;

public class ListenerBundle extends ObjectBundle<Listener> {

    public static final ListenerBundle EMPTY_BUNDLE = new ListenerBundle();

    final SpigotPlugin plugin = SpigotPlugin.getInstance();

    final PluginManager pluginManager = Bukkit.getPluginManager();

    final Map<String, Listener> actives = new HashMap<>();

    public ListenerBundle() {}

    @Override
    public void add(String k, Listener listener) {
        if (contains(k)) return;
        actives.put(k, listener);
        pluginManager.registerEvents(listener, plugin);
    }

    @Override
    public void remove(String k) {
        if (!contains(k)) return;

        final Listener listener = get(k);

        if (listener == null) return;

        actives.remove(k);
        unregister(listener);
    }

    @Override
    public void unregister(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    public boolean isRegistered(String k) {
        return contains(k);
    }
}
