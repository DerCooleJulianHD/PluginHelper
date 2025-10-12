package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.command.CommandManager;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.GlobalScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.Scoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.run.Timer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MCSpigotPlugin extends JavaPlugin implements SpigotPlugin, Prefixable {

    protected static MCSpigotPlugin plugin;

    protected String prefix = null;

    final String name = getClass().getSimpleName();

    protected Scoreboard scoreboard;

    protected PluginConfigFile config;

    protected ListenerBundle listeners;
    protected CommandManager commandManager;

    @Override
    public void onLoad() {
        plugin = this;
        this.config = new PluginConfigFile(this);

        this.onPluginLoad();
    }

    @Override
    public void onEnable() {
        this.listeners = new ListenerBundle(this);
        this.commandManager = new CommandManager(this);
        this.scoreboard = new GlobalScoreboard();

        this.onPluginEnable();
    }

    @Override
    public void onPluginLoad() {}

    @Override
    public void onPluginDisable() {}

    @Override
    public void onDisable() {
        this.onPluginDisable();
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

    @Override
    public void setConfigPrefix() {
        this.setConfigPrefix("");
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

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ListenerBundle getListeners() {
        return listeners;
    }

    public PluginConfigFile getConfiguration() {
        return config;
    }

    public String getPluginName() {
        return name;
    }
}
