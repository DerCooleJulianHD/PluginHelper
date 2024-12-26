package me.xcuzimsmart.utils.file;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static boolean delete(File file) {
        if (!file.exists()) return true;

        if (file.isDirectory()) {
            final File[] childs = file.listFiles();
            if (childs != null) {
                for (File child : childs) delete(child);
            }
        }

        file.delete();
        return file.exists();
    }


    public static boolean createFile(File file) {
        if (!file.getParentFile().exists())
            createDirectory(file.getParentFile());

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file.exists();
    }

    public static File createDirectory(File file) {
        file.mkdirs();
        return file;
    }
}
