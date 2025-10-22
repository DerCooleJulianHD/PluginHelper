package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.scoreboard

import org.bukkit.ChatColor
import org.bukkit.scoreboard.Objective

class Score(val objective: Objective?, val id: Int, var prefix: String?, var suffix: String?) {
    val entry: org.bukkit.scoreboard.Team?

    init {
        this.entry = this.entryTeam
    }

    fun setPrefix(prefix: String) {
        this.prefix = prefix
        if (entry == null) return

        if (prefix.length <= 16) entry.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix))
    }

    fun setSuffix(suffix: String) {
        this.suffix = suffix
        if (entry == null) return

        if (suffix.length <= 16) entry.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix))
    }

    private val entryNameByScore: EntryName?
        get() {
            for (name in EntryName.entries) {
                if (id == name.getEntry()) return name
            }

            return null
        }

    private val entryTeam: Team?
        get() {
            if (objective == null) return null

            val parent = objective.getScoreboard()
            val name = this.entryNameByScore

            if (name == null) return null

            var team = parent.getEntryTeam(name.entryName)
            if (team != null) return team
            team = parent.registerNewTeam(name.name)
            team.addEntry(name.entryName)

            return team
        }

    fun show() {
        if (objective == null) return
        val name = this.entryNameByScore
        if (name == null) return
        if (objective.getScore(name.entryName).isScoreSet()) return
        objective.getScore(name.entryName).setScore(id)
    }

    fun hide() {
        if (objective == null) return
        val parent = objective.getScoreboard()
        val name = this.entryNameByScore
        if (name == null) return
        if (!objective.getScore(name.entryName).isScoreSet()) return
        parent.resetScores(name.entryName)
    }

    fun getPrefix(): String? {
        return prefix
    }

    fun getSuffix(): String? {
        return suffix
    }

    enum class EntryName(val entry: Int, entryName: String) {
        SCORE_0(0, ChatColor.GRAY.toString()),
        SCORE_1(1, ChatColor.DARK_GRAY.toString()),
        SCORE_2(2, ChatColor.RED.toString()),
        SCORE_3(3, ChatColor.DARK_RED.toString()),
        SCORE_4(4, ChatColor.BLUE.toString()),
        SCORE_5(5, ChatColor.DARK_BLUE.toString()),
        SCORE_6(6, ChatColor.YELLOW.toString()),
        SCORE_7(7, ChatColor.GOLD.toString()),
        SCORE_8(8, ChatColor.GREEN.toString()),
        SCORE_9(9, ChatColor.DARK_GREEN.toString()),
        SCORE_10(10, ChatColor.AQUA.toString()),
        SCORE_11(11, ChatColor.BOLD.toString()),
        SCORE_12(12, ChatColor.LIGHT_PURPLE.toString()),
        SCORE_13(13, ChatColor.DARK_AQUA.toString()),
        SCORE_14(14, ChatColor.RESET.toString()),
        SCORE_15(15, ChatColor.ITALIC.toString());

        val entryName: String?

        init {
            this.entryName = entryName
        }
    }
}
