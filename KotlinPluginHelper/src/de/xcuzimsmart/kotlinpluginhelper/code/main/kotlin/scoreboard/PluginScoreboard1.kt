package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.scoreboard

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import kotlin.collections.HashMap
import kotlin.collections.MutableMap

abstract class PluginScoreboard {
    protected var scoreboard: Scoreboard? = null

    var objective: Objective? = null
        protected set

    val teams: MutableMap<String?, Team?> = HashMap<String?, Team?>()

    val scoresSet: MutableMap<Int?, Score?> = HashMap<Int?, Score?>()

    protected fun getObjectiveByKey(k: String?): Objective? {
        return scoreboard!!.getObjective(k)
    }

    fun addObjective(k: String?, title: String?, criteria: String?, slot: DisplaySlot?): Objective? {
        val obj = scoreboard!!.registerNewObjective(k, criteria)

        if (title != null) obj.displayName = title
        if (slot != null) obj.displaySlot = slot

        this.objective = obj
        return obj
    }

    protected fun getOrDefault(k: String?, defaultValue: Objective?): Objective? {
        if (hasObjective(k)) return getObjectiveByKey(k)
        return defaultValue
    }

    // function
    fun hasTeams(): Boolean {
        return !teams.isEmpty()
    }

    fun getTeamByName(name: String?): Team? {
        return teams.get(name)
    }

    fun containsTeam(name: String?): Boolean {
        return !teams.isEmpty() && teams.containsKey(name)
    }

    fun addTeam(name: String, prefix: String?, color: ChatColor?): Team {
        val team = Team(this, name, prefix, if (color == null) ChatColor.WHITE else color)

        if (scoreboard!!.getTeam(name) == null) scoreboard!!.registerNewTeam(name)

        team.setEntry(scoreboard!!.getTeam(name))
        team.entry?.prefix = (prefix + color)
        teams.put(name, team)

        return team
    }

    fun addToPlayer(player: Player) {
        player.setScoreboard(this.scoreboard)
    }

    fun hasObjective(k: String?): Boolean {
        return getObjectiveByKey(k) != null
    }

    fun removeTeam(name: String?) {
        if (!containsTeam(name)) return

        val team = getTeamByName(name)

        if (team == null) return

        if (team.getEntry() != null) team.getEntry().unregister()
        teams.remove(name)
    }

    fun getScore(id: Int): Score? {
        return scoresSet.get(id)
    }

    protected fun setScoreboard(scoreboard: Scoreboard) {
        this.scoreboard = scoreboard
    }
}
