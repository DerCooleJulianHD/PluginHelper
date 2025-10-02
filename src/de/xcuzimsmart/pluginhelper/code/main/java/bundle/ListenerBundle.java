package de.xcuzimsmart.pluginhelper.code.main.java.bundle;

import de.xcuzimsmart.pluginhelper.code.main.java.utils.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public final class ListenerBundle extends Bundle<EventListener> {

    final PluginManager pluginManager;

    public ListenerBundle(Plugin plugin) {
        super(plugin);
        this.pluginManager = Bukkit.getPluginManager();
    }

    @Override
    // adding it.
    public void register(EventListener listener) {
        if (isRegistered(listener.getKey())) return;
        BY_NAME.add(listener.getName());
        actives.put(listener.getKey(), listener);
        pluginManager.registerEvents(listener, plugin);
        Bukkit.getConsoleSender().sendMessage(this.getName() + ChatColor.GREEN + " Registered " + ChatColor.AQUA + listener.getName() + ChatColor.GRAY + " on key: " + listener.getKey());
    }

    @Override
    // removing it.
    public void unregister(String k) {
        if (!isRegistered(k)) return;
        EventListener listener = get(k);
        if (listener == null) return;
        HandlerList.unregisterAll(listener);
        this.actives.remove(k);
        BY_NAME.remove(listener.getName());
        Bukkit.getConsoleSender().sendMessage(this.getName() + ChatColor.RED + " Unregistered " + ChatColor.AQUA + listener.getName() + ChatColor.GRAY + " on key: " + listener.getKey());
    }
}