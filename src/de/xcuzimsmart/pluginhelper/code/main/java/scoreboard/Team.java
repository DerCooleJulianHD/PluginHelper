package de.xcuzimsmart.pluginhelper.code.main.java.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class Team {

    final PluginScoreboard scoreboard;

    String name, entryName;
    String prefix;

    ChatColor color;

    org.bukkit.scoreboard.Team entry;

    public Team(PluginScoreboard scoreboard, String name, String prefix, ChatColor color) {
        this.scoreboard = scoreboard;

        this.name = name;
        this.entryName = name.toUpperCase();
        this.prefix = prefix;
        this.color = color;
    }

    public Team(PluginScoreboard scoreboard, String name) {
        this(scoreboard, name, null, ChatColor.WHITE);
    }

    public org.bukkit.scoreboard.Team getEntry() {
        return entry;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public void addPlayer(Player player) {
        if (entry != null) entry.addEntry(player.getName());
    }

    public void setEntry(org.bukkit.scoreboard.Team entry) {
       this.entry = entry;
    }

    public static Team builder(PluginScoreboard scoreboard, String name) {
        return new Team(scoreboard, name);
    }

    public String getEntryName() {
        return entryName;
    }
}
