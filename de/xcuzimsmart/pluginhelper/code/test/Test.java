package de.xcuzimsmart.pluginhelper.code.test;

import de.xcuzimsmart.pluginhelper.code.Main;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.bundle.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.configuration.JsonConfigurator;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.configuration.YamlConfigurator;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.utils.Dependencies;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class Test implements Listener {

    public final Main main = Main.getInstance();

    private final JsonConfigurator jsonConfig = new JsonConfigurator(main.getDataFolder(), "test.json");
    private final YamlConfigurator yamlConfig = new YamlConfigurator(main.getDataFolder(), "test.yml");
    private final ListenerBundle listeners = new ListenerBundle(main);

    // ##############################################################################################################

    /*
     * here goes your test fields
     */

    // ###############################################################################################################

    public Test() {
        yamlConfig.createFiles();
        jsonConfig.createFiles();

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
    }

    public JsonConfigurator getJsonConfig() {
        return jsonConfig;
    }

    public YamlConfigurator getYamlConfig() {
        return yamlConfig;
    }

    public ListenerBundle getListeners() {
        return listeners;
    }
}
