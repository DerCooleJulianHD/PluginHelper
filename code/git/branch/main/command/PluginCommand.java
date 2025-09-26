package de.xcuzimsmart.pluginhelper.code.git.branch.main.command;

import de.xcuzimsmart.pluginhelper.code.git.branch.main.utils.Messanger;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public abstract class PluginCommand implements CommandExecutor, TabCompleter {

    protected final Logger logger = Logger.getLogger(PluginCommand.class.getSimpleName());

    protected final Plugin plugin;

    protected final CommandInfo info;

    public PluginCommand(Plugin plugin) {
        this.plugin = plugin;
        this.info = getClass().getAnnotation(CommandInfo.class);
        Validate.notNull(this.info, getClass().getName() + " misses CommandInfo Annotation");
        plugin.getServer().getPluginCommand(info.name()).setExecutor(this);
    }

    @Override // [from: CommandExecutor]
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (this.requiresPermission() && (!sender.hasPermission(info.permission()))) {
            Messanger.broadcast(plugin, sender, ChatColor.RED + "You doun't have the permission to execute this command. (missing: " + info.permission() + ")");
            return false;
        }

        if (info.requiresPlayer() && (!isPlayer(sender))) {
            Messanger.broadcast(plugin, sender, ChatColor.RED + "Only players can execute this type of command.");
            return false;
        }

        this.execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] strings);

    public void sendHelp(CommandSender sender, String usage) {
        Messanger.broadcast(plugin, sender, usage);
    }

    public CommandInfo getInfo() {
        return info;
    }

    public boolean requiresPermission() {
        return info != null && info.permission().isEmpty();
    }

    public boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public Logger getLogger() {
        return logger;
    }
}


