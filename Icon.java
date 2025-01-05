package pluginutility.menu.icons;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import pluginutility.builder.ItemStackBuilder;
import pluginutility.menu.Menu;

import java.util.function.Consumer;

public abstract class Icon extends ItemStackBuilder implements Cloneable {
    public static final Enchantment ENCHANTMENT_TYPE = Enchantment.DURABILITY;
    public static final ItemFlag ITEM_FLAG_TYPE = ItemFlag.HIDE_ENCHANTS;

    private final Menu menu;
    private final Consumer<Player> action;

    protected Icon(Menu menu, Material material, int id, int amount, String displayName, Consumer<Player> action) {
        super(material, id, amount, displayName);
        this.menu = menu;
        this.action = action;
    }

    protected Icon(Menu menu, Material material, int amount, String displayName, Consumer<Player> action) {
        this(menu, material, 0, amount, displayName, action);
    }

    // this method will be called by interaction with an item in inventory
    public void click(Player player) {
        // checking if our action parameter isn't null
        if (action != null) {
            // execute the action
            action.accept(player);
        }
    }

    @Override
    public Icon clone() {
        return (Icon) super.clone();
    }

    public void enchant(boolean b) {
        final ItemMeta meta = this.getItemMeta();
        if (b) {
            // adding the enchantments to itemStack
            setEnchantments(meta);
            setItemFlags(meta);
        } else {
            // checking if there are any enchantments set
            if (!(isEnchanted() && meta.hasItemFlag(ITEM_FLAG_TYPE))) return;
            // removing the enchantments to itemStack
            removeEnchantments(meta);
            removeItemFlags(meta);
        }
        this.setItemMeta(meta);
    }

    private void setEnchantments(ItemMeta meta) {
        meta.addEnchant(ENCHANTMENT_TYPE, 1, true);
    }

    private void setItemFlags(ItemMeta meta) {
        meta.addItemFlags(ITEM_FLAG_TYPE);
    }

    public boolean isEnchanted() {
        final ItemMeta meta = this.getItemMeta();
        return (meta.hasEnchants() && meta.hasEnchant(ENCHANTMENT_TYPE));
    }

    private void removeEnchantments(ItemMeta meta) {
        meta.removeEnchant(ENCHANTMENT_TYPE);
    }

    private void removeItemFlags(ItemMeta meta) {
        meta.removeItemFlags(ITEM_FLAG_TYPE);
    }

    public Menu getMenu() {
        return menu;
    }

    public Consumer<Player> getAction() {
        return action;
    }
}
