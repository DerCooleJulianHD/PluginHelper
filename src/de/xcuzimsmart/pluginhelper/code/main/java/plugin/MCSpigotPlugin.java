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

    protected final ListenerBundle listeners;

    final String name = getClass().getSimpleName();

    public MCSpigotPlugin() {
        plugin = this;
        config = new PluginConfigFile(this);
        
        this.listeners = new ListenerBundle(this);

        this.scoreboard = new GlobalScoreboard();
    }

    public static Timer createTimer(Plugin plugin) {
        return new Timer(plugin, 0, 20);
    }

    @Override
    public String getPrefix() {
       if (this.prefix != null && !this.prefix.isEmpty()) return this.prefix;
       
       final String read = getPrefixFromConfig();
       
       if (!read.isEmpty()) return read;
       return null;
    }

    @Override
    public String getPrefixFromConfig() {
       if (config == null) return "";
       if (!config.isSet("prefix")) return "";

       final String read =  config.readString("prefix");
       
       if (read != null) return read;
       return "";
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void setConfigPrefix(String prefix) {
        config.writeString("prefix", prefix);
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

    public ListenerBundle getListeners() {
        return listeners;
    }

    public static PluginConfigFile getConfiguration() {
        return config;
    }

    public String getPluginName() {
        return name;
    }
}
