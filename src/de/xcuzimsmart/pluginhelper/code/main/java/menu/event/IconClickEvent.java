package de.xcuzimsmart.pluginhelper.code.main.java.menu.event;

import de.xcuzimsmart.pluginhelper.code.main.java.menu.Icon;
import de.xcuzimsmart.pluginhelper.code.main.java.menu.Menu;
import org.bukkit.entity.Player;

public class IconClickEvent extends MenuInteractEvent {

    final Icon clickedIcon;

    public IconClickEvent(Player p, Menu menu, int slot, Icon icon) {
        super(p, menu, slot);
        this.clickedIcon = icon;
    }

    public Icon getClickedIcon() {
        return clickedIcon;
    }
}
