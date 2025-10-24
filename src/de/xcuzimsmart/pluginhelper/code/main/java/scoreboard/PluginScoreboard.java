package de.xcuzimsmart.pluginhelper.code.main.java.scoreboard;

import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.score.Score;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public abstract class PluginScoreboard {

    protected Scoreboard scoreboard;

    protected Objective objective;

    protected final Map<String, Team> teams = new HashMap<>();

    protected final Map<Integer, Score> scores = new HashMap<>();

    public final Objective getObjective() {
        return objective;
    }

    @Nullable
    protected final Objective getObjectiveByKey(String k) {
        return scoreboard.getObjective(k);
    }

    @Nullable
    public final Objective addObjective(String k, String title, String criteria, DisplaySlot slot) {
        final Objective obj = scoreboard.registerNewObjective(k, criteria);

        if (title != null) obj.setDisplayName(title);
        if (slot != null) obj.setDisplaySlot(slot);

        this.setObjective(obj);
        return obj;
    }

    protected final Objective getOrDefault(String k, Objective defaultValue) {
        if (hasObjective(k)) return getObjectiveByKey(k);
        return defaultValue;
    }

    public final Map<String, Team> getTeams() {
        return teams;
    }

    public final boolean hasTeams() {
        return !teams.isEmpty();
    }

    @Nullable
    public final Team getTeamByName(String name) {
        return teams.get(name);
    }

    public final boolean containsTeam(String name) {
        return !teams.isEmpty() && teams.containsKey(name);
    }

    public final Team addTeam(String name, String prefix, ChatColor color) {
        final Team team = new Team(this, name, prefix, color == null ? ChatColor.WHITE : color);
        org.bukkit.scoreboard.Team entry = scoreboard.getTeam(name);

        if (entry == null) entry = scoreboard.registerNewTeam(name);

        entry.setPrefix(prefix + color);
        team.setEntry(entry);
        teams.put(name, team);

        return team;
    }

    public final void addToPlayer(Player player) {
        player.setScoreboard(this.scoreboard);
    }

    public final boolean hasObjective(String k) {
        return getObjectiveByKey(k) != null;
    }

    public final void removeTeam(String name) {
        if (!containsTeam(name)) return;

        final Team team = getTeamByName(name);

        if (team == null) return;

        if (team.getEntry() != null) team.getEntry().unregister();
        teams.remove(name);
    }

    public final Score getScore(int id) {
        return scores.get(id);
    }

    public final Map<Integer, Score> getScoresSet() {
        return scores;
    }

    protected final void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    protected final void setObjective(Objective objective) {
        this.objective = objective;
    }
}
