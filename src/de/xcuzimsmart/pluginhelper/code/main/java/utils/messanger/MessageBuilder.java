package de.xcuzimsmart.pluginhelper.code.main.java.utils.messanger;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.interfaces.Prefixable;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import org.bukkit.ChatColor;

public final class MessageBuilder {

    public static final String COMMAND_NO_PERMISSION = ChatColor.RED + "You doun't have the permission to execute this command.";
    public static final String COMMAND_NO_PLAYER_INSTANCE = ChatColor.RED + "Only players can execute this type of command.";

    public static String buildMessageOutput(String prefix, String message) {
        final StringBuilder out = new StringBuilder();
        if (prefix != null) out.append(prefix);
        out.append(message);
        return Colorize.translateColorCodes(out.toString());
    }

    public static String build(SpigotPlugin plugin, String message) {
        if (!(plugin instanceof Prefixable prefixable)) return buildMessageOutput(null, message);
        return buildMessageOutput(prefixable.getPrefix(), message);
    }

    public static String message(String message) {
        return Colorize.translateColorCodes(message);
    }
}
