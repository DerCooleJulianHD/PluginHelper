package de.code.test.bundle;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nullable;

public final class ListenerBundle extends Bundle<EventListener> {

    final PluginManager pluginManager;

    public ListenerBundle(Plugin plugin) {
        super(plugin);
        this.pluginManager = Bukkit.getPluginManager();
    }

    @Override
    public void register(EventListener listener) {
        if (isRegistered(listener.getKey())) return;
        // adding it.
        actives.put(listener.getKey(), listener);
        pluginManager.registerEvents(listener, plugin);
    }


    @Override
    public void unregister(String k) {
        if (!isRegistered(k)) return;
        EventListener listener = get(k);
        if (listener == null) return;
        HandlerList.unregisterAll(listener);
        this.actives.remove(k);
    }

    @Nullable
    public EventListener getListenerByName(String name) {
        if (this.actives.isEmpty()) return null;

        for (String key : actives.keySet()) {
            EventListener listener = get(key);
            if (listener == null) continue;
            if (name.equals(listener.getName())) return listener;
        }

        return null;
    }
}
