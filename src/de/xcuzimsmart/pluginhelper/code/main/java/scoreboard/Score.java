package de.xcuzimsmart.pluginhelper.code.main.java.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import javax.annotation.Nullable;

public final class Score {

    final Objective objective;

    final int id;

    String prefix;
    String suffix;

    final Team entry;

    public Score(Objective obj, int id, String prefix, String suffix) {
        this.objective = obj;
        this.id = id;
        this.prefix = prefix;
        this.suffix = suffix;

        this.entry = this.getEntryTeam();
    }

    public Objective getObjective() {
        return objective;
    }

    public int getId() {
        return id;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        if (entry == null) return;

        if (prefix.length() <= 16) entry.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix));
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
        if (entry == null) return;

        if (suffix.length() <= 16) entry.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix));
    }

    @Nullable
    private EntryName getEntryNameByScore() {
        for (EntryName name : EntryName.values()) {
            if (id == name.getEntry())
                return name;
        }

        return null;
    }

    private Team getEntryTeam() {
        if (objective == null) return null;

        final Scoreboard parent = objective.getScoreboard();
        final EntryName name = getEntryNameByScore();

        if (name == null) return null;

        Team team = parent.getEntryTeam(name.getEntryName());
        if (team != null) return team;
        team = parent.registerNewTeam(name.name());
        team.addEntry(name.getEntryName());

        return team;
    }

    public void show() {
        if (objective == null) return;
        final EntryName name = getEntryNameByScore();
        if (name == null) return;
        if (objective.getScore(name.entryName).isScoreSet()) return;
        objective.getScore(name.entryName).setScore(id);
    }

    public void hide() {
        if (objective == null) return;
        final Scoreboard parent = objective.getScoreboard();
        final EntryName name = getEntryNameByScore();
        if (name == null) return;
        if (!objective.getScore(name.entryName).isScoreSet()) return;
        parent.resetScores(name.entryName);
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public Team getEntry() {
        return entry;
    }

    public enum EntryName {

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

        final int entry;
        final String entryName;

        EntryName(int entry, String entryName) {
            this.entry = entry;
            this.entryName = entryName;
        }

        public int getEntry() {
            return entry;
        }

        public String getEntryName() {
            return entryName;
        }
    }
}
