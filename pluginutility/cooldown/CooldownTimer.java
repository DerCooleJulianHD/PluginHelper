package pluginutility.cooldown;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pluginutility.MinecraftPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public record CooldownTimer(MinecraftPlugin plugin, int delay) {
    public static final Set<UUID> set = new HashSet<>();

    public void setCooldown(Player player) {
        set.add(player.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                set.remove(player.getUniqueId());
            }
        }.runTaskLater(plugin, delay);
    }

    public void indicatePlayer(Player player) {
        player.sendMessage(getCooldownMessage());
        // play sound to indicate player he's in cooldown
        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 2);
    }

    public String getCooldownMessage() {
        return plugin.prefix() + ChatColor.RED + "Please wait a few seconds until you use this again.";
    }

    public boolean isCooldown(Player player) {
        return set.contains(player.getUniqueId());
    }
}
