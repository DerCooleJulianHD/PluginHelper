package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.scoreboard

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Team
import java.util.*

class Team @JvmOverloads constructor(
    val scoreboard: PluginScoreboard?,
    var name: String,
    var prefix: String? = null,
    var color: ChatColor? = ChatColor.WHITE
) {
    var entryName: String? = name.uppercase(Locale.getDefault())

    var entry: Team? = null

    fun addPlayer(player: Player) {
        if (entry != null) entry!!.addEntry(player.name)
    }

    companion object {
        fun builder(
            scoreboard: PluginScoreboard?,
            name: String
        ): de.xcuzimsmart.pluginhelper.code.main.kotlin.scoreboard.Team {
            return Team(scoreboard, name)
        }
    }
}
