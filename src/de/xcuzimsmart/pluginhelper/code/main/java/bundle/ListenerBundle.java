package de.xcuzimsmart.pluginhelper.code.main.java.bundle;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public final class ListenerBundle extends Bundle<EventListener> {

    final PluginManager pluginManager = Bukkit.getPluginManager();

    public ListenerBundle(SpigotPlugin plugin) {
        super(plugin);
    }

    @Override
    // adding it.
    public void onRegisterObject(EventListener listener) {
        pluginManager.registerEvents(listener, plugin.getPlugin());
    }

    @Override
    // removing it.
    public void onUnregisterObject(EventListener listener) {
        HandlerList.unregisterAll(listener);
    }
}