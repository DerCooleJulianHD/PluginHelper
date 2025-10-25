package de.xcuzimsmart.pluginhelper.code.main.java.listener;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ObjectBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.interfaces.MinecraftPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class ListenerBundle extends ObjectBundle<Listener> {

    final PluginManager pluginManager = Bukkit.getPluginManager();

    public ListenerBundle(MinecraftPlugin plugin) {
        super(plugin);
    }

    @Override
    // adds a new Listener to the bundle and enables it on the server
    public void add(String k, Listener listener) {
        if (contains(k)) return; // if it's already in this bundle we aren't going to add it.
        actives.put(k, listener); // storing it.
        register(listener); // enabling it.
    }

    @Override
    // removes and disables the listener from key
    public void remove(String k) {
        if (!contains(k)) return; // if it's not in this bundle, then stop any execution of code here.

        final Listener listener = get(k); // this is the listener

        if (listener == null) return; // cancel if the listener on key 'k' does not exist or has a null value.

        actives.remove(k); // removing it from the map.
        unregister(listener); // disabling it
    }

    @Override
    // enables the listener on the server.
    public void register(Listener listener) {
        pluginManager.registerEvents(listener, (SpigotPlugin) plugin);
    }

    @Override
    // disables ist from the server.
    public void unregister(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    // returns true when listener does contain in the map.
    public boolean isRegistered(String k) {
        return contains(k);
    }

    // returns a new empty Listener Bundle.
    public static ListenerBundle emptyBundle(MinecraftPlugin plugin) {
        return new ListenerBundle(plugin);
    }
}
