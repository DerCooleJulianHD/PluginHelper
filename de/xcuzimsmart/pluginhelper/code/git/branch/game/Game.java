package de.xcuzimsmart.pluginhelper.code.git.branch.game;

import de.xcuzimsmart.pluginhelper.code.git.branch.game.map.GameMap;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.configuration.YamlConfigurator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {

    protected final Plugin plugin;

    protected final String name;

    protected final File dir;
    protected final YamlConfigurator config;

    protected final List<Player> onlinePlayers = new ArrayList<>();

    protected GameMap gameMap;

    public Game(Plugin plugin, String name) {
        this.plugin = plugin;
        this.dir = new File(GameManager.getDir(), name);
        this.config = new YamlConfigurator(dir, "game.yml", false);
        this.name = config.isSet("name") ? config.readString("name") : name;
    }

    public void loadGameMap(GameMap gameMap) {
        if (hasGameMap()) return;
        if (!gameMap.isLoaded()) gameMap.load();
        this.gameMap = gameMap;
    }

    public boolean hasGameMap() {
        return this.gameMap != null;
    }

    public String getName() {
        return name;
    }

    public int getMinPlayers() {
        return config.isSet("min-players") ? config.readInt("min-players") : 0;
    }

    public int getMaxPlayers() {
        return config.isSet("max-players") ? config.readInt("max-players") : plugin.getServer().getMaxPlayers();
    }

    public File getDir() {
        return dir;
    }

    public YamlConfigurator getConfig() {
        return config;
    }

    public List<Player> getOnlinePlayers() {
        return onlinePlayers;
    }
}
