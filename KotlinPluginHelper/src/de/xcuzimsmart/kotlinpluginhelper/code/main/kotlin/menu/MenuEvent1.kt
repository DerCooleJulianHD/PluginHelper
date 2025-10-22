package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.menu

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

abstract class MenuEvent(p: Player?, val menu: Menu?) : PlayerEvent(p) {
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        val handlerList: HandlerList = HandlerList()
    }
}
