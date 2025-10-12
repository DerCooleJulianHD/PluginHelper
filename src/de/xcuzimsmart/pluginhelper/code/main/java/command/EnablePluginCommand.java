package de.xcuzimsmart.pluginhelper.code.main.java.command;

import de.xcuzimsmart.pluginhelper.code.main.java.utils.Messanger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@CommandInfo(name = "enableplugin", permission = "pluginhelper.commands.operation", requiresPlayer = false)
public final class EnablePluginCommand extends PluginCommand {

    public EnablePluginCommand(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) /* syntax: /disableplugin <name> */ {
            this.sendSyntax(sender, "<name>");
            return;
        }

        final PluginManager pluginManager = Bukkit.getPluginManager();

        final File pluginFile = new File("./plugins/", args[0]);

        if (!pluginFile.exists()) {
            Messanger.broadcast(plugin, sender, ChatColor.RED + "'" + args[0] + "' not found.");
            return;
        }

        try {
            Plugin target = pluginManager.loadPlugin(pluginFile);

            if (target == null) return;

            if (target.isEnabled()) {
                Messanger.broadcast(plugin, sender, ChatColor.RED + "'" + args[0] + "' is already enabled.");
                return;
            }

            pluginManager.enablePlugin(target);
        } catch (Exception e) {
            Messanger.broadcast(plugin, sender, ChatColor.RED + "Something went wrong trying to load or enable '" + args[0] + "'.");
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
            return;
        }

        Messanger.broadcast(plugin, sender, ChatColor.GRAY + args[0] + " has been enabled.");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] strings) {
        return getDisabledPlugins();
    }

    private List<String> getDisabledPlugins() {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        final Plugin[] plugins = pluginManager.getPlugins();
        final List<String> list = new ArrayList<>();

        if (plugins == null) return list;

        for (Plugin target : plugins) if (!target.isEnabled()) list.add(target.getName());
        return list;
    }
}
