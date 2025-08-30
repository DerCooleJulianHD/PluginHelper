package minecraft.lib.plugins.exceptions;

import java.io.File;

public class IncorrectFilenameEndingException extends InvalidParameterException {

    public IncorrectFilenameEndingException(File file, String ending) {
        super(IncorrectFilenameEndingException.getExceptionMessage(file, ending));
    }

    public IncorrectFilenameEndingException(File file, String ending, Throwable cause) {
        super(IncorrectFilenameEndingException.getExceptionMessage(file, ending), cause);
    }

    private static String getExceptionMessage(File file, String ending) {
        return file.getName() + "does not end with: " + ending;
    }
}
