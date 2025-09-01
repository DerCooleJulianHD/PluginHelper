
public class CommandExecuteException extends Exception {

    private final PluginCommand command;

    public CommandExecuteException(String message, PluginCommand command) {
        super(message);
        this.command = command;
    }

    public CommandExecuteException(String message, Throwable cause, PluginCommand command) {
        super(message, cause);
        this.command = command;
    }

    public CommandExecuteException(Throwable cause, PluginCommand command) {
        super(cause);
        this.command = command;
    }

    public PluginCommand getCommand() {
        return command;
    }
}

