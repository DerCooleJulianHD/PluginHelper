package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.run.Timer;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.GlobalScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.PluginScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.AbstractUsage;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.FileManager;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.MessageBuilder;
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

    protected static PluginConfigFile config;

    protected ListenerBundle listeners;

    protected static Logger logger = Logger.getLogger(SpigotPlugin.class.getSimpleName());

    protected final ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    @AbstractUsage
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
    @AbstractUsage
    public void onEnable() {
        // creating a bundle for listeners.
        this.listeners = new ListenerBundle();
        this.scoreboard = new GlobalScoreboard(); // creating a default scoreboard.

        getPlugin().onEnable(); // invoke the 'onEnable()' on sub class.
        this.sendEnableMessage(); // printing out to console, that the plugin has been enabled.
    }

    @Override
    @AbstractUsage
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
        console.sendMessage(MessageBuilder.build(this, ChatColor.GREEN + getPluginName() + " has been successfully enabled."));
    }

    @Override
    public final void sendDisableMessage() {
        console.sendMessage(MessageBuilder.build(this, ChatColor.RED + getPluginName() + " has been successfully disabled."));
    }

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

    public final ListenerBundle getListeners() {
        return listeners;
    }

    @Override
    public PluginConfigFile getConfiguration() {
        return config;
    }

    @Override
    public final String getPluginName() {
        return getDescription().getFullName();
    }

    public static SpigotPlugin getInstance() {
        return plugin;
    }

    public static Logger getPluginLogger() {
        return logger;
    }

    public ConsoleCommandSender getConsoleSender() {
        return console;
    }
}
