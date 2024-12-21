package me.dercoolejulianhd.api.minigame.game;

import me.dercoolejulianhd.api.minigame.game.config.GameSettings;

import java.io.File;

public interface Game extends Map {

    File getDataFolder();
    GameSettings settings();
    void sendStartingMessage();
    void sendStoppingMessage();

}
