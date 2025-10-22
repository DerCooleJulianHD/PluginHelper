package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.run

import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.Messanger
import org.apache.commons.lang.Validate
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

@TimerColor(brackets = ChatColor.WHITE, timer = ChatColor.WHITE) /* <-- by default */
class Timer(delay: Long, period: Long) : Counter(delay, period) {
    val shown: MutableList<UUID?> = ArrayList<UUID?>()

    var hours: Int
    var minutes: Int
    var seconds: Int

    val color: TimerColor = javaClass.getDeclaredAnnotation<TimerColor>(TimerColor::class.java)

    init {
        Validate.notNull(color, "Timer must have Color Annotation!")

        this.hours = 0
        this.minutes = 0
        this.seconds = 0
    }

    override fun execute() {
        if (isRunning()) {
            if (seconds == 59) {
                minutes++
                seconds = 0
            } else if (minutes == 59) {
                hours++
                minutes = 0
            } else {
                seconds++
            }
        }

        sendActionBar()
    }

    fun showTo(player: Player) {
        this.shown.add(player.getUniqueId())
    }

    fun hideFrom(player: Player) {
        if (shown.contains(player.getUniqueId())) this.shown.remove(player.getUniqueId())
    }

    fun setTime(hours: Int, minutes: Int, seconds: Int) {
        this.hours = hours
        this.minutes = minutes
        this.seconds = seconds
    }

    override fun isRunning(): Boolean {
        return running
    }

    override fun setRunning(running: Boolean) {
        this.running = running
    }

    override fun toString(): String {
        return (color.brackets.toString() + "["
                + color.timer + (if (hours < 10) "0" + hours else hours) + ":" + (if (minutes < 10) "0" + minutes else minutes) + ":" + (if (seconds < 10) "0" + seconds else seconds)
                + color.brackets + "]")
    }

    private fun sendActionBar() {
        if (shown.isEmpty()) return

        for (uuid in shown) {
            val player = Bukkit.getPlayer(uuid)

            if (player == null) return
            if (!player.isOnline()) return

            Messanger.sendActionBar(player, this.toString())
        }
    }
}
