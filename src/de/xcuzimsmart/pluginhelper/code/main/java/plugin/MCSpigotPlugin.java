package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.command.CommandManager;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.GlobalScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.Scoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.run.Timer;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class MCSpigotPlugin extends JavaPlugin implements Prefixable {

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
        this.createDataFolder(this.getDataFolder());
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

    public void onPluginLoad() {}

    public void onPluginDisable() {}

    public abstract void onPluginEnable();

    public static Timer createTimer(Plugin plugin) {
        return new Timer(plugin, 0, 20);
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

    public void createDataFolder(File file) {
        if (file != null && !file.exists()) if (file.isDirectory()) file.mkdirs();
    }

    public void sendEnableMessage() {
        final ConsoleCommandSender console = Bukkit.getConsoleSender();
        final String message = MessageBuilder.build(this, ChatColor.GREEN + name + " has been successfully enabled.");

        console.sendMessage(message);
    }

    public void sendDisableMessage() {
        final ConsoleCommandSender console = Bukkit.getConsoleSender();
        final String message = MessageBuilder.build(this, ChatColor.RED + name + " has been successfully disabled.");

        console.sendMessage(message);
    }
}
