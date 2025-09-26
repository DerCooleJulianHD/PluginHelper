package de.xcuzimsmart.pluginhelper.code.git.branch.main.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public final class PlayerScoreboard extends ScoreboardBuilder {

    final Player player;

    public PlayerScoreboard(Player player, String title) {
        super(player.getScoreboard());
        this.player = player;

        this.addObjective("display_" + player.getUniqueId().toString(), "dummy", title, DisplaySlot.SIDEBAR, true);
    }

    public Player getPlayer() {
        return player;
    }
}
