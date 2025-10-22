package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.run

import org.bukkit.ChatColor

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class TimerColor(val brackets: ChatColor, val timer: ChatColor)
