package me.dercoolejulianhd.api.minigame.utils.file;

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

    public static boolean createFiles(File file) {
        if (file.exists()) return true;
        final File destination = file.getParentFile();

        if (!destination.exists()) destination.mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file.exists();
    }

}
