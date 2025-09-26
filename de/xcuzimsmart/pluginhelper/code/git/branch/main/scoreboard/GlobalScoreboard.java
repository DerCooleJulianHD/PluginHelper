package de.xcuzimsmart.pluginhelper.code.git.branch.main.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;

public final class GlobalScoreboard extends ScoreboardBuilder {

    public GlobalScoreboard(String title) {
        super(Bukkit.getScoreboardManager().getNewScoreboard());
        // registering new Objective
        this.addObjective("display", "dummy", title, DisplaySlot.SIDEBAR, false);
    }
}
