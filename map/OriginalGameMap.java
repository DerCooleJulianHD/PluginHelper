package de.xcuzimsmart.minigameapi.classes.game.map;

import org.bukkit.Bukkit;

import java.io.File;

public final class OriginalGameMap extends GameMap {

    public OriginalGameMap(String name) {
        super(new File(Bukkit.getWorldContainer().getParentFile(), name), new File(Bukkit.getWorldContainer().getParentFile(), name), true);
    }
}
