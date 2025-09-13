package de.code.test.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalScoreboard {

    public final Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
    public final Objective objective;
    final Map<Integer, String> scores = new HashMap<>();

    public GlobalScoreboard() {
        this.objective = board.registerNewObjective("display", "dummy");
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void setTitle(String s) {
        this.objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', s));
    }

    public void addToPlayer(Player player) {
        player.setScoreboard(this.board);
    }

    public int getScoresSet() {
        return scores.size();
    }

    public Map<Integer, String> getScores() {
        return scores;
    }

    @Nullable
    final EntryName getEntryNameByScore(int score) {
        for (EntryName name : EntryName.values()) {
            if (score == name.getEntry())
                return name;
        }

        return null;
    }

    final Team getEntryTeam(int score) {
        final EntryName name = getEntryNameByScore(score);
        if (name == null) return null;
        Team team = this.board.getEntryTeam(name.getEntryName());
        if (team != null) return team;
        team = this.board.registerNewTeam(name.name());
        team.addEntry(name.getEntryName());
        return team;
    }

    final void showScore(int score) {
        final EntryName name = getEntryNameByScore(score);
        if (name == null) return;
        if (objective.getScore(name.entryName).isScoreSet()) return;
        objective.getScore(name.entryName).setScore(score);
    }

    final void hideScore(int score) {
        final EntryName name = getEntryNameByScore(score);
        if (name == null) return;
        if (!objective.getScore(name.entryName).isScoreSet()) return;
        board.resetScores(name.entryName);
    }

    public void setLine(int score, String s) {
        this.scores.put(score, s);
    }

    public void addLine(String s) {
        this.setLine(scores.size(), s);
    }

    public void removeLine(int i) {
        this.scores.remove(i);
    }

    public void setLines(List<String> lines) {
        lines.forEach(this::addLine);
    }

    public void clear() {
        this.scores.keySet().forEach(this::hideScore);
        this.scores.clear();
    }

    public void updateScoreboard() {
        this.scores.forEach((score, s) -> {
            final Team team = getEntryTeam(score);
            if (team == null) return;
            if (s.length() > 16) return;
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', s));
            this.showScore(score);
        });
    }
}
