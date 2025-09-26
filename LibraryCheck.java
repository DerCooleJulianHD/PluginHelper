
public final class Dependencies {

    public static boolean hasDependencyPlugin(Plugin plugin, String libraryName) {
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





