
public abstract class PluginCommand implements CommandExecutor, TabCompleter {

    final Plugin plugin;

    protected final CommandInfo info;

    public PluginCommand(Plugin plugin) {
        this.plugin = plugin;
        this.info = getClass().getDeclaredAnnotation(CommandInfo.class);
        Validate.notNull(this.info, getClass().getName() + " Missing CommandInfo Annotation");
        plugin.getServer().getPluginCommand(info.name()).setExecutor(this);
    }

    @Override // [from: CommandExecutor]
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (this.requiresPermission() && (!sender.hasPermission(info.permission()))) {
            sender.sendMessage(MessageBuilder.build(plugin, ChatColor.RED + "You doun't have the permission to execute this command. (missing: " + info.permission() + ")"));
            return false;
        }

        if (info.requiresPlayer()) {
            if ((!isPlayer(sender))) {
                sender.sendMessage(MessageBuilder.build(plugin, ChatColor.RED + "Only players can execute this type of command."));
                return false;
            

            this.execute((Player) sender, args)
            return true;
        }
        this.execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] strings) {


        this.onCommand(sender, strings);
        return true;
    }

    public void sendHelp(CommandSender sender) {
        sender.sendMessage(MessageBuilder.build(plugin, ChatColor.RED + this.info.name() + " Help:"));

        list.values().forEach((s, command) -> {
            String builder = ChatColor.GRAY + "- " + ChatColor.RED + "/" + this.info.name() + " " + s +
                    ChatColor.DARK_GRAY + " : " + ChatColor.YELLOW + command.getDescription();

            sender.sendMessage(builder);
        });
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

    public String getDescription() {
        return info.description();
    }
}
