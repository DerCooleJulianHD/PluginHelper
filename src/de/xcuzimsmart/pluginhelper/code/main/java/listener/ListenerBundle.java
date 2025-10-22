package de.xcuzimsmart.pluginhelper.code.main.java.listener;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.Bundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

import java.util.HashMap;
import java.util.Map;

public final class ListenerBundle implements Bundle<EventListener> {

    final SpigotPlugin plugin = SpigotPlugin.getInstance();

    final PluginManager pluginManager = Bukkit.getPluginManager();

    final Map<String, EventListener> actives = new HashMap<>();

    public ListenerBundle() {
    }

    @Override
    public void add(String k, EventListener listener) {
        if (contains(k)) return;

        actives.put(k, listener);
        pluginManager.registerEvents(listener, plugin);
    }

    @Override
    public void remove(String k) {
        if (!contains(k)) return;

        final EventListener listener = get(k);

        if (listener == null) return;

        actives.remove(k);
        HandlerList.unregisterAll(listener);
    }

    @Override
    public EventListener get(String k) {
        return actives.get(k);
    }

    @Override
    public boolean contains(String k) {
        return actives.containsKey(k);
    }

    public boolean isRegistered(String k) {
        return contains(k);
    }

    @Override
    public Map<String, EventListener> getActives() {
        return actives;
    }
}
