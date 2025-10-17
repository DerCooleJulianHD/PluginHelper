package de.xcuzimsmart.pluginhelper.code.main.java.menu;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.Bundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public final class Menus extends Bundle<Menu> {

    private final Menu.ClickListener listener = new Menu.ClickListener();

    @Override
    public void onRegisterObject(Menu menu) {
        if (actives.size() == 1) { // when true, it's the first element we added.
            final PluginManager pluginManager = Bukkit.getPluginManager();

            pluginManager.registerEvents(listener, SpigotPlugin.getInstance());
        }
    }

    @Override
    public void onUnregisterObject(Menu menu) {
        if (actives.isEmpty()) { // when true, it's the last element we're removed.
            final PluginManager pluginManager = Bukkit.getPluginManager();

            HandlerList.unregisterAll(listener);
        }
    }

    public static void register(Menu menu) {
        final Menus menus = new Menus();

        menus.register(String.valueOf(menus.actives.size()), menu.title, menu);
    }

    public static void unregister(int id) {
        final Menus menus = new Menus();
        final Menu menu = menus.get(id);

        if (menu == null) return;

        menus.unregister(menu.title);
    }
}
