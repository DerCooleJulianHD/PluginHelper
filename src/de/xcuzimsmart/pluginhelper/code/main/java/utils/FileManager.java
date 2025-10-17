package de.xcuzimsmart.pluginhelper.code.main.java.utils;

import org.bukkit.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FileManager {
    
    static Logger logger = Logger.getLogger(FileManager.class.getName());

    public static void copyInnerFiles(File from, File to) {
        FileUtil.copy(from, to);
    }

    public static void copyDir(File dir, File to) {
        if (!dir.exists()) return;
        if (to.isDirectory() && !to.exists()) to.mkdirs();

        final File copy = new File(to, dir.getName());

        if (!copy.isDirectory()) return;
        if (!copy.exists()) copy.mkdirs();
        copyInnerFiles(dir, copy);
    }

    public static void deleteFile(File file) {
        if (!file.exists()) return;

        if (file.isDirectory()) {
            final File[] childs = file.listFiles();
            if (childs == null) return;
            if (hasChilds(file))
                for (File child : childs) deleteFile(child);
        }

        file.delete();
    }

    public static void zip(File target) {
        if (!target.exists()) return;

        final File dir = target.getParentFile();
        final File zip = new File(dir, target.getName() + ".zip");

        try {
            if (!zip.exists()) zip.createNewFile();

            copyDir(target, zip);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public static void unzip(File zip, File to) {
        if (zip.exists()) copyInnerFiles(zip, to);
    }

    public static boolean hasChilds(File dir) {
        return dir.listFiles() != null && Objects.requireNonNull(dir.listFiles()).length > 0;
    }

    public static void renameFile(File file, String newName) {
        if (!file.exists()) return;
        File destination = new File(file.getParent(), newName);
        file.renameTo(destination);
    }

    public static boolean isFolderEmpty(File file) {
        return file.isDirectory() && (!hasChilds(file));
    }
}



