package minecraft.lib.plugins.exceptions;

public class InvalidValueTypeException extends InvalidParameterException {

    public InvalidValueTypeException(String message) {
        super(message);
    }

    public InvalidValueTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
