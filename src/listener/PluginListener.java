package listener;

import org.bukkit.Server;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public abstract class PluginListener implements Listener {

    private static PluginListener instance;
    private final MinecraftPlugin plugin;
    private boolean enabled;

    public PluginListener(MinecraftPlugin plugin) {
        this.plugin = plugin;
        this.setEnabled(false);
    }

    public void enable() {
       final Server server = plugin.getPlugin().getServer();
       final PluginManager pluginManager = server.getPluginManager();
       pluginManager.registerEvents(this, plugin.getPlugin());
       this.setEnabled(true);
    }


    public void disable() {
        HandlerList.unregisterAll(this);
        this.setEnabled(false);
    }

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
