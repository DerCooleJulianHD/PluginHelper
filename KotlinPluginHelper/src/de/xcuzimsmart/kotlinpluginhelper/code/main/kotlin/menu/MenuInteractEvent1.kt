package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.menu

import org.bukkit.entity.Player

class MenuInteractEvent(player: Player?, menu: Menu?, val slot: Int, val clickedIcon: Icon?) : MenuEvent(player, menu)
