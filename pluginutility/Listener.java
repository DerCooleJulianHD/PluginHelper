package pluginutility;

import org.bukkit.Server;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public abstract class Listener implements org.bukkit.event.Listener {

    private static Listener instance; // static instance to reach listener by using class
    private final MinecraftPlugin plugin;
    private boolean enabled; // true or false

    public Listener(MinecraftPlugin plugin) {
        this.plugin = plugin;
        this.setEnabled(false);
    }

    // register this listener
    public void enable() {
       final Server server = plugin.getServer();
       final PluginManager pluginManager = server.getPluginManager();
       pluginManager.registerEvents(this, plugin); // register
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

    protected static void setInstance(Listener listener) {
        instance = listener;
    }

    public static Listener getInstance() {
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
