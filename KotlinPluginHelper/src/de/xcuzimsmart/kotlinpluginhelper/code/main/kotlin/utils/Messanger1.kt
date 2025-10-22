package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.utils

import de.xcuzimsmart.pluginhelper.code.main.kotlin.plugin.SpigotPlugin
import net.minecraft.server.v1_8_R3.IChatBaseComponent
import net.minecraft.server.v1_8_R3.PacketPlayOutChat
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle
import org.bukkit.command.CommandSender
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player

object Messanger {
    fun sendTitle(player: Player, title: String?, subtitle: String?) {
        player.sendTitle(title, subtitle)
    }

    fun sendTitle(collection: MutableCollection<out Player?>, title: String?, subtitle: String?) {
        if (collection.isEmpty()) return
        collection.forEach { target: Player? -> Messanger.sendTitle(target!!, title, subtitle) }
    }

    fun sendTitle(player: Player, title: String?, subtitle: String?, fadeIn: Int, stay: Int, fadeOut: Int) {
        val connection = (player as CraftPlayer).getHandle().playerConnection
        val times = PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut)
        connection.sendPacket(times)

        if (subtitle != null) {
            val subTitleComponent = IChatBaseComponent.ChatSerializer.a(
                "{\"text\": \"" + Colorize.translateColorCodes(subtitle) + "\"}"
            )
            val subTitlePacket = PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subTitleComponent)
            connection.sendPacket(subTitlePacket)
        }

        if (title != null) {
            val titleComponent = IChatBaseComponent.ChatSerializer.a(
                "{\"text\": \"" + Colorize.translateColorCodes(title) + "\"}"
            )
            val titlePacket = PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleComponent)
            connection.sendPacket(titlePacket)
        }
    }

    fun sendTitle(
        collection: MutableCollection<out Player?>,
        title: String?,
        subtitle: String?,
        fadeIn: Int,
        stay: Int,
        fadeOut: Int
    ) {
        if (collection.isEmpty()) return
        collection.forEach { target: Player? -> Messanger.sendTitle(target!!, title, subtitle, fadeIn, stay, fadeOut) }
    }

    fun sendActionBar(player: Player, message: String?) {
        (player as CraftPlayer).getHandle().playerConnection.sendPacket(
            PacketPlayOutChat(
                IChatBaseComponent.ChatSerializer.a(
                    "{\"text\": \"" + Colorize.translateColorCodes(message) + "\"}"
                ), 2.toByte()
            )
        )
    }

    fun sendActionBar(collection: MutableCollection<out Player>, content: String?) {
        if (collection.isEmpty()) return
        for (target in collection) sendActionBar(target, content)
    }

    fun broadcast(entity: CommandSender, message: String?) {
        entity.sendMessage(MessageBuilder.build(SpigotPlugin.Companion.getInstance(), message))
    }

    fun broadcast(collection: MutableCollection<out CommandSender?>, message: String?) {
        if (!collection.isEmpty()) collection.forEach { target: CommandSender? ->
            Messanger.broadcast(
                target!!,
                message
            )
        }
    }
}
