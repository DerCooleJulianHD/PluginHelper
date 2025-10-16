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

public abstract class MCSpigotPlugin extends JavaPlugin implements SpigotPlugin {

    protected String prefix = null; /* <-- by default */

    protected Scoreboard scoreboard;

    protected PluginConfigFile config;

    protected ListenerBundle listeners;
    protected CommandManager commandManager;

    @Override
    public void onLoad() {
        // creating the Plugin folder first.
        if (!getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }

        // creating the config file and load it.
        this.config = new PluginConfigFile(this, true);
        getPlugin().onLoad(); // invoke the 'onLoad()' on sub class.
    }

    @Override
    public void onEnable() {
        // creating both bundles.
        this.listeners = new ListenerBundle(this);
        this.commandManager = new CommandManager(this); // ...CommandManager is also a Bundle.

        this.scoreboard = new GlobalScoreboard(); // creating a default scoreboard.

        getPlugin().onEnable(); // invoke the 'onEnable()' on sub class.
        this.sendEnableMessage(); // printing out to console, that the plugin has been enabled.
    }

    @Override
    public void onDisable() {
        getPlugin().onDisable(); // invoke the 'onDisable()' on sub class.
        this.sendDisableMessage(); // printing out to console, that the plugin has been disabled.
    }

    @Override
    public String getPrefix() {
        if (this.prefix != null) return this.prefix;
       
        final String read = getPrefixFromConfig();
       
        if (read != null && (!read.isEmpty())) return read;
        return null;
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
        return this;
    }

    @Override
    public String getPrefixFromConfig() {
        return config.getPrefix();
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void setConfigPrefix(String prefix) {
        config.setPrefix(prefix);
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
}
