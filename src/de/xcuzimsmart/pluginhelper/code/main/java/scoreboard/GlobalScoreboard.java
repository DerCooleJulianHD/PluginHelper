package de.xcuzimsmart.pluginhelper.code.main.java.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public final class GlobalScoreboard extends ScoreboardBuilder {

    public GlobalScoreboard(String title) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        // registering new Objective
        this.objective = this.addObjective("display", "dummy", title, DisplaySlot.SIDEBAR, false);
    }

    public GlobalScoreboard() {
        this(null);
    }

    public void addToPlayer(Player player) {
        player.setScoreboard(this.scoreboard);
    }
}
