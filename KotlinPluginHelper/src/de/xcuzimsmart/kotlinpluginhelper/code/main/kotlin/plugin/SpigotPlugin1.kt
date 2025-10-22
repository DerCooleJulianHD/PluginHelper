package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin

import de.xcuzimsmart.pluginhelper.code.main.kotlin.listener.ListenerManager
import de.xcuzimsmart.pluginhelper.code.main.kotlin.run.Timer
import de.xcuzimsmart.pluginhelper.code.main.kotlin.scoreboard.GlobalScoreboard
import de.xcuzimsmart.pluginhelper.code.main.kotlin.scoreboard.PluginScoreboard
import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.Abstract
import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.FileManager
import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.MessageBuilder
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

abstract class SpigotPlugin : JavaPlugin(), MinecraftPlugin {

    override var prefix: String? = null /* <-- by default */

    override var scoreboard: PluginScoreboard? = null

    override var listenerManager: ListenerManager? = null

    val consoleSender: ConsoleCommandSender = Bukkit.getConsoleSender()

    @Abstract
    override fun onLoad() {
        // creating an instance.
        instance = this

        // creating the Plugin folder first.
        FileManager.mkdirIfNotExists(dataFolder)

        // creating the config file and load it.
        Companion.config = PluginConfigFile(true)
        getPlugin()!!.onLoad() // invoke the 'onLoad()' on sub class.
    }

    @Abstract
    override fun onEnable() {
        this.listenerManager = ListenerManager()

        this.scoreboard = GlobalScoreboard() // creating a default scoreboard.

        getPlugin()!!.onEnable() // invoke the 'onEnable()' on sub class.
        this.sendEnableMessage() // printing out to console, that the plugin has been enabled.
    }

    @Abstract
    override fun onDisable() {
        getPlugin()!!.onDisable() // invoke the 'onDisable()' on sub class.
        this.sendDisableMessage() // printing out to console, that the plugin has been disabled.
    }

    fun getPrefix(): String? {
        if (this.prefix != null) return this.prefix
        if (Companion.config == null) return null

        val read: String? = Companion.config!!.getPrefix()

        if (read != null && (!read.isEmpty())) return read
        return null
    }

    override fun sendEnableMessage() {
        consoleSender.sendMessage(
            MessageBuilder.build(
                this,
                ChatColor.GREEN.toString() + getPluginName() + " has been successfully enabled."
            )
        )
    }

    override fun sendDisableMessage() {
        consoleSender.sendMessage(
            MessageBuilder.build(
                this,
                ChatColor.RED.toString() + getPluginName() + " has been successfully disabled."
            )
        )
    }

    fun createTimer(): Timer {
        return Timer(0, 20)
    }

    fun getPlugin(): Plugin? {
        return getAsJavaPlugin()
    }

    fun getAsJavaPlugin(): JavaPlugin? {
        return instance
    }

    fun setPrefix(prefix: String?) {
        this.prefix = prefix
    }

    fun getScoreboard(): PluginScoreboard? {
        return scoreboard
    }

    fun setScoreboard(scoreboard: PluginScoreboard?) {
        this.scoreboard = scoreboard
    }

    fun getListenerManager(): ListenerManager? {
        return listenerManager
    }

    fun getConfiguration(): PluginConfigFile? {
        return Companion.config
    }

    fun getPluginName(): String {
        return description.fullName
    }

    companion object {
        var instance: SpigotPlugin? = null
            protected set

        @JvmStatic
        protected var config: PluginConfigFile? = null

        var pluginLogger: Logger? = Logger.getLogger(SpigotPlugin::class.java.getSimpleName())
            protected set
    }
}
