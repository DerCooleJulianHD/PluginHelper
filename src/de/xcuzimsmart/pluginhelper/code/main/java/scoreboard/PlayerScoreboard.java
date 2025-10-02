package de.xcuzimsmart.pluginhelper.code.main.java.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public final class PlayerScoreboard extends ScoreboardBuilder {

    final Player player;

    public PlayerScoreboard(Player player, String title) {
        this.player = player;

        if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }

        this.scoreboard = player.getScoreboard();
        // adding the new obj
        this.objective = this.addObjective("display:player", "dummy", title, DisplaySlot.SIDEBAR, true);
    }

    public PlayerScoreboard(Player player) {
        this(player, null);
    }

    public Player getPlayer() {
        return player;
    }
}
