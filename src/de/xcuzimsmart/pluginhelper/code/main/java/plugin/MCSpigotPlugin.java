package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.GlobalScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.Scoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.timer.Timer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MCSpigotPlugin extends JavaPlugin implements Prefixable {

    protected static MCSpigotPlugin plugin;

    protected String prefix = null;

    protected Scoreboard scoreboard;

    protected static PluginConfigFile config;

    final String name = getClass().getSimpleName();

    public MCSpigotPlugin() {
        plugin = this;
        config = new PluginConfigFile(this);

        this.scoreboard = new GlobalScoreboard();
    }

    public static Timer createTimer(Plugin plugin) {
        return new Timer(plugin, 0, 20);
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public static MCSpigotPlugin getInstance() {
        return plugin;
    }

    public static PluginConfigFile getConfiguration() {
        return config;
    }

    public String getPluginName() {
        return name;
    }
}
