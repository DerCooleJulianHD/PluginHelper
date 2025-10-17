package de.xcuzimsmart.pluginhelper.code.main.java.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.LinkedList;

public final class GlobalScoreboard extends PluginScoreboard {

    public GlobalScoreboard() {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        this.objective = getOrDefault("display", this.addObjective("display", null, "dummy", DisplaySlot.SIDEBAR));
    }

    public void setScore(int score, String content) {
        if (scoreboard == null) return;
        if (objective == null) return;

        if (getScore(score) == null) scores.put(score, new Score(objective, score, null, null));

        final Score entry = getScore(score);

        entry.setPrefix(content);
        entry.show();
    }

    public void setLines(LinkedList<String> lines) {
        if (lines.isEmpty()) return;

        try {
            lines.forEach(content -> {
                final int score = lines.indexOf(content);
                setScore(score, content);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeLine(int score) {
        if (scoreboard == null) return;
        if (objective == null) return;

        final Score entry = getScore(score);

        if (entry == null) return;

        scores.remove(score);
        entry.hide();
    }

    public void addLine(String content) {
        final int score = scores.size();

        setScore(score, content);
    }

    public void setTitle(String title) {
        if (scoreboard == null && objective == null) return;

        objective.setDisplayName(title);
    }
}
