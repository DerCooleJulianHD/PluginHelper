package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.command

import de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin.SpigotPlugin
import de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.utils.Abstract
import de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.utils.MessageBuilder
import de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.utils.Messanger
import org.apache.commons.lang.Validate
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import java.util.logging.Level

abstract class PluginCommand : CommandExecutor, TabCompleter {
    protected val plugin: de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin.SpigotPlugin = _root_ide_package_.de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin.SpigotPlugin.Companion.getInstance()

    val info: de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.command.CommandInfo? = javaClass.getDeclaredAnnotation<CommandInfo?>(CommandInfo::class.java)

    init {
        Validate.notNull(this.info, javaClass.getSimpleName() + " misses CommandInfo Annotation")
        plugin.getServer().getPluginCommand(info!!.name).setExecutor(this)
    }

    // [from: CommandExecutor]
    @Abstract
    override fun onCommand(sender: CommandSender, command: Command?, label: String?, args: Array<String?>?): Boolean {
        return tryToExecuteCommand(sender, args)
    }

    @Abstract
    override fun onTabComplete(
        commandSender: CommandSender?,
        command: Command?,
        s: String?,
        strings: Array<String?>?
    ): MutableList<String?> {
        return this.onTabComplete(commandSender, strings)
    }

    fun tryToExecuteCommand(sender: CommandSender, args: Array<String?>?): Boolean {
        try {
            if (this.requiresPermission() && (!sender.hasPermission(info!!.permission))) {
                Messanger.broadcast(sender, MessageBuilder.COMMAND_NO_PERMISSION)
                return false
            }

            if (info!!.requiresPlayer) {
                if (!isPlayer(sender)) {
                    Messanger.broadcast(sender, MessageBuilder.COMMAND_NO_PLAYER_INSTANCE)
                    return false
                }

                this.execute(sender as Player, args)
                return true
            }

            execute(sender, args)
            return true
        } catch (e: Exception) {
            SpigotPlugin.Companion.getPluginLogger()
                .log(Level.SEVERE, "couldn't execute command: '" + info!!.name + "'.", e)
        }

        return false
    }

    @Abstract
    fun execute(sender: CommandSender?, args: Array<String?>?) {
    }

    @Abstract
    fun execute(player: Player?, args: Array<String?>?) {
    }

    @Abstract
    fun onTabComplete(sender: CommandSender?, strings: Array<String?>?): MutableList<String?> {
        return mutableListOf<String?>()
    }

    fun sendSyntax(sender: CommandSender, vararg args: String?) {
        val builder = StringBuilder()
        for (arg in args) builder.append(arg).append("&7, ").append(ChatColor.AQUA)
        Messanger.broadcast(sender, "&cSyntax: &8/&7" + this.info!!.name + " &b" + builder)
    }

    fun requiresPermission(): Boolean {
        return info != null && info.permission.isEmpty()
    }

    fun isPlayer(sender: CommandSender?): Boolean {
        return sender is Player
    }

    fun plugin(): SpigotPlugin {
        return plugin
    }
}


