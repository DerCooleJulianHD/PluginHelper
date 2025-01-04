package pluginutility;

import org.bukkit.plugin.java.JavaPlugin;
import pluginutility.menu.events.MenuInteractionListener;

public abstract class MinecraftPlugin extends JavaPlugin {

    public abstract String prefix();

    public String name() {
        return this.getDescription().getName();
    }

    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public void registerListeners() {
        new MenuInteractionListener(this);
    }
}
