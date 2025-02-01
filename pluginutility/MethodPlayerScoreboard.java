package pluginutility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class MethodPlayerScoreboard {

    private final Map<Integer, String> lines;
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final Player player;

    public MethodPlayerScoreboard(Player player) {
        this.lines = new HashMap<>();
        this.player = player;

        if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        this.scoreboard = player.getScoreboard();
        if (scoreboard.getObjective("display") != null) scoreboard.getObjective("display").unregister();

        this.objective = scoreboard.registerNewObjective("display", "dummy");
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        setLines(lines);
    }

    public void updateScoreboard() {
        this.lines.values().forEach(scoreboard::resetScores);
        setLines(this.lines);
    }

    @Deprecated
    private void setLines(Map<Integer, String> lines) {
        this.lines.forEach((index, lineContent) -> objective.getScore(lineContent).setScore(index));
    }

    public void setTitle(String title) {
        objective.setDisplayName(title);
    }

    public void addLine(int index, String lineContent) {
        this.lines.put(index, lineContent);
        this.updateScoreboard();
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Objective getObjective() {
        return objective;
    }

    public Player getPlayer() {
        return player;
    }
}
