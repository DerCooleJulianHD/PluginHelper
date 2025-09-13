package de.code.test.scoreboard;

import org.bukkit.ChatColor;

public enum EntryName {

    SCORE_0(0, ChatColor.GRAY.toString()),
    SCORE_1(1, ChatColor.DARK_GRAY.toString()),
    SCORE_2(2, ChatColor.RED.toString()),
    SCORE_3(3, ChatColor.DARK_RED.toString()),
    SCORE_4(4, ChatColor.BLUE.toString()),
    SCORE_5(5, ChatColor.DARK_BLUE.toString()),
    SCORE_6(6, ChatColor.YELLOW.toString()),
    SCORE_7(7, ChatColor.GOLD.toString()),
    SCORE_8(8, ChatColor.GREEN.toString()),
    SCORE_9(9, ChatColor.DARK_GREEN.toString()),
    SCORE_10(10, ChatColor.AQUA.toString()),
    SCORE_11(11, ChatColor.BOLD.toString()),
    SCORE_12(12, ChatColor.LIGHT_PURPLE.toString()),
    SCORE_13(13, ChatColor.DARK_AQUA.toString()),
    SCORE_14(14, ChatColor.RESET.toString()),
    SCORE_15(15, ChatColor.ITALIC.toString());

    final int entry;
    final String entryName;

    EntryName(int entry, String entryName) {
        this.entry = entry;
        this.entryName = entryName;
    }

    public int getEntry() {
        return entry;
    }

    public String getEntryName() {
        return entryName;
    }
}
