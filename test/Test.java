package test;

import de.xcuzimsmart.pluginhelper.code.Main;
import de.xcuzimsmart.pluginhelper.code.bundle.bundles.ListenerBundle;
import de.xcuzimsmart.pluginhelper.code.configuration.configurators.json.JsonConfigurator;
import de.xcuzimsmart.pluginhelper.code.configuration.configurators.yaml.YamlConfigurator;
import org.bukkit.scheduler.BukkitRunnable;

public final class Test {

    public final Main main = Main.getInstance();

    private JsonConfigurator jsonConfig;
    private YamlConfigurator yamlConfig;

    private ListenerBundle listeners;

    public Test() {
        this.executeTestCode();
    }

    public void executeTestCode() {
        // here your test code.
        this.listeners = new ListenerBundle(main);
        this.jsonConfig = new JsonConfigurator(main.getDataFolder(), "test.json");
        this.yamlConfig = new YamlConfigurator(main.getDataFolder(), "test.yml");
        
        yamlConfig.writeString("test", "this comes from Yaml Config.");
        listeners.register(new TestListener());
    }

    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                listeners.unregisterAll();
            }
        }.runTaskTimer(main, 20, 20*10);
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
