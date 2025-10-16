package de.xcuzimsmart.pluginhelper.code.main.java.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Abstract */
public abstract class ScoreboardBuilder implements Scoreboard {

    protected org.bukkit.scoreboard.Scoreboard scoreboard;
    protected Objective objective;

    protected Map<Integer, Score> scores = new HashMap<>();

    @Override
    public void setLine(int id, String prefix, String suffix) {
        if (scoreboard == null) return;
        if (objective == null) return;


        if (!scores.containsKey(id)) {
             scores.put(id, new Score(objective, id, null, null));
        }

        final Score score = scores.get(id);

        if (prefix != null) score.setPrefix(prefix);
        if (suffix != null) score.setSuffix(suffix);

        score.show();
    }

    @Override
    public void removeLine(int id) {
        if (scoreboard == null) return;
        if (objective == null) return;

        if (!scores.containsKey(id)) return;

        final Score score = scores.get(id);

        score.hide();
        scores.remove(id);
    }

    @Override
    public void setLine(int id, String s) {
        this.setLine(id, s, null);
    }

    @Override
    public void addLine(String s) {
        this.setLine(scores.size(), s);
    }

    @Override
    public void setLines(List<String> lines) {
        lines.forEach(this::addLine);
    }

    @Override
    public void setTitle(String s) {
        if (objective != null) objective.setDisplayName("  " + ChatColor.translateAlternateColorCodes('&', s) +  "  ");
    }

    @Override
    public String getTitle() {
        return objective == null ? "" : objective.getDisplayName();
    }

    @Override
    public void clear() {
        scores.keySet().forEach(this::removeLine);
    }

    @Override
    public org.bukkit.scoreboard.Scoreboard getBukkitScoreboard() {
        return scoreboard;
    }

    @Override
    public Objective getObjective() {
        return objective;
    }

    @Override
    public Objective getObjective(String key) {
        return scoreboard.getObjective(key);
    }

    @Override
    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    @Override
    public Objective addObjective(String key, String criteria, String title, DisplaySlot displaySlot, boolean replace) {
        if (scoreboard == null) return null;

        if (replace && hasObjective(key)) scoreboard.getObjective(key).unregister();

        if (hasObjective(key)) return getObjective(key);

        Objective o = scoreboard.registerNewObjective(key, criteria);
        if (title != null) o.setDisplayName(title);
        if (displaySlot != null) o.setDisplaySlot(displaySlot);

        return o;
    }

    @Override
    public boolean hasObjective(String key) {
        return scoreboard.getObjective(key) != null;
    }

    @Override
    public void setBukkitScoreboard(org.bukkit.scoreboard.Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    @Override
    public Map<Integer, Score> getScores() {
        return scores;
    }
}
