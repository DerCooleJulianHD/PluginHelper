package de.xcuzimsmart.pluginhelper.code.main.java.menu;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public final class MenuInteractEvent extends MenuEvent {

    final int slot;

    final Icon clickedIcon;

    public MenuInteractEvent(Player player, Menu menu, int slot, Icon icon) {
        super(player, menu);
        this.slot = slot;
        this.clickedIcon = icon;
    }

    public int getSlot() {
        return slot;
    }

    @Nullable
    public Icon getClickedIcon() {
        return clickedIcon;
    }
}
