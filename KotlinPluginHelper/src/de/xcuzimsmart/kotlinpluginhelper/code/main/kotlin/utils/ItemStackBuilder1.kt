package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.utils

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.*

@Abstract
open class ItemStackBuilder protected constructor(material: Material, id: Int, amount: Int) {
    val itemStack: ItemStack

    var material: Material?
        protected set

    // subid of material
    var id: Int
        protected set

    protected var displayName: String?
    protected var unbreakable: Boolean = false

    val enchantments: MutableMap<Enchantment?, Int?> = HashMap<Enchantment?, Int?>()

    protected var meta: ItemMeta?

    init {
        this.itemStack = ItemStack(material, amount, id.toByte().toShort())

        // from ItemStack of trooper class
        this.meta = itemStack.getItemMeta()

        this.material = material
        this.id = id
        this.displayName = meta!!.getDisplayName()
    }

    fun setDisplayName(displayName: String?): ItemStackBuilder {
        this.displayName = displayName
        if (meta != null) meta!!.setDisplayName(displayName)
        return this
    }

    fun setLore(lore: MutableList<String?>?): ItemStackBuilder {
        if (meta != null) meta!!.setLore(lore)
        return this
    }

    fun setLore(lore: Array<String?>): ItemStackBuilder {
        if (meta != null) {
            val list = Arrays.stream<String?>(lore).toList()
            if (list.isEmpty()) return this
            return setLore(list)
        }

        return this
    }

    fun setUnbreakable(b: Boolean): ItemStackBuilder {
        this.unbreakable = b
        if (meta != null) meta!!.spigot().setUnbreakable(b)
        return this
    }

    fun addEnchant(enchantment: Enchantment?, lvl: Int) {
        this.enchantments.put(enchantment, lvl)
    }

    fun removeEnchant(enchantment: Enchantment?) {
        this.enchantments.remove(enchantment)
    }

    fun setMaterial(material: Material?): ItemStackBuilder {
        this.material = material
        itemStack.setType(material)
        return this
    }

    fun setId(id: Int): ItemStackBuilder {
        this.id = id
        return this
    }

    fun setMeta(meta: ItemMeta?) {
        this.meta = meta
    }

    fun build(): ItemStack {
        if (meta == null) return itemStack

        meta!!.setDisplayName(displayName)
        meta!!.spigot().setUnbreakable(unbreakable)

        if (!enchantments.isEmpty()) enchantments.forEach { (enchantment: Enchantment?, lvl: Int?) ->
            meta!!.addEnchant(
                enchantment,
                lvl!!,
                true
            )
        }

        itemStack.setItemMeta(meta)
        return itemStack
    }

    fun update(): ItemStack {
        return this.build()
    }

    companion object {
        fun of(material: Material, id: Int, amount: Int): ItemStackBuilder {
            return ItemStackBuilder(material, id, amount)
        }
    }
}
