package de.xcuzimsmart.pluginhelper.code.main.java.scoreboard;

import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.score.Score;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.LinkedList;

public final class PerPlayerScoreboard extends PluginScoreboard {

    public void setScore(Player player, int score, String content) {
        if (scoreboard == null) setScoreboard(getPlayerScoreboard(player));
        if (objective == null) return;

        if (getScore(score) == null) scores.put(score, new Score(objective, score, null, null));

        final Score entry = getScore(score);

        entry.setPrefix(content);
        entry.show();
    }

    public void setLines(Player player, LinkedList<String> lines) {
        if (lines.isEmpty()) return;

        try {
            lines.forEach(content -> {
                final int score = lines.indexOf(content);
                setScore(player, score, content);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeLine(Player player, int score) {
        if (scoreboard == null) setScoreboard(getPlayerScoreboard(player));
        if (objective == null) return;

        final Score entry = getScore(score);

        if (entry == null) return;

        scores.remove(score);
        entry.hide();
    }

    public void addLine(Player player, String content) {
        final int score = scores.size();

        setScore(player, score, content);
    }

    public void setTitle(Player player, String title) {
        if (scoreboard == null) setScoreboard(getPlayerScoreboard(player));

        if (objective == null) return;
        objective.setDisplayName(title);
    }

    private Scoreboard getPlayerScoreboard(Player player) {
        if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        if (scoreboard == null) setScoreboard(player.getScoreboard());
        return scoreboard;
    }
}
