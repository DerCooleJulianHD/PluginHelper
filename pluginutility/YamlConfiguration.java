package pluginutility;

import org.bukkit.configuration.InvalidConfigurationException;

import java.io.*;

public class YamlConfiguration extends org.bukkit.configuration.file.YamlConfiguration {
    // this is the filename ending the file will have
    public final String ending = ".yaml";
    // this is the file which will be edited
    private final File file;

    public YamlConfiguration(File file) {
        file.getName().replaceAll(".yml", ending);
        if (!file.exists()) FileManager.createFile(file);
        this.file = file;
    }

    public YamlConfiguration(File destination, String fileName) {
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
    public void load() {
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
