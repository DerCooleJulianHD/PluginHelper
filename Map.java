package me.dercoolejulianhd.api.minigame.game;

import org.bukkit.World;

public interface Map {

    Game parent();
    World getWorld();
    Game load();
    void unload();
    boolean reset();
    boolean isOnline();
}
