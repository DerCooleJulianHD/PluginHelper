package de.xcuzimsmart.pluginhelper.code.main.java.test;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestItem extends ItemStack {

    public TestItem() {
        super(Material.PAPER);

        ItemMeta meta = super.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "Ein Test");
        meta.spigot().setUnbreakable(true);

        meta.addEnchant(Enchantment.DURABILITY, 1, true);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        super.setItemMeta(meta);
    }
}
