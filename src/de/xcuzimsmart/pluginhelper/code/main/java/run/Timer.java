package de.xcuzimsmart.pluginhelper.code.main.java.run;

import de.xcuzimsmart.pluginhelper.code.main.java.utils.messanger.Messanger;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@TimerColor(brackets = ChatColor.WHITE, timer = ChatColor.WHITE) /* <-- by default */
public final class Timer extends Counter {

    public final List<UUID> shown = new ArrayList<>();

    int hours;
    int minutes;
    int seconds;

    final TimerColor color = getClass().getDeclaredAnnotation(TimerColor.class);

    public Timer(long delay, long period) {
        super(delay, period);
        Validate.notNull(color, "Timer must have Color Annotation!");

        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    @Override
    public void execute() {
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

    public void showTo(Player player) {
        this.shown.add(player.getUniqueId());
    }

    public void hideFrom(Player player) {
        if (shown.contains(player.getUniqueId())) this.shown.remove(player.getUniqueId());
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
        return color.brackets() +"["
                + color.timer() + (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds)
                + color.brackets() + "]";
    }

    private void sendActionBar() {
        if (shown.isEmpty()) return;

        for (UUID uuid : shown) {
            final Player player = Bukkit.getPlayer(uuid);

            if (player == null) return;
            if (!player.isOnline()) return;

            Messanger.sendActionBar(player, this.toString());
        }
    }
}
