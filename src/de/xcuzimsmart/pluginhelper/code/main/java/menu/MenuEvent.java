package de.xcuzimsmart.pluginhelper.code.main.java.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public abstract class MenuEvent extends PlayerEvent {

    protected static final HandlerList handlers = new HandlerList();

    protected final Menu menu;

    public MenuEvent(Player p, Menu menu) {
        super(p);
        this.menu = menu;
    }

    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public final Menu getMenu() {
        return menu;
    }
}
