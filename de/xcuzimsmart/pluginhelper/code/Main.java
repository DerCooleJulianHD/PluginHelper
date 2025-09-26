package de.xcuzimsmart.pluginhelper.code;

import de.xcuzimsmart.pluginhelper.code.commands.DisablePluginCommand;
import de.xcuzimsmart.pluginhelper.code.commands.EnablePluginCommand;
import de.xcuzimsmart.pluginhelper.code.commands.TimerCommand;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.interfaces.Prefixable;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.timer.Timer;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.utils.EventListener;
import de.xcuzimsmart.pluginhelper.code.test.Test;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Prefixable {

    private static Main instance;

    private static Test test;

    private static Timer timer;

    @Override
    public void onEnable() {
        instance = this;

        new DisablePluginCommand(this);
        new EnablePluginCommand(this);
        new TimerCommand(this);

        try {
            timer = new Timer(this);

            timer.start();
        } catch (Exception e) {
            if (timer != null) timer.cancel();
        }

        test = new Test();

        test.getListeners().register(new EventListener() {
        });
    }

    public static Test getTest() {
        return test;
    }

    public static Main getInstance() {
        return instance;
    }

    public static Timer getTimer() {
        return timer;
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
