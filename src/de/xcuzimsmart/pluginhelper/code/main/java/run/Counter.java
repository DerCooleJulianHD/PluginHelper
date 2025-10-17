package de.xcuzimsmart.pluginhelper.code.main.java.run;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.AbstractUsage;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Executable;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Counter extends BukkitRunnable implements Executable {

    protected boolean running = false;

    protected final long delay, period;

    public Counter(long delay, long period) {
        this.delay = delay;
        this.period = period;

        this.runTaskTimer(SpigotPlugin.getInstance(), delay, period);
    }

    @Override
    public final void run() {
        try {
            execute();
        } catch (Exception e) {
            this.cancel();
        }
    }

    @AbstractUsage public boolean isRunning() {
        return running;
    }

    @AbstractUsage public void setRunning(boolean running) {
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
