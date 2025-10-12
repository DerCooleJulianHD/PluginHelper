package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.command.CommandManager;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.Scoreboard;

public interface SpigotPlugin extends Prefixable {

    ListenerBundle getListeners();
    CommandManager getCommandManager();

    void onPluginLoad();

    void onPluginEnable();

    void onPluginDisable();

    Scoreboard getScoreboard();

    void setScoreboard(Scoreboard scoreboard);

    String getPluginName();
}
