package de.xcuzimsmart.pluginhelper.code.main.java.listener;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ObjectBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class ListenerBundle extends ObjectBundle<Listener> {

    public static final ListenerBundle EMPTY_BUNDLE = new ListenerBundle();

    final SpigotPlugin plugin = SpigotPlugin.getInstance();

    final PluginManager pluginManager = Bukkit.getPluginManager();

    public ListenerBundle() {}

    @Override
    public void add(String k, Listener listener) {
        if (contains(k)) return;
        actives.put(k, listener);
        register(listener);
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
    public void register(Listener listener) {
        pluginManager.registerEvents(listener, plugin);
    }

    @Override
    public void unregister(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    public boolean isRegistered(String k) {
        return contains(k);
    }
}
