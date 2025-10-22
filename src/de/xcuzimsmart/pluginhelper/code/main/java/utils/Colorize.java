package de.xcuzimsmart.pluginhelper.code.main.java.utils;

import org.bukkit.ChatColor;

public final class Colorize {

    public static String translateColorCodes(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String stripColor(String coloredStr) {
        return ChatColor.stripColor(coloredStr);
    }
}
