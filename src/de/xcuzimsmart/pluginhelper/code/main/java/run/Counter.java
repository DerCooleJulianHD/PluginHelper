package de.xcuzimsmart.pluginhelper.code.main.java.run;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Executable;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Counter extends BukkitRunnable implements Executable {

    protected final SpigotPlugin plugin;

    protected boolean running = false;

    protected final long delay, period;

    public Counter(SpigotPlugin plugin, long delay, long period) {
        this.plugin = plugin;
        this.delay = delay;
        this.period = period;

        this.runTaskTimer(plugin.getPlugin(), delay, period);
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (Exception e) {
            this.cancel();
        }
    }

    public void start() {
       if (!isRunning()) this.setRunning(true);
    }

    public void stop() {
        if (isRunning()) this.setRunning(false);
    }

    public SpigotPlugin getPlugin() {
        return plugin;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public long getDelay() {
        return delay;
    }

    public long getPeriod() {
        return period;
    }
}
