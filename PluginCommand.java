
public abstract class PluginCommand implements CommandExecutor, TabCompleter {

    final Plugin plugin;

    protected final CommandInfo info;

    public PluginCommand(Plugin plugin) {
        this.plugin = plugin;
        this.info = getClass().getDeclaredAnnotation(CommandInfo.class);
        Validate.notNull(this.info, getClass().getName() + " misses CommandInfo Annotation");
        plugin.getServer().getPluginCommand(info.name()).setExecutor(this);
    }

    @Override // [from: CommandExecutor]
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (this.requiresPermission() && (!sender.hasPermission(info.permission()))) {
            sender.sendMessage(MessageBuilder.build(plugin, ChatColor.RED + "You doun't have the permission to execute this command. (missing: " + info.permission() + ")"));
            return false;
        }

        if (info.requiresPlayer() && (!isPlayer(sender))) {
            sender.sendMessage(MessageBuilder.build(plugin, ChatColor.RED + "Only players can execute this type of command."));
            return false;
        }

        this.execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] strings);

    public void sendHelp(CommandSender sender, String usage) {
        sender.sendMessage(MessageBuilder.build(plugin, usage));
    }

    public CommandInfo getInfo() {
        return info;
    }

    public boolean requiresPermission() {
        return info != null && info.permission().isEmpty();
    }

    public boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }
}


