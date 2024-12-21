package me.dercoolejulianhd.api.minigame.game;

import me.xcuzimsmart.utils.storages.YamlStorage;
import java.io.File;

public interface Game {
    File getDataFolder();
    YamlStorage settings();
    Map map();
    void sendStartingMessage();
    void sendStoppingMessage();
}
