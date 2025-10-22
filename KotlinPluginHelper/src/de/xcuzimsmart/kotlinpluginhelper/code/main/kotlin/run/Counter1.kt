package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.run

import de.xcuzimsmart.pluginhelper.code.main.kotlin.plugin.SpigotPlugin
import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.Abstract
import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.Executable
import org.bukkit.scheduler.BukkitRunnable

abstract class Counter(val delay: Long, val period: Long) : BukkitRunnable(), Executable {
    @get:Abstract
    @set:Abstract
    open var isRunning: Boolean = false

    init {
        this.runTaskTimer(
            SpigotPlugin.Companion.getInstance(),
            delay,
            period
        )
    }

    override fun run() {
        try {
            execute()
        } catch (e: Exception) {
            this.cancel()
        }
    }

    fun start() {
        if (!this.isRunning) this.isRunning = true
    }

    fun stop() {
        if (this.isRunning) this.isRunning = false
    }
}
