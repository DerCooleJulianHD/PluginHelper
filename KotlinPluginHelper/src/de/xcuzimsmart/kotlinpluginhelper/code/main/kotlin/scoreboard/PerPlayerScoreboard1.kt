package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.scoreboard

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Scoreboard
import java.util.*
import java.util.function.Consumer

class PerPlayerScoreboard : PluginScoreboard() {
    fun setScore(player: Player, score: Int, content: String?) {
        if (scoreboard == null) setScoreboard(getPlayerScoreboard(player))
        if (objective == null) return

        if (getScore(score) == null) scores.put(score, Score(objective, score, null, null))

        val entry = getScore(score)

        entry.setPrefix(content)
        entry.show()
    }

    fun setLines(player: Player, lines: LinkedList<String?>) {
        if (lines.isEmpty()) return

        try {
            lines.forEach(Consumer { content: String? ->
                val score = lines.indexOf(content)
                setScore(player, score, content)
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun removeLine(player: Player, score: Int) {
        if (scoreboard == null) setScoreboard(getPlayerScoreboard(player))
        if (objective == null) return

        val entry = getScore(score)

        if (entry == null) return

        scores.remove(score)
        entry.hide()
    }

    fun addLine(player: Player, content: String?) {
        val score = scores.size

        setScore(player, score, content)
    }

    fun setTitle(player: Player, title: String?) {
        if (scoreboard == null) setScoreboard(getPlayerScoreboard(player))

        if (objective == null) return
        objective.setDisplayName(title)
    }

    private fun getPlayerScoreboard(player: Player): Scoreboard? {
        if (player.getScoreboard() == Bukkit.getScoreboardManager()
                .getMainScoreboard()
        ) player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard())
        if (scoreboard == null) setScoreboard(player.getScoreboard())
        return scoreboard
    }
}
