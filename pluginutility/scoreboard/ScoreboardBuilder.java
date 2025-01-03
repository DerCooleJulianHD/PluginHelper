package pluginutility.scoreboard;

import com.avaje.ebean.validation.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import pluginutility.MinecraftPlugin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ScoreboardBuilder {
    private final MinecraftPlugin plugin;
    private final Scoreboard scoreboard;
    private final Map<String, Objective> objectives;
    private Objective main;
    
    public ScoreboardBuilder(MinecraftPlugin plugin) {
        this.plugin = plugin;
        this.objectives = new HashMap<>();
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.main = this.scoreboard.registerNewObjective("main", "dummy");
    }
    
    public void setScores(Objective obj, List<String> values) {
        values.forEach(s -> {
            int score = values.indexOf(s);
            this.setScore(obj, s, score);
        });
    }
    
    @NotNull
    protected ScoreEntry getScoreEntry(int score) {
        for (ScoreEntry entry : ScoreEntry.values()) {
            if (score == entry.getEntry()) return entry;
        }

        return null;
    }
    
    @NotNull
    protected Team getScoreEntryTeam(int score) {
        ScoreEntry entry = this.getScoreEntry(score);
        if (entry == null) return null;

        Team team = this.scoreboard.getEntryTeam(entry.getEntryName());
        if (team != null) return team;

        team = this.scoreboard.registerNewTeam(entry.name());
        team.addEntry(entry.getEntryName());
        return team;
    }
    
    protected void showScore(Objective obj, int score) {
        ScoreEntry entry = this.getScoreEntry(score);
        if (entry == null) return;
        if (obj.getScore(entry.getEntryName()).isScoreSet()) return;
        obj.getScore(entry.getEntryName()).setScore(score);
    }
    
    protected void hideScore(Objective obj, int score) {
        ScoreEntry entry = this.getScoreEntry(score);
        if (entry == null) return;
        if (!obj.getScore(entry.getEntryName()).isScoreSet()) return;
        scoreboard.resetScores(entry.getEntryName());
    }
    
    public void removeScore(Objective obj, int score) {
        Team team = this.getScoreEntryTeam(score);
        if (team == null) return;
        team.unregister();
        hideScore(obj, score);
    }
    
    public void setScore(Objective obj, String content, int score) {
        Team team = this.getScoreEntryTeam(score);
        if (team == null) return;
        team.setPrefix(content);
        showScore(obj, score);
    }
    
    public Objective addObjective(String key, String criteria, String displayName, DisplaySlot slot) {
        if (isObjectiveExists(key)) return this.getRegisteredObjective(key);
        Objective obj = this.scoreboard.registerNewObjective("obj:{" + key + "}", criteria);
        obj.setDisplayName(displayName);
        obj.setDisplaySlot(slot);
        this.objectives.put(key, obj);
        return obj;
    }
    
    public void removeObjective(String key) {
        if (!isObjectiveExists(key)) return;
        Objective obj = this.scoreboard.getObjective("obj:{" + key + "}");
        obj.unregister();
        this.objectives.remove(key, obj);
    }
    
    public Objective getRegisteredObjective(String key) {
        if (!isObjectiveExists(key)) return null;
        return this.objectives.get(key);
    }
    
    public boolean isObjectiveExists(String key) {
        boolean objExist = this.scoreboard.getObjective("obj:{" + key + "}") != null;
        boolean isAdded = this.objectives.containsKey(key);
        return (objExist && isAdded);
    }


    public void update(Objective obj, int score, String content) {
        setScore(obj, content, score);
    }

    protected void setDisplayName(Objective obj, String displayName) {
        obj.setDisplayName(displayName);
    }

    public void addPlayer(Player player) {
        player.setScoreboard(this.scoreboard);
    }
    
    public MinecraftPlugin getPlugin() {
        return plugin;
    }

    protected Scoreboard getScoreboard() {
        return scoreboard;
    }

    protected Map<String, Objective> getRegisteredObjectives() {
        return objectives;
    }
    
    public Objective getMainObjective() {
        return this.main;
    }

    public void setMainObjective(Objective objective) {
        this.main = objective;
    }
}
