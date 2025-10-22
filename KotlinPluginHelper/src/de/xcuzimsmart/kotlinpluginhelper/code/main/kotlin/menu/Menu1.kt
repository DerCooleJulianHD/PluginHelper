package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.menu

import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.ItemStackBuilder
import org.apache.commons.lang.Validate
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.InventoryView
import org.bukkit.plugin.PluginManager
import java.awt.SystemColor.menu
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.MutableList
import kotlin.collections.MutableMap

abstract class Menu(
    var rows: Rows, // Identifier to indicate how big the menu is
    val title: String?, // the title of the menu inventory
    var isKeepOpen: Boolean // when return true, the inventory will not close by clicking on icons
) : InventoryHolder {
    val inventory: Inventory = Bukkit.createInventory(this, rows.slots, title)

    // this contains all the items.
    val icons: MutableMap<Int?, Icon?> = HashMap() // this map contains all the items

    protected val viewers: MutableList<Player> = ArrayList<Player>()

    // returns the inventoryView the player is viewing in
    var view: InventoryView? = null
        protected set

    // improves menu style by adding glass-panes to empty slots
    fun setGlassPanes(color: Int) {
        // looping over every single slot in the inventory.
        for (slot in 0..<rows.slots) this.setGlassPanes(color, slot)
    }

    fun setGlassPanes(color: Int, slot: Int) {
        // ...and check if there will be an item. While true this slot will be ignored
        if (this.icons.containsKey(slot)) return
        // placing the glass pane in our inventory
        this.inventory.setItem(slot, ItemStackBuilder.Companion.of(Material.STAINED_GLASS_PANE, color, 1).build())
    }

    // does open player the menu inventory
    fun open(player: Player) {
        // set player as our viewer
        addViewer(player)
        // calling custom event method to execute some custom code on open.
        this.onMenuOpen(player)
        this.view = player.openInventory(this.getInventory())
        // play sound to improve style
        player.playSound(player.location, Sound.CLICK, 3f, 1f)
    }

    // check if player is locking in this menu
    fun isViewing(player: Player): Boolean {
        if (this.view != null && this.viewers.contains(player)) return player.openInventory == this.view
        else return false
    }

    // adds an item to inventory
    fun setIcon(slot: Int, icon: Icon) {
        icons[slot] = icon // adding it to the map
        inventory.setItem(slot, icon.build())
    }

    // removes an item from the menu
    fun removeIcon(slot: Int) {
        icons.remove(slot) // removing it form the map
        inventory.clear(slot) // removes out of inventory
    }

    // adding a player to the viewers of this menu
    fun addViewer(viewer: Player) {
        this.viewers.add(viewer)
    }

    // defines what should happen when player opens this menu
    fun onMenuOpen(whoOpened: Player) {}

    // returns the item from a specific slot
    fun getIcon(slot: Int): Icon? {
        return this.icons.get(slot)
    }

    // returns the inventory the player will be looking in
    override fun getInventory(): Inventory {
        return this.inventory
    }

    // a list that contains all the players, which has this menu open
    fun getViewers(): MutableList<Player> {
        return viewers
    }


    // sizes of menu inventory
    enum class Rows(val value: Int) {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        Six(6);

        val slots: Int get() = this.value * 9

        companion object {
            // returns the rows object by only passing in the correct number of how many rows
            fun getRowsByInt(amount: Int): Rows? {
                // checking if parameter size is between 0 and 6
                // because of Minecraft Inventory sizes can't be bigger than 6 rows <=> 54 slots
                Validate.isTrue(amount in 1..6, "amount of rows must be a number between '0' and '6'")
                for (rows in Rows.entries) {
                    if (amount == rows.value) {
                        return rows
                    }
                }

                // returning null when nothing found in loop
                return null
            }
        }
    }

    class ClickListener : Listener {
        val pluginManager: PluginManager = Bukkit.getPluginManager()

        @EventHandler
        fun onInventoryClick(event: InventoryClickEvent) {
            // check for instance of who clicked.
            if (event.whoClicked !is Player) return

            val player: Player = event.whoClicked as Player;

            val inventory = event.clickedInventory ?: return // looking for a valid inventory.

            if (inventory.holder !is Menu) return  // check if inventory what has been clicked, was a menu inventory.

            val menu: Menu? = inventory.holder as Menu?;

            menu?.isViewing(player)?.let { if (!it) return }  // only executing when player is viewing this menu, so that only viewers can interact with icons.

            if (!event.isLeftClick) return  // asking for the right click type.


            val slot = event.slot // this is the slot the player has been clicking on.
            val icon: Icon? = menu?.getIcon(slot)

            // if icon is valid, the action of icon will be executed.
            if (icon != null) {
                val action = icon.action // getting the action declaration.

                if (action != null) {
                    // executing code when clicking on icon.
                    action.accept(player)
                    icon.update(slot) // updating the item if it has to change.

                    if (!menu.isKeepOpen) player.closeInventory() // closing menu when is not keep open.
                }

                // making item steal not possible by canceling event, if icon hasn't item-moving ability.
                if (!icon.isMovable) event.isCancelled = true
            }

            pluginManager.callEvent(MenuInteractEvent(player, menu, slot, icon))
        }
    }
}