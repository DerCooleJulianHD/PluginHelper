package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.utils

import org.bukkit.ChatColor
import org.bukkit.Server
import org.bukkit.plugin.Plugin

object Dependencies {
    // does check if a plugin, which is using a dependency, also has the dependency plugin installed.
    fun hasDependencyPlugin(server: Server, using: Plugin?, pluginNameOfDepend: String?): Boolean {
        val manager = server.getPluginManager()

        // dependency-plugin which needs to be installed.
        val depend = manager.getPlugin(pluginNameOfDepend)

        // here when dependency-plugin is not installed:
        if (depend == null) {
            server.getConsoleSender()
                .sendMessage(ChatColor.RED.toString() + "'" + pluginNameOfDepend + "' plugin not found, please install it before continue.")
            if (using != null && using.isEnabled()) manager.disablePlugin(using)
            return false
        }

        if (!depend.isEnabled()) manager.enablePlugin(depend)
        return true
    }
}





