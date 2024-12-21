package me.dercoolejulianhd.api.minigame.game;

import me.xcuzimsmart.utils.storages.YamlStorage;
import java.io.File;

public interface Game extends Map {
    File getDataFolder();
    YamlStorage settings();
    void sendStartingMessage();
    void sendStoppingMessage();
}
