package de.xcuzimsmart.pluginhelper.code.git.branch.main.utils;

import de.xcuzimsmart.pluginhelper.code.git.branch.main.interfaces.Prefixable;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public final class MessageBuilder {

    public static final String COMMAND_NO_PERMISSION = ChatColor.RED + "You doun't have the permission to execute this command.";
    public static final String COMMAND_NO_PLAYER_INSTANCE = ChatColor.RED + "Only players can execute this type of command.";

    public static String buildMessageOutput(String prefix, String message) {
        StringBuilder out = new StringBuilder();
        if (prefix != null) out.append(prefix);
        out.append(message);
        return ChatColor.translateAlternateColorCodes('&', out.toString());
    }

    public static String build(Plugin plugin, String message) {
        if (!(plugin instanceof Prefixable prefixable)) return buildMessageOutput(null, message);
        return buildMessageOutput(prefixable.getPrefix(), message);
    }

    public static String message(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
