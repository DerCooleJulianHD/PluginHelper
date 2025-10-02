package de.xcuzimsmart.pluginhelper.code.main.java.test;

import de.xcuzimsmart.pluginhelper.code.main.java.Main;
import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.PluginConfigFile;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.PlayerScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Messanger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class Test implements Listener {

    private final ListenerBundle listeners = new ListenerBundle(Main.getInstance());

    // ##############################################################################################################

    /*
     * here goes your test fields
     */

    // ###############################################################################################################

    public Test() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        this.executeTestCode(); // final
    }

    public void executeTestCode() {
        /*
        * here goes your test code.
        */
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // here when player joins the server.
        final Player player = event.getPlayer();
        final PlayerScoreboard scoreboard = new PlayerScoreboard(player);
        final PluginConfigFile config = Main.getConfiguration();

        scoreboard.setTitle("&b&l" + player.getName());
        scoreboard.addLine("&aLine two");
        scoreboard.addLine("Hello!!!");

        Messanger.broadcast(Main.getInstance(), player, ChatColor.GOLD + config.getMessage());
        player.getInventory().addItem(config.getItem());

        player.playSound(player.getLocation(), Sound.LEVEL_UP, 3, 1);
    }

    public ListenerBundle getListeners() {
        return listeners;
    }
}
