package pluginutility;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class FuncGlobalScoreboard {

    private final Map<Integer, String> lines;
    private final Scoreboard scoreboard;
    private final Objective objective;

    public FuncGlobalScoreboard(String title, Map<Integer, String> lines) {
        this.lines = new HashMap<>();
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        if (scoreboard.getObjective("display") == null) scoreboard.registerNewObjective("display", "dummy");
        this.objective = scoreboard.getObjective("display");
        this.objective.setDisplayName(title);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        setLines(lines);
    }

    public void updateScoreboard() {
        this.lines.values().forEach(scoreboard::resetScores);
        setLines(this.lines);
    }

    private void setLines(Map<Integer, String> lines) {
        this.lines.forEach((index, lineContent) -> objective.getScore(lineContent).setScore(index));
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Objective getObjective() {
        return objective;
    }
}
