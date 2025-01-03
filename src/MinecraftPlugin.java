import org.bukkit.plugin.java.JavaPlugin;

public interface MinecraftPlugin {
    JavaPlugin getPlugin();
    String prefix();
    String name();
}
