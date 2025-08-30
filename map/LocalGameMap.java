package de.xcuzimsmart.minigameapi.classes.game.map;

import org.bukkit.Bukkit;

import java.io.File;
import java.util.Date;

public final class LocalGameMap extends GameMap {

    public LocalGameMap(File sourceFolder, String name) {
        super(sourceFolder, new File(Bukkit.getWorldContainer().getParentFile(), name + "_active_" + new Date()), false);
    }
}
