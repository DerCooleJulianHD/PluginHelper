package pluginutility.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pluginutility.MinecraftPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import pluginutility.builder.ItemStackBuilder;
import pluginutility.cooldown.CooldownTimer;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public abstract class Menu implements InventoryHolder {

    private final MinecraftPlugin plugin;
    private final CooldownTimer cooldownTimer;
    private final Rows rows;
    private final String title;
    private final Inventory inventory;
    // this map contains all the items
    private Map<Integer, Icon> icons;

    public Menu(MinecraftPlugin plugin, Rows rows, String title) {
        this.plugin = plugin;
        this.cooldownTimer = new CooldownTimer(plugin, /* this is the wait time in seconds */ 5);
        this.rows = rows;
        this.title = title;
        this.inventory = Bukkit.createInventory(this, rows.getSlots(), title);
        this.icons = new HashMap<>();
    }

    protected Menu(MinecraftPlugin plugin, Rows rows, String title, Map<Integer, Icon> icons) {
        this.plugin = plugin;
        this.cooldownTimer = new CooldownTimer(plugin, /* this is the wait time in seconds */ 5);
        this.rows = rows;
        this.title = title;
        this.inventory = Bukkit.createInventory(this, rows.getSlots(), title);
        this.icons = icons;
    }

    // adds an item to inventory
    public void setIcon(int slot, Icon icon) {
        icons.put(slot, icon); // adding it to the map
        this.updateInventory(); // calling the update method to change everything
    }

    // sets all item to inventory for filling.
    public void setIcons(Map<Integer, Icon> icons) {
        this.icons = icons;
        this.updateInventory(); // calling the update method to change everything
    }

    // removes an item from the menu
    public void removeIcon(int slot) {
        icons.remove(slot); // removing it form the map
        this.updateInventory(); // calling the update method to change everything
    }

    // returns the item from a specific slot
    @Nullable
    public Icon getIcon(int slot) {
        return this.icons.get(slot);
    }

    // this will be called by interaction with a slot
    public void click(int slot, Player player) {
        final Icon icon = getIcon(slot);

        if (icon != null)
            icon.click(player);
    }

    public void updateInventory() { // this method is being called when something changed
        // checking if the inventory has been initialized
        if (this.inventory == null) return;
        // ...while true, thw inventory will be cleared.
        this.inventory.clear();

        // asking for items that will be set in inventory if it's empty
        if (this.icons.isEmpty()) return;

        this.icons.forEach((slot, icon) -> {
            // setting each item to inventory slots
            this.inventory.setItem(slot, icon.itemStack());
        });
    }

    // interaction logic here, will be called by clicking in any slot of menu.
    public abstract void onInteract();

    // Identifier to indicate how big the menu is
    public Rows getRows() {
        return rows;
    }

    public String getTitle() {
        return title;
    }

    public Map<Integer, Icon> getIcons() {
        return icons;
    }

    public MinecraftPlugin getPlugin() {
        return plugin;
    }

    public CooldownTimer getCooldownTimer() {
        return cooldownTimer;
    }

    // improves menu style by adding glass-panes to empty slots
    public void setGlassEnabled() {
        // looping every slot in inventory...
        for (int slot = 0; slot < rows.getSlots(); slot++) {
            // ...and check if there will be an item. While true this slot will be ignored
            if (this.icons.containsKey(slot)) continue;

            // creating the glass itemStack
            final ItemStack GLASS_PANE = new ItemStackBuilder(Material.STAINED_GLASS_PANE, 15, 1, false).setDisplayName(" ").build();
            // placing the glass pane in our inventory
            this.inventory.setItem(slot, GLASS_PANE);
        }
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}