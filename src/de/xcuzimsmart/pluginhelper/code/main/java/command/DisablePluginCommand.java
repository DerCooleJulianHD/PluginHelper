package de.xcuzimsmart.pluginhelper.code.main.java.command;

import de.xcuzimsmart.pluginhelper.code.main.java.Main;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.Messanger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@CommandInfo(name = "disableplugin", permission = "pluginhelper.commands.operation", requiresPlayer = false)
public final class DisablePluginCommand extends PluginCommand {

    public DisablePluginCommand(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) /* syntax: /disableplugin <name> */ {
            this.sendSyntax(sender, "<name>");
            return;
        }

        final PluginManager pluginManager = Bukkit.getPluginManager();

        if (args[0].equalsIgnoreCase(Main.getInstance().getName())) {
            Messanger.broadcast(plugin, sender, ChatColor.RED + Main.getInstance().getName() + " cannot be disabled it self.");
            return;
        }

        final Plugin target = pluginManager.getPlugin(args[0]);

        if (target == null) {
            Messanger.broadcast(plugin, sender, ChatColor.RED + "'" + args[0] + "' not found.");
            return;
        }

        if (!target.isEnabled()) {
            Messanger.broadcast(plugin, sender, ChatColor.RED + "'" + args[0] + "' is already disabled.");
            return;
        }

        try {
            pluginManager.disablePlugin(target);
        } catch (Exception e) {
            Messanger.broadcast(plugin, sender, ChatColor.RED + "Something went wrong trying to disable '" + args[0] + "'.");
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
            return;
        }

        Messanger.broadcast(plugin, sender, ChatColor.GRAY + target.getName() + " has been disabled.");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] strings) {
        return getEnabledPlugins();
    }

    private List<String> getEnabledPlugins() {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        final Plugin[] plugins = pluginManager.getPlugins();
        final List<String> list = new ArrayList<>();

        if (plugins == null) return list;

        for (Plugin target : plugins) if (target.isEnabled()) list.add(target.getName());
        return list;
    }
}
