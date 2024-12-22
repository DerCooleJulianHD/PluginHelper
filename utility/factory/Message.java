package me.xcuzimsmart.utils.factory;

import me.xcuzimsmart.utils.MinecraftPlugin;
import org.bukkit.ChatColor;

public final class Message {

    public static String factorizeMessage(MinecraftPlugin plugin, MessageType type, String input) {
        return plugin.prefix() + (type.color + input);
    }


    public static String resetColors(String input) {
        return ChatColor.stripColor(input);
    }


    public String translateColorCodes(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }


    public enum MessageType {
        ERROR(ChatColor.RED),
        LOG(ChatColor.GRAY),
        INFO(ChatColor.BLUE),
        COMMAND_USAGE(ChatColor.BLUE),
        WARN(ChatColor.YELLOW),
        SUCCESS_ACTION(ChatColor.GREEN);

        private final ChatColor color;

        MessageType(ChatColor color) {
            this.color = color;
        }

        public ChatColor getColor() {
            return color;
        }
    }
}

