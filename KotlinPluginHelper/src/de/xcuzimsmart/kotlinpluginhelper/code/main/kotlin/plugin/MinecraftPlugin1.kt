package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin

import de.xcuzimsmart.pluginhelper.code.main.kotlin.listener.ListenerManager
import de.xcuzimsmart.pluginhelper.code.main.kotlin.scoreboard.PluginScoreboard
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

interface MinecraftPlugin : Prefixable {
    // prits a message to console.
    fun sendEnableMessage()

    fun sendDisableMessage()

    // Bukkit Plugin
    val plugin: Plugin?

    val asJavaPlugin: JavaPlugin?

    var scoreboard: PluginScoreboard?

    val configuration: PluginConfigFile?

    val pluginName: String?

    val listenerManager: ListenerManager?
}
