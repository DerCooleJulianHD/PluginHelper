package de.code.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {
    
    static Logger logger = Logger.getLogger(FileManager.class.getName());

    public static void copyInnerFiles(File target, File destination) {
        if (!target.exists()) return;
        try {
            FileInputStream in = new FileInputStream(target);
            FileOutputStream out = new FileOutputStream(destination);
            if (!destination.exists()) destination.mkdirs();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) out.write(buffer, 0, length);
            out.flush();
            in.close();
            out.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public static void copyDir(File dir, File destination) {
        if (!dir.exists()) return;
        if (!destination.exists()) destination.mkdirs();

        final File copy = new File(destination, dir.getName());

        if (!copy.isDirectory()) return;
        if (!copy.exists()) copy.mkdirs();
        copyInnerFiles(dir, copy);
    }

    public static void deleteFile(File target) {
        if (!target.exists()) return;

        if (target.isDirectory()) {
            File[] read = target.listFiles();
            if (read == null) return;
            if (hasChilds(target))
                for (File child : read) deleteFile(child);
        }

        target.delete();
    }

    public static boolean hasChilds(File dir) {
        final File[] buffer = dir.listFiles();
        return buffer != null && buffer.length > 0;
    }

    public static void renameFile(File target, String newName) {
        if (!target.exists()) return;
        File destination = new File(target.getParent(), newName);
        target.renameTo(destination);
    }

    public static boolean isFolderEmpty(File file) {
        if (!file.isDirectory()) return true;
        return hasChilds(file);
    }
}



