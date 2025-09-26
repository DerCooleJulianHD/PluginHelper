package de.xcuzimsmart.pluginhelper.code.git.branch.main.exceptions;

import java.security.InvalidParameterException;

public final class IndexOutOfRangeException extends InvalidParameterException {

    public IndexOutOfRangeException(String message) {
        super(message);
    }

    public IndexOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}

