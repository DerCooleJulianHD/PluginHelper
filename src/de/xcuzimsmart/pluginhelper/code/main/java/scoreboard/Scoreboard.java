package de.xcuzimsmart.pluginhelper.code.main.java.scoreboard;

import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.List;
import java.util.Map;

public interface Scoreboard {

    void setTitle(String s);

    String getTitle();

    void setLine(int id, String prefix, String suffix);

    void setLine(int id, String s);

    void removeLine(int id);

    void addLine(String s);

    void setLines(List<String> lines);

    void clear();

    org.bukkit.scoreboard.Scoreboard getScoreboard();

    void setScoreboard(org.bukkit.scoreboard.Scoreboard scoreboard);

    Objective getObjective();

    Objective getObjective(String key);

    void setObjective(Objective objective);

    Objective addObjective(String key, String criteria, String title, DisplaySlot displaySlot, boolean replace);

    boolean hasObjective(String key);

    Map<Integer, Score> getScores();
}
