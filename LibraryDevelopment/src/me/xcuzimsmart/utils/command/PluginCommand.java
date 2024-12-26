package me.xcuzimsmart.utils.command;

import me.xcuzimsmart.utils.*;
import me.xcuzimsmart.utils.factory.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PluginCommand implements CommandExecutor {

    private final MinecraftPlugin plugin;
    private final String name;

    protected PluginCommand(MinecraftPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        plugin.getPlugin().getCommand(this.name()).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(Message.factorizeMessage(plugin, Message.MessageType.ERROR, "Sorry... but you do not have the required permissions to execute this command."));
            return false;
        }

        if (isPlayerRequired()) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(Message.factorizeMessage(plugin, Message.MessageType.ERROR, "You must be a Player."));
                return false;
            }

            this.checkArgsLength(player, args);
            this.execute(player, command, label, args);
        } else {
            checkArgsLength(sender, args);
            execute(sender, command, label, args);
        }

        return false;
    }

    public void execute(Player player, Command command, String label, String[] args) {}
    public void execute(CommandSender sender, Command command, String label, String[] args) {}

    public abstract Syntax getSyntax();
    public abstract String getPermission();
    public abstract boolean isPlayerRequired();

    public String name() {
        return name;
    }

    public MinecraftPlugin getMinecraftPlugin() {
        return plugin;
    }

    private void checkArgsLength(CommandSender sender, String[] args) {
        if (args.length != getSyntax().getArguments().size()) {
            sendUsage(sender);
        }
    }

    private void sendUsage(CommandSender sender) {
        final StringBuilder args = new StringBuilder();

        for (String arg : getSyntax().getArguments()) {
            args.append(" ").append("<").append(arg).append(">");
        }

        sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GRAY + "/" + getSyntax().getLabel() + " " + ChatColor.BLUE + args + ChatColor.GRAY + ".");
    }
}
