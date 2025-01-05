package pluginutility.timer;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import pluginutility.MinecraftPlugin;

public abstract class Timer extends BukkitRunnable {
    private final MinecraftPlugin plugin;
    private final TimerType type;
    private boolean running; // true or false
    private double time;
    private final long delay;
    private long period;
    private boolean prettyPrinting;

    public Timer(MinecraftPlugin plugin, TimerType type, int time, long delay, long period, boolean start) {
        this.plugin = plugin;
        this.type = type;
        this.time = time; /* the current time the timer will start at */
        this.delay = delay;
        this.period = period;
        this.running = start;
        this.prettyPrinting = false;
    }

    public Timer(MinecraftPlugin plugin, TimerType type, int time, long period, boolean start) {
        this(plugin, type, time, 0, period, start);
    }

    // will be called every time the current time changes to create an inherit custom event.
    public abstract void onTimeChange();

    @Override
    public void run() {
        // check if timer is running and is counting time
        if (!isRunning()) return;

        double i = (double) period;

        switch (type) {
            case SIMPLE -> this.setTime(time + i);
            case COUNTDOWN -> this.setTime(time - 1);
        }
    }

    public String getFullMessage(boolean prettyPrinting) {
        this.setPrettyPrinting(prettyPrinting);
        return ChatColor.GRAY + "Time: " + ChatColor.GREEN + ChatColor.ITALIC + this.getTimeAsString();
    }

    // returns current time in a String format
    public String getTimeAsString() {
        return this.toString();
    }

    // parsing the current time into string and take into account pretty-printing
    @Override
    public String toString() {
        if (this.isPrettyPrinting()) return (time < 10 ? "0" + time : String.valueOf(time));
        return String.valueOf(time);
    }

    // changes the current time
    public void setTime(double time) {
        this.time = time;
        this.onTimeChange();
    }

    // starts and stops the timer
    public void setRunning(boolean running, long delay, long period) {
        this.running = running;
        if (running) this.runTaskTimer(plugin, delay, period);
        else if (isRunning()) cancel();
    }

    // this is the state if timer has been started
    public boolean isRunning() {
        return running;
    }

    // returns the current number from our timer
    public double getTime() {
        return time;
    }

    // while true and time is blow 10 there will be added 0 in front of time
    public boolean isPrettyPrinting() {
        return prettyPrinting;
    }

    public void setPrettyPrinting(boolean prettyPrinting) {
        this.prettyPrinting = prettyPrinting;
    }

    // this is the time distance the timer is executing
    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
        if (isRunning()) cancel();
        setRunning(true, this.delay, period);
    }

    // this is the time distance when timer executes the first time
    public long getDelay() {
        return delay;
    }

    public MinecraftPlugin getMinecraftPlugin() {
        return plugin;
    }

    public TimerType getType() {
        return type;
    }

    public enum TimerType {
        SIMPLE(),
        CLOCK(),
        COUNTDOWN();
    }
}
