package de.xcuzimsmart.pluginhelper.code.main.java.menu.event;

import de.xcuzimsmart.pluginhelper.code.main.java.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public abstract class MenuEvent extends PlayerEvent {

    static final HandlerList handlers = new HandlerList();

    final Menu menu;

    public MenuEvent(Player p, Menu menu) {
        super(p);
        this.menu = menu;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Menu getMenu() {
        return menu;
    }

}
