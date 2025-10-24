package de.xcuzimsmart.pluginhelper.code.main.java.listener;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.Bundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public final class ListenerBundle implements Bundle<Listener> {

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
    public void unregisterAll() {
       if (!isEmpty()) actives.values().forEach(this::unregister);
    }

    @Override
    public void forEach(BiConsumer<String, ? super Listener> action) {
        if (isEmpty()) return;
        if (action == null) return;

        actives.forEach(action);
    }

    @Override
    public void clear() {
        unregisterAll();
        actives.clear();
    }

    @Override
    public void unregister(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    @Override
    public Listener get(String k) {
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
    public Map<String, Listener> getActives() {
        return actives;
    }

    @Override
    public boolean isEmpty() {
        return actives.isEmpty();
    }

    public static ListenerBundle EMPTY_BUNDLE() {
        return new ListenerBundle();
    }
}
