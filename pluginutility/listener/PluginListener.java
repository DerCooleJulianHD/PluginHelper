package pluginutility.listener;

import org.bukkit.Server;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import pluginutility.MinecraftPlugin;

public abstract class PluginListener implements Listener {

    private static PluginListener instance; // static instance to reach listener by using class
    private final MinecraftPlugin plugin;
    private boolean enabled; // true or false

    public PluginListener(MinecraftPlugin plugin) {
        this.plugin = plugin;
        this.setEnabled(false);
    }

    // register this listener
    public void enable() {
       final Server server = plugin.getPlugin().getServer();
       final PluginManager pluginManager = server.getPluginManager();
       pluginManager.registerEvents(this, plugin.getPlugin()); // register
       this.setEnabled(true); // set instance as enabled.
    }


    // unregister this listener
    public void disable() {
        HandlerList.unregisterAll(this); // unregister
        this.setEnabled(false); // set instance as not enabled.
    }

    // static unregistration
    public static void unregister() {
        instance.disable();
    }

    protected static void setInstance(PluginListener listener) {
        instance = listener;
    }

    public static PluginListener getInstance() {
        return instance;
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
