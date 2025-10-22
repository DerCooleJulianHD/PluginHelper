package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.listener.ListenerManager;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.PluginScoreboard;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public interface MinecraftPlugin extends Prefixable {

    // prits a message to console.
    void sendEnableMessage();

    void sendDisableMessage();

    // Bukkit Plugin
    Plugin getPlugin();

    JavaPlugin getAsJavaPlugin();

    PluginScoreboard getScoreboard();

    void setScoreboard(PluginScoreboard scoreboard);

    PluginConfigFile getConfiguration();

    String getPluginName();

    ListenerManager getListenerManager();
}
