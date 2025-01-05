package pluginutility.menu.icons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pluginutility.menu.Menu;

import java.util.function.Consumer;

public class SimpleIcon extends Icon {
    public SimpleIcon(Menu menu, Material material, int id, int amount, String displayName, Consumer<Player> action) {
        super(menu, material, id, amount, displayName, action);
    }

    public SimpleIcon(Menu menu, Material material, int amount, String displayName, Consumer<Player> action) {
        super(menu, material, amount, displayName, action);
    }
}
