package storages;

import file.FileManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;

public class YamlStorage extends YamlConfiguration {
    // this is the filename ending the file will have
    public final String ending = ".yaml";
    // this is the file which will be edited
    private final File file;

    public YamlStorage(File file) {
        file.getName().replaceAll(".yml", ending);
        if (!file.exists()) FileManager.createFile(file);
        this.file = file;
    }

    public YamlStorage(File destination, String fileName) {
        fileName.replaceAll(".yml", ending);
        this.file = new File(destination, (fileName.contains(ending) ? fileName : fileName  + ending));
        if (!file.exists()) FileManager.createFile(file);
    }

    // saves file data
    public void save() {
        try {
            this.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // loads the file data
    private void load() {
        try {
            this.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile() {
        return file;
    }
}
