package de.xcuzimsmart.pluginhelper.code.main.java.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@Abstract
public class ItemStackBuilder {

    protected final ItemStack itemStack;

    protected Material material;

    // subid of material
    protected int id;

    protected String displayName;
    protected boolean unbreakable = false;

    protected final Map<Enchantment, Integer> enchantments = new HashMap<>();

    protected ItemMeta meta;

    protected ItemStackBuilder(Material material, int id, int amount) {
        this.itemStack = new ItemStack(material, amount, (byte) id);

        // from ItemStack of trooper class
        this.meta = itemStack.getItemMeta();

        this.material = material;
        this.id = id;
        this.displayName = meta.getDisplayName();
    }

    public static ItemStackBuilder of(Material material, int id, int amount) {
        return new ItemStackBuilder(material, id, amount);
    }

    public final ItemStackBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        if (meta != null) meta.setDisplayName(displayName);
        return this;
    }

    public final ItemStackBuilder setLore(List<String> lore) {
        if (meta != null) meta.setLore(lore);
        return this;
    }

    public final ItemStackBuilder setLore(String[] lore) {
        if (meta != null) {
            final List<String> list = Arrays.stream(lore).toList();
            if (list.isEmpty()) return this;
            return setLore(list);
        }

        return this;
    }

    public final ItemStackBuilder setUnbreakable(boolean b) {
        this.unbreakable = b;
        if (meta != null) meta.spigot().setUnbreakable(b);
        return this;
    }

    public final void addEnchant(Enchantment enchantment, int lvl) {
        this.enchantments.put(enchantment, lvl);
    }

    public final void removeEnchant(Enchantment enchantment) {
        this.enchantments.remove(enchantment);
    }

    public final Material getMaterial() {
        return material;
    }

    public final ItemStackBuilder setMaterial(Material material) {
        this.material = material;
        itemStack.setType(material);
        return this;
    }

    public final int getId() {
        return id;
    }

    public final ItemStackBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public final Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public final void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    public final ItemStack build() {
        if (meta == null) return itemStack;

        meta.setDisplayName(displayName);
        meta.spigot().setUnbreakable(unbreakable);

        if (!enchantments.isEmpty()) enchantments.forEach((enchantment, lvl) -> meta.addEnchant(enchantment, lvl, true));

        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public final ItemStack update() {
        return this.build();
    }
}
