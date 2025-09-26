package de.xcuzimsmart.pluginhelper.code.git.branch.main.timer;

import de.xcuzimsmart.pluginhelper.code.git.branch.main.utils.Messanger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Timer extends BukkitRunnable {

    final Plugin plugin;

    int seconds;
    int minutes;
    int hours;

    boolean running;

    public Timer(Plugin plugin) {
        this.plugin = plugin;

        this.running = false;

        this.seconds = 0;
        this.minutes = 0;
        this.hours = 0;
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

    @Override
    public String toString() {
        return (seconds < 10 ? "0" + seconds : seconds) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" +  (hours < 10 ? "0" + hours : hours);
    }

    public void countSeconds() {
        seconds++;
    }

    public void countMinutes() {
        if (seconds < 60) return;
        minutes++;
        seconds = 0;
    }

    public void countHours() {
        if (minutes < 60) return;
        hours++;
        minutes = 0;
        seconds = 0;
    }

    public void sendActionBar() {
        if (!isRunning()) return;
        Messanger.sendActionBar(Bukkit.getOnlinePlayers(), this.toString());
    }

    @Override
    public void run() {
        sendActionBar();

        if (!isRunning()) return;

        countSeconds();
        countMinutes();
        countHours();
    }

    public void start() {
        runTaskTimer(plugin, 20, 20);
    }
}
