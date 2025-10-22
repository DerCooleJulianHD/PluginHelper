package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.scoreboard

import org.bukkit.Bukkit
import org.bukkit.scoreboard.DisplaySlot
import java.util.*
import java.util.function.Consumer

class GlobalScoreboard : PluginScoreboard() {
    init {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard()

        this.objective = getOrDefault("display", this.addObjective("display", null, "dummy", DisplaySlot.SIDEBAR))
    }

    fun setScore(score: Int, content: String?) {
        if (scoreboard == null) return
        if (objective == null) return

        if (getScore(score) == null) scores.put(score, Score(objective, score, null, null))

        val entry = getScore(score)

        entry.setPrefix(content)
        entry.show()
    }

    fun setLines(lines: LinkedList<String?>) {
        if (lines.isEmpty()) return

        try {
            lines.forEach(Consumer { content: String? ->
                val score = lines.indexOf(content)
                setScore(score, content)
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun removeLine(score: Int) {
        if (scoreboard == null) return
        if (objective == null) return

        val entry = getScore(score)

        if (entry == null) return

        scores.remove(score)
        entry.hide()
    }

    fun addLine(content: String?) {
        val score = scores.size

        setScore(score, content)
    }

    fun setTitle(title: String?) {
        if (scoreboard == null && objective == null) return

        objective.setDisplayName(title)
    }
}
