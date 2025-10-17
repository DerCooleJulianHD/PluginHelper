package de.xcuzimsmart.pluginhelper.code.main.java.command;

import de.xcuzimsmart.pluginhelper.code.main.java.bundle.Bundle;
import de.xcuzimsmart.pluginhelper.code.main.java.plugin.SpigotPlugin;

public final class CommandManager extends Bundle<PluginCommand> {

    @Override
    public void onRegisterObject(PluginCommand command) {
        SpigotPlugin.getInstance().getServer().getPluginCommand(command.getInfo().name()).setExecutor(command);
    }

    @Override
    public void onUnregisterObject(PluginCommand command) {
        SpigotPlugin.getInstance().getServer().getPluginCommand(command.getInfo().name()).setExecutor(null);
    }
}
