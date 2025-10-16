package de.xcuzimsmart.pluginhelper.code.main.java.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@AbstractUsage
public class ItemStackBuilder extends ItemStack {

    Material material;

    // subid of material
    int id;

    String displayName;
    boolean unbreakable = false;

    final Map<Enchantment, Integer> enchantments = new HashMap<>();

    ItemMeta meta;

    protected ItemStackBuilder(Material material, int id, int amount) {
        super(material, amount, (byte) id);

        // from ItemStack of trooper class
        this.meta = this.getItemMeta();

        this.material = material;
        this.id = id;
        this.displayName = meta.getDisplayName();
    }

    public static ItemStackBuilder of(Material material, int id, int amount) {
        return new ItemStackBuilder(material, id, amount);
    }

    public ItemStackBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        if (meta != null) meta.setDisplayName(displayName);
        return this;
    }

    public ItemStackBuilder setLore(List<String> lore) {
        if (meta != null) meta.setLore(lore);
        return this;
    }

    public ItemStackBuilder setLore(String[] lore) {
        if (meta != null) {
            final List<String> list = Arrays.stream(lore).toList();
            if (list.isEmpty()) return this;
            return setLore(list);
        }

        return this;
    }

    public ItemStackBuilder setUnbreakable(boolean b) {
        this.unbreakable = b;
        if (meta != null) meta.spigot().setUnbreakable(b);
        return this;
    }

    public void addEnchant(Enchantment enchantment, int lvl) {
        this.enchantments.put(enchantment, lvl);
    }

    public void removeEnchant(Enchantment enchantment) {
        this.enchantments.remove(enchantment);
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStackBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public int getId() {
        return id;
    }

    public ItemStackBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    public ItemStack build() {
        if (meta == null) return this;

        meta.setDisplayName(displayName);
        meta.spigot().setUnbreakable(unbreakable);

        if (!enchantments.isEmpty()) {
            enchantments.forEach((enchantment, lvl) -> {
                meta.addEnchant(enchantment, lvl, true);
            });
        }

        this.setItemMeta(meta);
        return this;
    }

    public ItemStack update() {
        return this.build();
    }
}
