package de.xcuzimsmart.pluginhelper.code.git.branch.main.timer;

import de.xcuzimsmart.pluginhelper.code.git.branch.main.utils.Messanger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Timer extends BukkitRunnable {

    final Plugin plugin;

    int hours;
    int minutes;
    int seconds;

    boolean running;

    final long delay, period;

    public Timer(Plugin plugin, long delay, long period) {
        this.plugin = plugin;

        this.running = false;

        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;

        this.delay = delay;
        this.period = period;
    }

    public void setTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', "&f[&6" + (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds) + "&f]");
    }

    public void sendActionBar() {
        Messanger.sendActionBar(Bukkit.getOnlinePlayers(), this.toString());
    }

    @Override
    public void run() {

        if (isRunning()) {
            if (seconds == 59) {
                minutes++;
                seconds = 0;
            } else if (minutes == 59) {
                hours++;
                minutes = 0;
            } else {
                seconds++;
            }
        }

        sendActionBar();
    }

    public void start() {
        runTaskTimer(plugin, delay, period);
    }
}
