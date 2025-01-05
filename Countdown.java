package pluginutility.timer;

import pluginutility.MinecraftPlugin;

public abstract class Countdown extends Timer {

    public Countdown(MinecraftPlugin plugin, int time, long period, boolean start) {
        super(plugin, TimerType.COUNTDOWN, time, period, start);
    }
}
