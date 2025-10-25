package de.xcuzimsmart.pluginhelper.code.main.java.utils;

import de.xcuzimsmart.pluginhelper.code.main.java.utils.annotations.Abstract;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.annotations.ColorCodeSupport;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.messanger.Colorize;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@Abstract
public class ItemStackBuilder {

    protected ItemStack itemStack;

    protected Material material;

    // subid of material
    protected int id;

    protected String displayName, name;

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

    @ColorCodeSupport("&")
    public final ItemStackBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        if (meta != null) meta.setDisplayName(Colorize.translateColorCodes(displayName));
        return this;
    }

    public final ItemStackBuilder setLore(List<String> lore) {
        if (meta != null) meta.setLore(lore);
        return this;
    }

    public final ItemStackBuilder setLore(String... lore) {
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

    public final ItemStackBuilder addEnchant(Enchantment enchantment, int lvl) {
        this.enchantments.put(enchantment, lvl);
        if (meta != null) meta.addEnchant(enchantment, lvl, true);
        return this;
    }

    public final ItemStackBuilder removeEnchant(Enchantment enchantment) {
        this.enchantments.remove(enchantment);
        if (meta != null) meta.removeEnchant(enchantment);
        return this;
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

    public final ItemStackBuilder setMeta(ItemMeta meta) {
        this.meta = meta;
        return this;
    }

    public final ItemStack build() {
        if (meta == null) return itemStack;
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public final ItemStackBuilder setName(String name) {
        this.name = Colorize.stripColor(name);
        return this;
    }

    public final String getName() {
        return name;
    }

    public final String getIDName() {
        return name;
    }

    public final ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
