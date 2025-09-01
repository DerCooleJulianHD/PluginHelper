
public class FileManager {

    public static Logger logger = Logger.getLogger(FileManager.class.getName());

    public static void copyFile(File target, File destination) {
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
            logger.log(Level.SERVRE, e.getLocalizedMessage(), e);
        }
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


