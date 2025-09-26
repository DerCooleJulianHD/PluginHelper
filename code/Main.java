package de.xcuzimsmart.pluginhelper.code;

import de.xcuzimsmart.pluginhelper.code.commands.DisablePluginCommand;
import de.xcuzimsmart.pluginhelper.code.commands.EnablePluginCommand;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.interfaces.Prefixable;
import de.xcuzimsmart.pluginhelper.code.test.Test;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Prefixable {

    private static Main instance;

    private static Test test;

    @Override
    public void onEnable() {
        instance = this;

        new DisablePluginCommand(this);
        new EnablePluginCommand(this);

        test = new Test();
    }

    public static Test getTest() {
        return test;
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public String getPrefix() {
        return "&7[" + getName() + "]: &r";
    }

    @Override
    public void setPrefix(String prefix) {
        throw new UnsupportedOperationException("not supportet yet.");
    }
}
