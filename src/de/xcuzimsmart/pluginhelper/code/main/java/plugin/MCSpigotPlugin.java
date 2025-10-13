package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.command.CommandManager;
import de.xcuzimsmart.pluginhelper.code.main.java.run.Timer;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.GlobalScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.Scoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class MCSpigotPlugin extends JavaPlugin implements SpigotPlugin {

    protected static MCSpigotPlugin plugin;

    protected String prefix = null; // by default

    protected Scoreboard scoreboard;

    protected PluginConfigFile config;

    protected ListenerBundle listeners;
    protected CommandManager commandManager;

    @Override
    public void onLoad() {
        plugin = this;

        if (!getDataFolder().exists()) this.getDataFolder().mkdirs();

        this.config = new PluginConfigFile(this);
        this.onPluginLoad();
    }

    @Override
    public void onEnable() {
        this.listeners = new ListenerBundle(this);
        this.commandManager = new CommandManager(this);

        this.scoreboard = new GlobalScoreboard();

        this.onPluginEnable();
        this.sendEnableMessage();
    }

    @Override
    public void onDisable() {
        this.onPluginDisable();
        this.sendDisableMessage();
    }

    @Override
    public void onPluginLoad() {}

    @Override
    public void onPluginDisable() {}

    @Override
    public Timer createTimer() {
        return new Timer(this, 0, 20);
    }

    @Override
    public Plugin getPlugin() {
        return getAsJavaPlugin();
    }

    @Override
    public JavaPlugin getAsJavaPlugin() {
        return plugin;
    }

    @Override
    public String getPrefix() {
        if (this.prefix != null) return this.prefix;
       
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

    @Override
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public static MCSpigotPlugin getInstance() {
        return plugin;
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public ListenerBundle getListeners() {
        return listeners;
    }

    @Override
    public PluginConfigFile getConfiguration() {
        return config;
    }

    @Override
    public String getPluginName() {
        return getDescription().getFullName();
    }

    @Override
    public void sendEnableMessage() {
        final ConsoleCommandSender console = Bukkit.getConsoleSender();
        final String message = MessageBuilder.build(this, ChatColor.GREEN + getPluginName() + " has been successfully enabled.");

        console.sendMessage(message);
    }

    @Override
    public void sendDisableMessage() {
        final ConsoleCommandSender console = Bukkit.getConsoleSender();
        final String message = MessageBuilder.build(this, ChatColor.RED + getPluginName() + " has been successfully disabled.");

        console.sendMessage(message);
    }
}
