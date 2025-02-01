
package pluginutility;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ItemStackBuilder extends ItemStack implements ConfigurationSerializable, Cloneable {
    // this is the subid of material type
    private final int id;
    private ItemMeta meta;

    public ItemStackBuilder(Material material, int id, int amount, short damage, String displayName, boolean unbreakable) {
        super(material, amount, (byte) id);
        this.id = id;
        this.setDurability((short) (this.getDurability() - damage));
        this.meta = this.getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        meta.setDisplayName(displayName);
        this.setItemMeta(meta);
    }

    public ItemStackBuilder(Material material, int id, int amount, ItemMeta meta) {
        this(material, id, amount, meta.getDisplayName());
        this.meta = meta;
        this.build(meta);
    }

    public ItemStackBuilder(Material material, int id, int amount, String displayName, boolean unbreakable) {
        this(material, id, amount, (short) 0, displayName, unbreakable);
    }

    public ItemStackBuilder(Material material, String displayName, boolean unbreakable) {
        this(material, 0, 1, displayName, unbreakable);
    }

    public ItemStackBuilder(Material material, int amount, String displayName, boolean unbreakable) {
        this(material, 0, amount, displayName, unbreakable);
    }

    public ItemStackBuilder(Material material, int id, int amount, String displayName) {
        this(material, id, amount, displayName, false);
    }

    public ItemStackBuilder(Material material, int id, String displayName) {
        this(material, id, 1, displayName, false);
    }

    public ItemStackBuilder setDisplayName(String displayName) {
        this.meta.setDisplayName(displayName);
        return this.build(meta);
    }

    public ItemStackBuilder setLore(String... lines) {
        this.meta.setLore(List.of(lines));
        return this.build(meta);
    }

    public ItemStackBuilder addEnchant(Enchantment enchantment, int level, boolean visible) {
        this.meta.addEnchant(enchantment, level, true);
        final ItemFlag flag = ItemFlag.HIDE_ENCHANTS;
        if (!visible) this.meta.addItemFlags(flag);
        else if (meta.hasItemFlag(flag)) meta.removeItemFlags(flag);
        return this.build(meta);
    }

    public ItemStackBuilder removeEnchant(Enchantment enchantment) {
        this.meta.removeEnchant(enchantment);
        final ItemFlag flag = ItemFlag.HIDE_ENCHANTS;
        if (meta.hasItemFlag(flag)) meta.removeItemFlags(flag);
        return this.build(meta);
    }

    public ItemStackBuilder setInvisibleEnchants() {
        final ItemFlag flag = ItemFlag.HIDE_ENCHANTS;
        meta.addItemFlags(flag);
        return this.build(meta);
    }

    public ItemStackBuilder setId(int id) {
        return new ItemStackBuilder(this.getType(), id, this.getAmount(), this.getDisplayName() ,this.isUnbreakable()).build();
    }

    public int getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.meta.getDisplayName();
    }

    public boolean isUnbreakable() {
        return this.meta.spigot().isUnbreakable();
    }

    public ItemStackBuilder setUnbreakable(boolean unbreakable) {
        this.meta.spigot().setUnbreakable(unbreakable);
        return this.build(meta);
    }

    public ItemStackBuilder setUnbreakable() {
        return this.setUnbreakable(true);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("material", this.getType().name());
        data.put("id", this.id);
        data.put("amount", this.getAmount());
        data.put("meta", this.getItemMeta());
        return data;
    }

    public static ItemStackBuilder deserialize(Map<String, Object> args) {
        Material material = Material.getMaterial(((String) args.get("material")).toUpperCase());
        int id = (int) args.get("id");
        int amount = (int) args.get("amount");
        ItemMeta meta = (ItemMeta) args.get("meta");
        return new ItemStackBuilder(material, id, amount, meta);
    }

    public ItemMeta getMeta() {
        return meta;
    }

    public ItemStackBuilder build() {
        return this.build(this.meta);
    }

    public ItemStackBuilder build(ItemMeta meta) {
        this.setItemMeta(meta);
        return this;
    }

    @Override
    public ItemStackBuilder clone() {
        return (ItemStackBuilder) super.clone();
    }
}