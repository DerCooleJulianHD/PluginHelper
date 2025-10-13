package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.command.CommandManager;
import de.xcuzimsmart.pluginhelper.code.main.java.run.Timer;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.Scoreboard;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public interface SpigotPlugin extends Prefixable {

    Scoreboard getScoreboard();

    void onPluginLoad();

    void onPluginEnable();

    void sendEnableMessage();

    void onPluginDisable();

    void sendDisableMessage();

    Timer createTimer();

    void setScoreboard(Scoreboard scoreboard);

    CommandManager getCommandManager();

    ListenerBundle getListeners();

    PluginConfigFile getConfiguration();

    String getPluginName();

    void createDataFolder(File file);

    Plugin getPlugin();

    JavaPlugin getAsJavaPlugin();
}
