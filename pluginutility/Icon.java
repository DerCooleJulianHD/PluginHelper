package pluginutility;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Icon extends ItemStackBuilder implements Cloneable {
    public static final Enchantment ENCHANTMENT_TYPE = Enchantment.DURABILITY;
    protected final Menu menu;

    protected Icon(Menu menu, Material material, int id, int amount, String displayName) {
        super(material, id, amount, displayName);
        this.menu = menu;
    }

    protected Icon(Menu menu, Material material, int amount, String displayName) {
        this(menu, material, 0, amount, displayName);
    }

    @Override
    public Icon clone() {
        return (Icon) super.clone();
    }

    public void enchant(boolean visible) {
        this.addEnchant(ENCHANTMENT_TYPE, 1, visible);
    }

    public boolean isEnchanted() {
        final ItemMeta meta = this.getItemMeta();
        return (meta.hasEnchants() && meta.hasEnchant(ENCHANTMENT_TYPE));
    }

    private void setEnchantments() {
        this.addEnchant(ENCHANTMENT_TYPE, 1, false);
    }

    private void removeEnchantments() {
        this.removeEnchant(ENCHANTMENT_TYPE);
    }

    public Menu getMenu() {
        return menu;
    }

    // class to define if item can be clicked or not and what happens by doing it
    public interface Clickable {
        void onClick(Player player); // this will be executing by clicking on random Clickable
    }
}
