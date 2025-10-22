package de.xcuzimsmart.pluginhelper.code.main.java.command;

import de.xcuzimsmart.pluginhelper.code.main.java.exceptions.CommandExecuteException;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Abstract;
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
import java.util.logging.Level;

public abstract class PluginCommand implements CommandExecutor, TabCompleter {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();

    protected final CommandInfo info = getClass().getDeclaredAnnotation(CommandInfo.class);;

    public PluginCommand() {
        Validate.notNull(this.info, getClass().getSimpleName() + " misses CommandInfo Annotation");
        plugin.getServer().getPluginCommand(info.name()).setExecutor(this);
    }

    @Override // [from: CommandExecutor]
    @Abstract
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return tryToExecuteCommand(sender, args);
    }

    @Override
    @Abstract
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return this.onTabComplete(commandSender, strings);
    }

    final boolean tryToExecuteCommand(CommandSender sender, String[] args) {
        try {
            if (this.requiresPermission() && (!sender.hasPermission(info.permission()))) {
                Messanger.broadcast(sender, MessageBuilder.COMMAND_NO_PERMISSION);
                return false;
            }

            if (info.requiresPlayer()) {
                if (!isPlayer(sender)) {
                    Messanger.broadcast(sender, MessageBuilder.COMMAND_NO_PLAYER_INSTANCE);
                    return false;
                }

                this.execute((Player) sender, args);
                return true;
            }

            execute(sender, args);
            return true;
        } catch (Exception e) {
            SpigotPlugin.getPluginLogger().log(Level.SEVERE, "couldn't execute command: '" + info.name() + "'.", e);
        }

        return false;
    }

    @Abstract
    public void execute(CommandSender sender, String[] args) {}

    @Abstract
    public void execute(Player player, String[] args) {}

    @Abstract
    public List<String> onTabComplete(CommandSender sender, String[] strings) {
        return List.of();
    }

    public final void sendSyntax(CommandSender sender, String... args) {
        final StringBuilder builder = new StringBuilder();
        for (String arg : args) builder.append(arg).append("&7, ").append(ChatColor.AQUA);
        Messanger.broadcast(sender, "&cSyntax: &8/&7" + getInfo().name() + " &b" + builder);
    }

    public final CommandInfo getInfo() {
        return info;
    }

    public final boolean requiresPermission() {
        return info != null && info.permission().isEmpty();
    }

    public final boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public SpigotPlugin plugin() {
        return plugin;
    }
}


