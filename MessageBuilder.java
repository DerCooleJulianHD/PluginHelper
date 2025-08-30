package minecraft.lib.plugins.utils.message;

import minecraft.lib.plugins.extension.PluginLibrary;
import minecraft.lib.plugins.utils.Prefixable;
import org.bukkit.ChatColor;

public class MessageBuilder {

    public static String buildMessageOutput(String prefix, String message) {
        StringBuilder out = new StringBuilder();
        if (prefix != null) out.append(prefix);
        out.append(message);
        return ChatColor.translateAlternateColorCodes('&', out.toString());
    }

    public static String build(PluginLibrary library, String message) {
        if (!(library instanceof Prefixable prefixable)) return buildMessageOutput(null, message);
        return buildMessageOutput(prefixable.getPrefix(), message);
    }

    public static String message(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
