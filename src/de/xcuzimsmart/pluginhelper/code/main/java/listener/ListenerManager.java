package de.xcuzimsmart.pluginhelper.code.main.java.listener;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public final class ListenerManager {

    final Map<String, ListenerBundle> bundles = new HashMap<>();

    public ListenerManager() {
    }

    public void register(String name, ListenerBundle bundle) {
        bundles.put(name, bundle);

        final Map<String, EventListener> actives = bundle.actives;

        actives.values().forEach(listener -> {
            if (!listener.isRegistered()) {
                Bukkit.getPluginManager().registerEvents(listener, SpigotPlugin.getInstance());
                listener.setRegistered(true);
            }
        });
    }

    public void unregister(String name) {
        final ListenerBundle bundle = getBundle(name);

        if (bundle == null) return;

        final Map<String, EventListener> actives = bundle.actives;

        if (!actives.isEmpty()) {
            actives.values().forEach(listener -> {
                if (listener.isRegistered()) {
                    Bukkit.getPluginManager().registerEvents(listener, SpigotPlugin.getInstance());
                    listener.setRegistered(true);
                }
            });
        }

        bundles.remove(name);
    }

    public ListenerBundle getBundle(String name) {
        return bundles.get(name);
    }
}
