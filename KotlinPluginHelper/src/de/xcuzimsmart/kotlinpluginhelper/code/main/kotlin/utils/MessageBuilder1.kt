package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.utils

import de.xcuzimsmart.pluginhelper.code.main.kotlin.plugin.Prefixable
import de.xcuzimsmart.pluginhelper.code.main.kotlin.plugin.SpigotPlugin
import org.bukkit.ChatColor

object MessageBuilder {
    val COMMAND_NO_PERMISSION: String =
        ChatColor.RED.toString() + "You doun't have the permission to execute this command."
    val COMMAND_NO_PLAYER_INSTANCE: String = ChatColor.RED.toString() + "Only players can execute this type of command."

    fun buildMessageOutput(prefix: String?, message: String?): String {
        val out = StringBuilder()
        if (prefix != null) out.append(prefix)
        out.append(message)
        return Colorize.translateColorCodes(out.toString())
    }

    fun build(plugin: SpigotPlugin?, message: String?): String {
        if (plugin !is Prefixable) return buildMessageOutput(null, message)
        return buildMessageOutput(plugin.getPrefix(), message)
    }

    fun message(message: String?): String {
        return Colorize.translateColorCodes(message)
    }
}
