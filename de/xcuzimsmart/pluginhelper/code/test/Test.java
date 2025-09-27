package de.xcuzimsmart.pluginhelper.code.test;

import de.xcuzimsmart.pluginhelper.code.Main;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.bundle.ListenerBundle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class Test implements Listener {

    public final Main main = Main.getInstance();
    private final ListenerBundle listeners = new ListenerBundle(main);

    // ##############################################################################################################

    /*
     * here goes your test fields
     */

    // ###############################################################################################################

    public Test() {
        this.executeTestCode(); // final
    }

    public void executeTestCode() {
        /*
        * here goes your test code.
        */

        new JsonConfigTest(main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // here when player joins the server.
    }

    public ListenerBundle getListeners() {
        return listeners;
    }
}
