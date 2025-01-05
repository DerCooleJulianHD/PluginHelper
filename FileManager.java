package pluginutility.file;

import java.io.File;
import java.io.IOException;

public class FileManager {

    // deletes a file and/or their directories
    public static boolean delete(File file) {
        // checking file is existing yet
        if (!file.exists()) return true;

        // ask for if the file we want to delete is a directory
        if (file.isDirectory()) {
            // here when file is a directory
            final File[] childs = file.listFiles();
            if (childs != null) for (File child : childs) delete(child); // delete every file in this directory though
        }

        // deletes the overall file and return true when file isn't exist anymore
        file.delete();
        return !file.exists();
    }


    // creates a file within directories
    public static boolean createFile(File file) {
        // asking if directory file is existing. when not it will be created.
        if (!file.getParentFile().exists()) {
            createDirectory(file.getParentFile()); // creating the parent file as the directory
        }

        try {
            file.createNewFile(); // creating the storage file in the parent file.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // checking if file is existing.
        return file.exists();
    }

    public static File createDirectory(File file) {
        // asking if file isn't a directory
        if (!file.isDirectory()) {
            throw new RuntimeException(file.getName() + " must be a directory file");
        }

        file.mkdirs(); // creates the directory file.
        return file;
    }

    public static File createDirectory(String pathname) {
        // creating file by this pathname
        File file = new File(pathname);
        return createDirectory(file);
    }
}
