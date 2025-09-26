package de.xcuzimsmart.pluginhelper.code.commands;

import de.xcuzimsmart.pluginhelper.code.Main;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.command.CommandInfo;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.command.PluginCommand;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.timer.Timer;
import de.xcuzimsmart.pluginhelper.code.git.branch.main.utils.Messanger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

@CommandInfo(name = "timer", permission = "command.timer", description = "Controls Timer.", requiresPlayer = true)
public class TimerCommand extends PluginCommand {

    public TimerCommand(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length == 0) {
            this.sendSyntax(sender, "resume", "pause", "time <time>", "reset");
            return;
        }

        final Timer timer = Main.getTimer();

        switch (args[0].toLowerCase()) {
            case "resume": {
                if (timer.isRunning()) {
                    Messanger.broadcast(plugin, sender, ChatColor.RED + "Timer is already running.");
                    return;
                }

                timer.setRunning(true);
                Messanger.broadcast(plugin, sender, ChatColor.GRAY + "Timer has been resumed.");
                break;
            }

            case "pause": {
                if (!timer.isRunning()) {
                    Messanger.broadcast(plugin, sender, ChatColor.RED + "Timer is not running.");
                    return;
                }

                timer.setRunning(false);
                Messanger.broadcast(plugin, sender, ChatColor.GRAY + "Timer has been paused.");
                break;
            }

            case "time": {
                if (args.length != 2) {
                    this.sendSyntax(sender, "time <time>");
                    return;
                }

                String s = args[1];

                timer.setRunning(false);

                try {
                    if (s.endsWith("s")) timer.setSeconds(Integer.parseInt(s.replaceAll("s", "")));
                    else if (s.endsWith("m")) timer.setMinutes(Integer.parseInt(s.replaceAll("m", "")));
                    else if (s.endsWith("h")) timer.setHours((Integer.parseInt(s.replaceAll("h", ""))));

                    Messanger.broadcast(plugin, sender, ChatColor.GRAY + "Timer has become a new time.");
                } catch (NumberFormatException e) {
                    Messanger.broadcast(plugin, sender, ChatColor.RED + "Parameter 2 must be a Number");
                }

                break;
            }

            case "reset": {
                timer.setRunning(false);
                timer.setHours(0);
                timer.setMinutes(0);
                timer.setSeconds(0);
                Messanger.broadcast(plugin, sender, ChatColor.GRAY + "Timer has been reset.");
                break;
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] strings) {
        return List.of();
    }
}
