package pluginutility;

import org.bukkit.Material;

public class SimpleIcon extends Icon {

    public SimpleIcon(Menu menu, Material material, int id, int amount, String displayName) {
        super(menu, material, id, amount, displayName);
    }

    public SimpleIcon(Menu menu, Material material, int amount, String displayName) {
        super(menu, material, amount, displayName);
    }
}
