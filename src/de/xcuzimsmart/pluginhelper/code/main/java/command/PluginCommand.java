package de.xcuzimsmart.pluginhelper.code.main.java.command;

import de.xcuzimsmart.pluginhelper.code.main.java.exceptions.CommandExecuteException;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.MessageBuilder;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Messanger;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Logger;

public abstract class PluginCommand implements CommandExecutor, TabCompleter {

    protected final Logger logger = Logger.getLogger(PluginCommand.class.getSimpleName());

    protected final SpigotPlugin plugin;

    protected final CommandInfo info;

    public PluginCommand(SpigotPlugin plugin) {
        this.plugin = plugin;
        this.info = getClass().getDeclaredAnnotation(CommandInfo.class);
        Validate.notNull(this.info, getClass().getName() + " misses CommandInfo Annotation");
    }

    @Override // [from: CommandExecutor]
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            return tryToExecuteCommand(sender, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return this.onTabComplete(commandSender, strings);
    }

    boolean tryToExecuteCommand(CommandSender sender, String[] args) throws CommandExecuteException {
        try {
            if (this.requiresPermission() && (!sender.hasPermission(info.permission()))) {
                Messanger.broadcast(plugin, sender, MessageBuilder.COMMAND_NO_PERMISSION);
                return false;
            }

            if (info.requiresPlayer()) {
                if (!isPlayer(sender)) {
                    Messanger.broadcast(plugin, sender, MessageBuilder.COMMAND_NO_PLAYER_INSTANCE);
                    return false;
                }

                this.execute((Player) sender, args);
                return true;
            }

            this.execute(sender, args);
            return true;
        } catch (Exception e) {
            throw new CommandExecuteException("couldn't execute command: '" + info.name() + "'.", e, this);
        }
    }

    public void execute(CommandSender sender, String[] strings) {}

    public void execute(Player sender, String[] strings) {}

    public List<String> onTabComplete(CommandSender sender, String[] strings) {
        return List.of();
    }

    public void sendSyntax(CommandSender sender, String... args) {
        final StringBuilder builder = new StringBuilder();
        for (String arg : args) builder.append(arg).append("&7, ").append(ChatColor.AQUA);
        Messanger.broadcast(plugin, sender, "&cSyntax: &8/&7" + getInfo().name() + " &b" + builder);
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


