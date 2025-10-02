package de.xcuzimsmart.pluginhelper.code.main.java.exceptions;

import java.security.InvalidParameterException;

public final class IndexOutOfRangeException extends InvalidParameterException {

    public IndexOutOfRangeException(String message) {
        super(message);
    }

    public IndexOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}

