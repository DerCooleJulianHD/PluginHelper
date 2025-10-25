package de.xcuzimsmart.pluginhelper.code.main.java.utils.messanger;

import de.xcuzimsmart.pluginhelper.code.main.java.plugin.interfaces.MinecraftPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.annotations.Abstract;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.annotations.ColorCodeSupport;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

@Abstract
@ColorCodeSupport("&")
public record Messager(MinecraftPlugin plugin) {
    /* ### send titles ### */

    public void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(Colorize.translateColorCodes(title), Colorize.translateColorCodes(subtitle));
    }

    public void sendTitle(Collection<? extends Player> collection, String title, String subtitle) {
        if (collection.isEmpty()) return;
        collection.forEach(target -> sendTitle(target, title, subtitle));
    }

    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        final PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        final PacketPlayOutTitle times = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);
        connection.sendPacket(times);

        if (subtitle != null) {
            final IChatBaseComponent subTitleComponent = IChatBaseComponent.ChatSerializer.a(
                    "{\"text\": \"" + Colorize.translateColorCodes(subtitle) + "\"}");
            final PacketPlayOutTitle subTitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subTitleComponent);
            connection.sendPacket(subTitlePacket);
        }

        if (title != null) {
            final IChatBaseComponent titleComponent = IChatBaseComponent.ChatSerializer.a(
                    "{\"text\": \"" + Colorize.translateColorCodes(title) + "\"}");
            final PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleComponent);
            connection.sendPacket(titlePacket);
        }
    }

    public void sendTitle(Collection<? extends Player> collection, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        if (collection.isEmpty()) return;
        collection.forEach(target -> sendTitle(target, title, subtitle, fadeIn, stay, fadeOut));
    }

    /* ### send actionbars ### */

    public void sendActionBar(Player player, String message) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(
                new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(
                        "{\"text\": \"" + Colorize.translateColorCodes(message) + "\"}"), (byte) 2));
    }

    public void sendActionBar(Collection<? extends Player> collection, String content) {
        if (collection.isEmpty()) return;
        for (Player target : collection) sendActionBar(target, content);
    }


    /* ### send messages ### */

    public void sendMessage(CommandSender entity, String message) {
        entity.sendMessage(MessageBuilder.build(plugin.getPlugin(), message));
    }


    public void broadcast(Collection<? extends CommandSender> collection, String message) {
        if (!collection.isEmpty()) collection.forEach(target -> sendMessage(target, message));
    }
}
