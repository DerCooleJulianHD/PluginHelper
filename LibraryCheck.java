package pluginutility;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class LibraryCheck {

    public static boolean hasLibrary(Plugin plugin, String libraryName) {
        final Server server = plugin.getServer();
        final PluginManager manager = server.getPluginManager();
        final Plugin lib = manager.getPlugin(libraryName);

        if (lib == null) {
            server.getConsoleSender().sendMessage(ChatColor.RED + "'" + libraryName + "' plugin not found, please install it before continue.");
            manager.disablePlugin(plugin);
            return false;
        }

        if (!lib.isEnabled()) manager.enablePlugin(lib);
        return true;
    }
}



