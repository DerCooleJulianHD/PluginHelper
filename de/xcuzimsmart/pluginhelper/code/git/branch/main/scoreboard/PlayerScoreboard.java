package de.xcuzimsmart.pluginhelper.code.git.branch.main.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public final class PlayerScoreboard extends ScoreboardBuilder {

    final Player player;

    public PlayerScoreboard(Player player, String title) {
        super(player.getScoreboard());
        this.player = player;

        if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }

        // adding the new obj
        this.addObjective("display_" + player.getUniqueId().toString(), "dummy", title, DisplaySlot.SIDEBAR, true, true);
    }

    public Player getPlayer() {
        return player;
    }
}
