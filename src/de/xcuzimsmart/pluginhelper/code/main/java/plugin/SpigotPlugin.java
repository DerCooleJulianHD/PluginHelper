package de.xcuzimsmart.pluginhelper.code.main.java.plugin;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.command.CommandManager;
import de.xcuzimsmart.pluginhelper.code.main.java.run.Timer;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.GlobalScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.PluginScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.AbstractUsage;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class SpigotPlugin extends JavaPlugin implements Prefixable {

    protected static SpigotPlugin plugin;

    protected String prefix = null; /* <-- by default */

    protected PluginScoreboard scoreboard;

    protected PluginConfigFile config;

    protected ListenerBundle listeners;
    protected CommandManager commandManager;

    @Override
    @AbstractUsage
    public void onLoad() {
        // creating the Plugin folder first.
        plugin = this;

        if (!getDataFolder().exists()) this.getDataFolder().mkdirs();

        // creating the config file and load it.
        this.config = new PluginConfigFile(true);
        getPlugin().onLoad(); // invoke the 'onLoad()' on sub class.
    }

    @Override
    @AbstractUsage
    public void onEnable() {
        // creating both bundles.
        this.listeners = new ListenerBundle();
        this.commandManager = new CommandManager(); // ...CommandManager is also a Bundle.
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
       
        final String read = getPrefixFromConfig();
       
        if (read != null && (!read.isEmpty())) return read;
        return null;
    }

    public final void sendEnableMessage() {
        final ConsoleCommandSender console = Bukkit.getConsoleSender();
        final String message = MessageBuilder.build(this, ChatColor.GREEN + getPluginName() + " has been successfully enabled.");

        console.sendMessage(message);
    }

    public final void sendDisableMessage() {
        final ConsoleCommandSender console = Bukkit.getConsoleSender();
        final String message = MessageBuilder.build(this, ChatColor.RED + getPluginName() + " has been successfully disabled.");

        console.sendMessage(message);
    }

    public final Timer createTimer() {
        return new Timer(0, 20);
    }

    public final Plugin getPlugin() {
        return getAsJavaPlugin();
    }

    public final JavaPlugin getAsJavaPlugin() {
        return plugin;
    }

    @Override
    public final String getPrefixFromConfig() {
        return config.getPrefix();
    }

    @Override
    public final void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public final void setConfigPrefix(String prefix) {
        config.setPrefix(prefix);
    }

    @Override
    public final void setConfigPrefix() {
        this.setConfigPrefix("");
    }

    public final PluginScoreboard getScoreboard() {
        return scoreboard;
    }

    public final void setScoreboard(PluginScoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public final CommandManager getCommandManager() {
        return commandManager;
    }

    public final ListenerBundle getListeners() {
        return listeners;
    }

    public final PluginConfigFile getConfiguration() {
        return config;
    }

    public final String getPluginName() {
        return getDescription().getFullName();
    }

    public static SpigotPlugin getInstance() {
        return plugin;
    }
}
