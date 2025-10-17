package de.xcuzimsmart.pluginhelper.code.main.java.menu;

import de.xcuzimsmart.pluginhelper.code.main.java.utils.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.function.Consumer;

public abstract class Icon extends ItemStackBuilder implements Cloneable {

    private final Enchantment ENCHANTMENT_TYPE = Enchantment.DURABILITY;

    protected final Menu menu;
    protected Consumer<Player> action = null;

    protected Icon(Menu menu, Material material, int id, int amount, String displayName) {
        super(material, id, amount);
        this.menu = menu;
        if (!displayName.isEmpty()) this.setDisplayName(displayName);
        this.build();
    }

    protected Icon(Menu menu, Material material, int amount, String displayName) {
        this(menu, material, 0, amount, displayName);
    }

    protected Icon(Menu menu, Material material, int amount) {
        this(menu, material, 0, amount, "");
    }

    @Override
    public final Icon clone() {
        return (Icon) super.clone();
    }

    public final void enchant(boolean visible) {
        this.addEnchant(ENCHANTMENT_TYPE, 1);
        if (!visible)
            this.getItemMeta().addItemFlags(ItemFlag.HIDE_ENCHANTS);
    }

    public final boolean isEnchanted() {
        return (getItemMeta().hasEnchants() || getItemMeta().hasEnchant(ENCHANTMENT_TYPE));
    }

    public final void setEnchantments() {
        this.addEnchant(ENCHANTMENT_TYPE, 1);
    }

    public final void removeEnchantments() {
        this.removeEnchant(ENCHANTMENT_TYPE);
    }

    public final Menu getMenu() {
        return menu;
    }

    // defines what happens by clicking on it
    public final void setAction(Consumer<Player> action) {
        this.action = action;
    }

    public final Consumer<Player> getAction() {
        return action;
    }

    public final boolean isMovable() {
        return this instanceof Moveable;
    }
}
