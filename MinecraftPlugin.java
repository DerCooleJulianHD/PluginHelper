package pluginutility;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MinecraftPlugin extends JavaPlugin {
    public abstract String prefix();
    public abstract Plugin getPlugin();
    public abstract void registerClasses();
    public abstract void registerCommands();

    public String name() {
        return getDescription().getName();
    }
}
