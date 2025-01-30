package pluginutility;

import org.bukkit.Material;

public abstract class IconClickable extends Icon implements Icon.Clickable {

    public IconClickable(Menu menu, Material material, int id, int amount, String displayName) {
        super(menu, material, id, amount, displayName);
    }

    public IconClickable(Menu menu, Material material, int amount, String displayName) {
        super(menu, material, amount, displayName);
    }
}
