package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.utils

import org.bukkit.ChatColor

object Colorize {
    fun translateColorCodes(str: String): String {
        return ChatColor.translateAlternateColorCodes('&', str)
    }

    fun stripColor(coloredStr: String?): String? {
        return ChatColor.stripColor(coloredStr)
    }
}
