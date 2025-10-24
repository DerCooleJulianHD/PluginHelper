package de.xcuzimsmart.pluginhelper.code.main.java.utils.depend;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public final class Dependencies {

    // does check if a plugin, which is using a dependency, also has the dependency plugin installed.
    public static boolean hasDependencyPlugin(Server server, Plugin using, String pluginNameOfDepend) {
        final PluginManager manager = server.getPluginManager();

        // dependency-plugin which needs to be installed.
        final Plugin depend = manager.getPlugin(pluginNameOfDepend);

        // here when dependency-plugin is not installed:
        if (depend == null) {
            server.getConsoleSender().sendMessage(ChatColor.RED + "'" + pluginNameOfDepend + "' plugin not found, please install it before continue.");
            if (using != null && using.isEnabled()) manager.disablePlugin(using);
            return false;
        }

        if (!depend.isEnabled()) manager.enablePlugin(depend);
        return true;
    }
}





