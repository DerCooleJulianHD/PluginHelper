package pluginutility;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        final Server server = this.getServer();
        server.getPluginManager().registerEvents(new MenuClickListener(), this);
    }
}
