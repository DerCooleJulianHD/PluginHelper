package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.menu

import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.ItemStackBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import java.util.function.Consumer

abstract class Icon protected constructor(
    val menu: Menu,
    material: Material?,
    id: Int,
    amount: Int,
    displayName: String
) : ItemStackBuilder(material, id, amount), Cloneable {
    private val ENCHANTMENT_TYPE: Enchantment = Enchantment.DURABILITY

    // defines what happens by clicking on it
    var action: Consumer<Player?>? = null

    init {
        if (!displayName.isEmpty()) this.setDisplayName(displayName)
    }

    protected constructor(menu: Menu, material: Material?, amount: Int, displayName: String) : this(
        menu,
        material,
        0,
        amount,
        displayName
    )

    protected constructor(menu: Menu, material: Material?, amount: Int) : this(menu, material, 0, amount, "")

    public override fun clone(): Icon? {
        try {
            return super.clone() as Icon?
        } catch (e: CloneNotSupportedException) {
            throw RuntimeException(e)
        }
    }

    fun enchant(visible: Boolean) {
        this.addEnchant(ENCHANTMENT_TYPE, 1)
        if (!visible) meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
    }

    val isEnchanted: Boolean
        get() = (meta.hasEnchants() || meta.hasEnchant(ENCHANTMENT_TYPE))

    fun setEnchantments() {
        this.addEnchant(ENCHANTMENT_TYPE, 1)
    }

    fun removeEnchantments() {
        this.removeEnchant(ENCHANTMENT_TYPE)
    }

    val isMovable: Boolean
        get() = this is Moveable

    fun update(slot: Int) {
        menu.inventory.setItem(slot, this.update())
    }
}
