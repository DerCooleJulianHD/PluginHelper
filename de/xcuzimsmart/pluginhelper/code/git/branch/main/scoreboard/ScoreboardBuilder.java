package de.xcuzimsmart.pluginhelper.code.git.branch.main.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ScoreboardBuilder {

    protected final Scoreboard scoreboard;

    protected Objective objective;

    public ScoreboardBuilder(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        this.objective = null;
    }

    public void setLine(int id, String s) {
        this.setLine(id, s, null);
    }

    public void setLine(int id, String prefix, String suffix) {
        Team entry = getEntryTeam(id);
        if (entry == null) return;
        if (prefix != null && prefix.length() <= 16) entry.setPrefix(prefix);
        if (suffix != null && suffix.length() <= 16) entry.setSuffix(suffix);
        this.showScore(id);
    }

    public void addToPlayer(Player player) {
        player.setScoreboard(this.scoreboard);
    }

    public void addLine(String s) {
        this.setLine(scoreboard.getEntries().size(), s);
    }

    public void removeLine(int id) {
        this.hideScore(id);
    }

    public void setLines(List<String> lines) {
        lines.forEach(this::addLine);
    }

    public void setTitle(String s) {
       if (objective != null) objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', s));
    }

    public String getTitle() {
        return objective == null ? "" : objective.getDisplayName();
    }

    public void clear() {
        scoreboard.getEntries().forEach(scoreboard::resetScores);
    }

    @Nullable
    private EntryName getEntryNameByScore(int id) {
        for (EntryName name : EntryName.values()) {
            if (id == name.getEntry())
                return name;
        }

        return null;
    }

    private Team getEntryTeam(int id) {
        final EntryName name = getEntryNameByScore(id);
        if (name == null) return null;
        Team team = scoreboard.getEntryTeam(name.getEntryName());
        if (team != null) return team;
        team = scoreboard.registerNewTeam(name.name());
        team.addEntry(name.getEntryName());
        return team;
    }

    private void showScore(int id) {
        final EntryName name = getEntryNameByScore(id);
        if (name == null) return;
        if (objective == null) return;
        if (objective.getScore(name.entryName).isScoreSet()) return;
        objective.getScore(name.entryName).setScore(id);
    }

    private void hideScore(int id) {
        final EntryName name = getEntryNameByScore(id);
        if (name == null) return;
        if (objective == null) return;
        if (!objective.getScore(name.entryName).isScoreSet()) return;
        scoreboard.resetScores(name.entryName);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Objective objective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public void addObjective(String key, String criteria, String title, DisplaySlot displaySlot, boolean replace) {
        if (replace && hasObjective(key)) scoreboard.getObjective(key).unregister();

        if (hasObjective(key)) return;
        Objective o = scoreboard.registerNewObjective(key, criteria);
        o.setDisplayName(title);
        o.setDisplaySlot(displaySlot);

        if (this.objective == null) setObjective(o);
        else scoreboard.getObjectives().add(o);
    }

    public boolean hasObjective(String key) {
        return scoreboard.getObjective(key) != null;
    }
}
