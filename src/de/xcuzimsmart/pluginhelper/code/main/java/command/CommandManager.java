package de.xcuzimsmart.pluginhelper.code.main.java.command;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.Bundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;
import org.bukkit.Bukkit;

public final class CommandManager extends Bundle<PluginCommand> {

    public CommandManager(SpigotPlugin plugin) {
        super(plugin);
    }

    @Override
    public void onRegisterObject(PluginCommand command) {
        Bukkit.getPluginCommand(command.getInfo().name()).setExecutor(command);
    }

    @Override
    public void onUnregisterObject(PluginCommand command) {
        Bukkit.getPluginCommand(command.getInfo().name()).setExecutor(null);
    }
}
