package de.xcuzimsmart.pluginhelper.code.main.java.run;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.interfaces.MinecraftPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.annotations.Abstract;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.interfaces.Executable;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Counter extends BukkitRunnable implements Executable {

    protected final MinecraftPlugin plugin;

    protected boolean running = false;

    protected final long delay, period;

    public Counter(MinecraftPlugin plugin, long delay, long period) {
        this.plugin = plugin;
        this.delay = delay;
        this.period = period;

        this.runTaskTimer((SpigotPlugin) plugin, delay, period);
    }

    @Override
    public final void run() {
        if (isRunning()) {
            try {
                execute();
            } catch (Exception e) {
                this.cancel();
            }
        }
    }

    @Abstract
    public boolean isRunning() {
        return running;
    }

    @Abstract
    public void setRunning(boolean running) {
        this.running = running;
    }

    public final void start() {
       if (!isRunning()) this.setRunning(true);
    }

    public final void stop() {
        if (isRunning()) this.setRunning(false);
    }

    public final long getDelay() {
        return delay;
    }

    public final long getPeriod() {
        return period;
    }
}
