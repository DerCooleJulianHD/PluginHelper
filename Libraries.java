package pluginutility;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Libraries {

    public static void checkForLibrary(Plugin using, String libraryName) {
        final Server server = using.getServer();
        final PluginManager manager = server.getPluginManager();
        final Plugin lib = manager.getPlugin(libraryName);

        if (lib == null) {
            server.getConsoleSender().sendMessage(ChatColor.RED + "'" + libraryName + "' plugin not found, please install it before continue.");
            manager.disablePlugin(using);
            return false;
        }

        if (!lib.isEnabled()) manager.enablePlugin(lib);
        return true;
    }
}
