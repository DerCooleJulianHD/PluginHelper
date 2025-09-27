package de.xcuzimsmart.pluginhelper.code.test;

import de.xcuzimsmart.pluginhelper.code.git.branch.main.configuration.JsonConfigurator;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.configuration.JsonProperties;
import org.bukkit.plugin.Plugin;

@JsonProperties(prettyPrinting = true, htmlEscaping = true, innerClazzSerialisation = true)
public class JsonConfigTest extends JsonConfigurator {

    public JsonConfigTest(Plugin plugin) {
        super(plugin.getDataFolder(), "config_test.json", true);

        this.write(new TestObject());
    }
}
