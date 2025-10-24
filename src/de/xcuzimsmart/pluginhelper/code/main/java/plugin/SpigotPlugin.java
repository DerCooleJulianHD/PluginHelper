package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.listener.ListenerManager;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.interfaces.MinecraftPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.run.Timer;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.GlobalScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.PluginScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.annotations.Abstract;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.file.FileManager;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.messanger.MessageBuilder;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public abstract class SpigotPlugin extends JavaPlugin implements MinecraftPlugin {

    protected static SpigotPlugin plugin;

    protected String prefix = null; /* <-- by default */

    protected PluginScoreboard scoreboard;

    protected ListenerManager listenerManager;

    protected PluginConfigFile config;

    protected Logger logger = Logger.getLogger(SpigotPlugin.class.getSimpleName());

    protected final ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    @Abstract
    public void onLoad() {
        // creating an instance.
        plugin = this;

        // creating the Plugin folder first.
        FileManager.mkdirIfNotExists(getDataFolder());

        // creating the config file and load it.
        config = new PluginConfigFile(true);
        getPlugin().onLoad(); // invoke the 'onLoad()' on sub class.
    }

    @Override
    @Abstract
    public void onEnable() {
        this.listenerManager = new ListenerManager();

        this.scoreboard = new GlobalScoreboard(); // creating a default scoreboard.

        getPlugin().onEnable(); // invoke the 'onEnable()' on sub class.
        this.sendEnableMessage(); // printing out to console, that the plugin has been enabled.
    }

    @Override
    @Abstract
    public void onDisable() {
        getPlugin().onDisable(); // invoke the 'onDisable()' on sub class.
        this.sendDisableMessage(); // printing out to console, that the plugin has been disabled.
    }

    @Override
    public final String getPrefix() {
        if (this.prefix != null) return this.prefix;
        if (config == null) return null;

        final String read = config.getPrefix();

        if (read != null && (!read.isEmpty())) return read;
        return null;
    }

    @Override
    public final void sendEnableMessage() {
        console.sendMessage(MessageBuilder.build(this, ChatColor.GREEN + getPluginFullName() + " has been successfully enabled."));
    }

    @Override
    public final void sendDisableMessage() {
        console.sendMessage(MessageBuilder.build(this, ChatColor.RED + getPluginFullName() + " has been successfully disabled."));
    }

    @Override
    public final Timer createTimer() {
        return new Timer(0, 20);
    }

    @Override
    public final Plugin getPlugin() {
        return getAsJavaPlugin();
    }

    @Override
    public final JavaPlugin getAsJavaPlugin() {
        return plugin;
    }

    @Override
    public final void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public final PluginScoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public final void setScoreboard(PluginScoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    @Override
    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    @Override
    public PluginConfigFile getConfiguration() {
        return config;
    }

    @Override
    public final String getPluginName() {
        return getDescription().getName();
    }

    @Override
    public String getPluginFullName() {
        return getDescription().getFullName();
    }

    public static SpigotPlugin getInstance() {
        return plugin;
    }

    @Override
    public Logger getPluginLogger() {
        return logger;
    }

    @Override
    public ConsoleCommandSender getConsoleSender() {
        return console;
    }
}