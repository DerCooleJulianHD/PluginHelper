package de.xcuzimsmart.pluginhelper.code.main.java.menu;

import de.xcuzimsmart.pluginhelper.code.main.java.utils.ItemStackBuilder;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Menu implements InventoryHolder {

    protected final Rows rows;
    protected final String title;
    protected final Inventory inventory;
    protected final Map<Integer, Icon> icons; // this map contains all the items
    protected final List<Player> viewers;
    protected InventoryView view;
    protected boolean keepOpen;

    public Menu(Rows rows, String title, Map<Integer, Icon> icons, boolean keepOpen) {
        this.rows = rows;
        this.title = title;
        this.inventory = Bukkit.createInventory(this, rows.getSlots(), title);
        this.icons = icons;
        this.viewers = new ArrayList<>();
        this.keepOpen = keepOpen;
    }

    public Menu(Rows rows, String title, boolean keepOpen) {
        this(rows, title, new HashMap<>(), keepOpen);
    }

    // improves menu style by adding glass-panes to empty slots
    public final void setGlassPanes(int color) {
        // looping every slot in inventory...
        for (int slot = 0; slot < rows.getSlots(); slot++) this.setGlassPanes(color, slot);
    }

    public final void setGlassPanes(int color, int slot) {
        // ...and check if there will be an item. While true this slot will be ignored
        if (this.icons.containsKey(slot)) return;
        // placing the glass pane in our inventory
        this.inventory.setItem(slot, ItemStackBuilder.of(Material.STAINED_GLASS_PANE, color, 1).build());
    }

    // does open player the menu inventory
    public final void open(Player player) {
        // set player as our viewer
        addViewer(player);
        // calling custom event method to execute some custom code on open.
        this.onMenuOpen(player);
        this.view = player.openInventory(this.getInventory());
        // play sound to improve style
        player.playSound(player.getLocation(), Sound.CLICK, 3, 1);
    }

    // check if player is locking in this menu
    public final boolean isViewing(Player player) {
        if (this.view != null && this.viewers.contains(player)) return player.getOpenInventory().equals(this.view);
        else return false;
    }

    // when return true, the inventory will not close by clicking on icons
    public final boolean isKeepOpen() {
        return keepOpen;
    }

    public final void setKeepOpen(boolean keepOpen) {
        this.keepOpen = keepOpen;
    }

    // adds an item to inventory
    public final void setIcon(int slot, Icon icon) {
        icons.put(slot, icon); // adding it to the map
    }

    // removes an item from the menu
    public final void removeIcon(int slot) {
        icons.remove(slot); // removing it form the map
        this.inventory.clear(slot); // removes out of inventory
    }

    // Identifier to indicate how big the menu is
    public final Rows getRows() {
        return rows;
    }

    // the title of the menu inventory
    public final String getTitle() {
        return title;
    }

    // this contains all the items that will be set in inventory by opening it up
    public final Map<Integer, Icon> getIcons() {
        return icons;
    }

    // adding a player to the viewers of this menu
    public final void addViewer(Player viewer) {
        this.viewers.add(viewer);
    }

    // defines what should happen when player opens this menu
    public final void onMenuOpen(Player whoOpened) {}

    @Nullable
    // returns the item from a specific slot
    public final Icon getIcon(int slot) {
        return this.icons.get(slot);
    }

    @Override
    // returns the inventory the player will be looking in
    public final Inventory getInventory() {
        return this.inventory;
    }

    @Nullable
    // returns the inventoryView the player is viewing in
    public final InventoryView getView() {
        return view;
    }

    @Nullable
    // a list that contains all the players, which has this menu open
    public final List<Player> getViewers() {
        return viewers;
    }


    // sizes of menu inventory
    public enum Rows {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        Six(6);

        private final int i;

        Rows(int i) {
            this.i = i;
        }

        public final int getValue() {
            return i;
        }

        public final int getSlots() {
            return this.getValue() * 9;
        }

        @Nullable
        // returns the rows object by only passing in the correct number of how many rows
        public static Rows getRowsByInt(int amount) {
            // checking if parameter size is between 0 and 6
            // because of Minecraft Inventory sizes can't be bigger than 6 rows <=> 54 slots
            Validate.isTrue(amount > 0 && amount <= 6, "amount of rows must be a number between '0' and '6'");
            for (Rows rows : Rows.values()) {
                if (amount == rows.i) {
                    return rows;
                }
            }

            // returning null when nothing found in loop
            return null;
        }
    }

    public static final class ClickListener implements Listener {

        final PluginManager pluginManager = Bukkit.getPluginManager();

        @EventHandler
        public void onInventoryClick(InventoryClickEvent event) {
            // check for instance of who clicked.
            if (!(event.getWhoClicked() instanceof Player player)) return;

            Inventory inventory = event.getClickedInventory();

            if (inventory == null) return; // looking for a valid inventory.
            if (!(inventory.getHolder() instanceof Menu menu)) return; // check if inventory what has been clicked, was a menu inventory.
            if (!menu.isViewing(player)) return; // only executing when player is viewing this menu, so that only viewers can interact with icons.
            if (!event.isLeftClick()) return; // asking for the right click type.

            final int slot = event.getSlot(); // this is the slot the player has been clicking on.
            final Icon icon = menu.getIcon(slot);

            // if icon is valid, the action of icon will be executed.
            if (icon != null) {
                final Consumer<Player> action = icon.getAction(); // getting the action declaration.

                if (action != null) {
                    // executing code when clicking on icon.
                    action.accept(player);
                    icon.update(slot); // updating the item if it has to change.

                    if (!menu.isKeepOpen()) player.closeInventory(); // closing menu when is not keep open.
                }

                // making item steal not possible by canceling event, if icon hasn't item-moving ability.
                if (!icon.isMovable()) event.setCancelled(true);
            }

            pluginManager.callEvent(new MenuInteractEvent(player, menu, slot, icon));
        }
    }
}