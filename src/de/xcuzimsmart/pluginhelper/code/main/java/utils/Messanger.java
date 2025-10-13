package de.xcuzimsmart.pluginhelper.code.main.java.utils;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

public final class Messanger {

    public static void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(title, subtitle);
    }

    public static void sendTitle(Collection<? extends Player> collection, String title, String subtitle) {
        if (collection.isEmpty()) return;
        collection.forEach(target -> Messanger.sendTitle(target, title, subtitle));
    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        final PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        final PacketPlayOutTitle times = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);
        connection.sendPacket(times);

        if (subtitle != null) {
            final IChatBaseComponent subTitleComponent = IChatBaseComponent.ChatSerializer.a(
                    "{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', subtitle) + "\"}");
            final PacketPlayOutTitle subTitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subTitleComponent);
            connection.sendPacket(subTitlePacket);
        }

        if (title != null) {
            final IChatBaseComponent titleComponent = IChatBaseComponent.ChatSerializer.a(
                    "{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', title) + "\"}");
            final PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleComponent);
            connection.sendPacket(titlePacket);
        }
    }

    public static void sendTitle(Collection<? extends Player> collection, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        if (collection.isEmpty()) return;
        collection.forEach(target -> Messanger.sendTitle(target, title, subtitle, fadeIn, stay, fadeOut));
    }

    public static void sendActionBar(Player player, String message) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(
                new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(
                        "{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}"), (byte) 2));
    }

    public static void sendActionBar(Collection<? extends Player> collection, String content) {
        if (collection.isEmpty()) return;
        for (Player target : collection) Messanger.sendActionBar(target, content);
    }

    public static void broadcast(SpigotPlugin plugin, CommandSender entity, String message)  {
        entity.sendMessage(MessageBuilder.build(plugin, message));
    }

    public static void broadcast(SpigotPlugin plugin, Collection<? extends CommandSender> collection, String message) {
        if (collection.isEmpty()) return;
        final String s = MessageBuilder.build(plugin, message);
        collection.forEach(target -> target.sendMessage(s));
    }

    public static void log(SpigotPlugin plugin, String log) {
        final ConsoleCommandSender console = Bukkit.getConsoleSender();

        broadcast(plugin, console, log);
    }

    public static void logError(SpigotPlugin plugin, String message, Throwable ex) {
        final ConsoleCommandSender console = Bukkit.getConsoleSender();

        broadcast(plugin, console, message);
        broadcast(plugin, console, ChatColor.RED.toString() + ex);

        ex.printStackTrace();
    }
}
