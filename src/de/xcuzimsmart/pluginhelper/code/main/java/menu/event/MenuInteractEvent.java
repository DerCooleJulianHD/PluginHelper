package de.xcuzimsmart.pluginhelper.code.main.java.menu.event;

import de.xcuzimsmart.pluginhelper.code.main.java.menu.Menu;
import org.bukkit.entity.Player;

public class MenuInteractEvent extends MenuEvent {

    final int slot;

    public MenuInteractEvent(Player player, Menu menu, int slot) {
        super(player, menu);
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }
}
