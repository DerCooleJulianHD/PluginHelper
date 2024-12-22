package me.xcuzimsmart.utils;

import org.bukkit.plugin.java.JavaPlugin;

public interface MinecraftPlugin {
    JavaPlugin instance();
    String prefix();
    String name();
}
