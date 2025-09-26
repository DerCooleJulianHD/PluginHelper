package de.xcuzimsmart.pluginhelper.code.git.branch.main.exceptions;

public final class InvalidParameterException extends Exception {

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}

