package de.xcuzimsmart.pluginhelper.code.main.java.test;

import de.xcuzimsmart.pluginhelper.code.main.java.Main;
import de.xcuzimsmart.pluginhelper.code.main.java.bundle.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.PluginConfigFile;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.PlayerScoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.scoreboard.Scoreboard;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Messanger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class Test implements Listener {

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
        final Scoreboard scoreboard = Main.getInstance().getScoreboard();
        final PluginConfigFile config = Main.getInstance().getConfiguration();

        player.playSound(player.getLocation(), Sound.LEVEL_UP, 3, 1);
    }
}
