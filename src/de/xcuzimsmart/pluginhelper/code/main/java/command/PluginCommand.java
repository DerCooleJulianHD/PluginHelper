package de.xcuzimsmart.pluginhelper.code.main.java.command;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.interfaces.MinecraftPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.annotations.Abstract;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.messanger.MessageBuilder;
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

    protected final MinecraftPlugin plugin;

    protected final CommandInfo info = getClass().getDeclaredAnnotation(CommandInfo.class);;

    public PluginCommand(MinecraftPlugin plugin) {
        this.plugin = plugin;
        Validate.notNull(this.info, getClass().getSimpleName() + " misses CommandInfo Annotation");
        ((SpigotPlugin) plugin).getServer().getPluginCommand(info.name()).setExecutor(this);
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
            if (!info.permission().isEmpty() && (!sender.hasPermission(info.permission()))) {
                plugin.getMessager().sendMessage(sender, MessageBuilder.COMMAND_NO_PERMISSION);
                return false;
            }

            if (info.requiresPlayer()) {
                if (!(sender instanceof Player player)) {
                    plugin.getMessager().sendMessage(sender, MessageBuilder.COMMAND_NO_PLAYER_INSTANCE);
                    return false;
                }

                this.execute(player, args);
                return true;
            }

            execute(sender, args);
            return true;
        } catch (Exception e) {
            plugin.getPluginLogger().log(Level.SEVERE, "couldn't execute command: '" + info.name() + "'.", e);
        }

        return false;
    }

    public final void sendSyntax(CommandSender sender, String... args) {
        final StringBuilder builder = new StringBuilder();
        for (String arg : args) builder.append(arg).append("&7, ").append(ChatColor.AQUA);
        plugin.getMessager().sendMessage(sender, "&cSyntax: &8/&7" + getInfo().name() + " &b" + builder);
    }

    @Abstract
    public void execute(CommandSender sender, String[] args) {}

    @Abstract
    public void execute(Player player, String[] args) {}

    @Abstract
    public List<String> onTabComplete(CommandSender sender, String[] strings) {
        return List.of();
    }

    public final CommandInfo getInfo() {
        return info;
    }
}


