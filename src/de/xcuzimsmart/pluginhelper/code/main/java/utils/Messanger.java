package de.xcuzimsmart.pluginhelper.code.main.java.utils;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
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

    public static void broadcast(CommandSender entity, String message)  {
        entity.sendMessage(MessageBuilder.build(SpigotPlugin.getInstance(), message));
    }

    public static void broadcast(Collection<? extends CommandSender> collection, String message) {
        if (!collection.isEmpty()) collection.forEach(target -> broadcast(target, message));
    }
}
