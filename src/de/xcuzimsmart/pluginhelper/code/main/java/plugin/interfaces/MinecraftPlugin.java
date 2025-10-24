package de.xcuzimsmart.pluginhelper.code.main.java.plugin.interfaces;

import de.xcuzimsmart.pluginhelper.code.main.java.listener.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.PluginConfigFile;
import de.xcuzimsmart.pluginhelper.code.main.java.run.Timer;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.PluginScoreboard;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

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

    String getPluginFullName();

    ListenerBundle getListeners(String name);

    void addListeners(String name, ListenerBundle bundle);

    void removeListeners(String name);

    ConsoleCommandSender getConsoleSender();

    Logger getPluginLogger();

    Timer createTimer();
}
