package me.xcuzimsmart.utils.listener;

import me.xcuzimsmart.utils.*

import org.bukkit.Server;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public abstract class PluginListener implements Listener {

    private final MinecraftPlugin plugin;
    private boolean enabled;

    public PluginListener(MinecraftPlugin plugin) {
        this.plugin = plugin;
        this.setEnabled(false);
    }


    public void enable() {
       final Server server = plugin.getServer();
       final PluginManager pluginManager = server.getPluginManager();
       pluginManager.registerEvents(this, plugin);
       this.setEnabled(true);
    }


    public void disable() {
        HandlerList.unregisterAll(this);
        this.setEnabled(false);
    }


    public boolean isEnabled() {
        return enabled;
    }

	
    private void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	
    public MinecraftPlugin getMinecraftPlugin() {
	return plugin;
    }
}
