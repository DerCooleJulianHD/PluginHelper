package pluginutility.menu;

import pluginutility.MinecraftPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import java.util.HashMap;
import java.util.Map;

public abstract class Menu implements InventoryHolder {

    private final MinecraftPlugin plugin;
    private final Rows rows;
    private final String title;
    private final Inventory inventory;
    // this map contains all the items
    private Map<Integer, Icon> icons;

    public Menu(MinecraftPlugin plugin, Rows rows, String title) {
        this.plugin = plugin;
        this.rows = rows;
        this.title = title;
        this.inventory = Bukkit.createInventory(this, rows.getSlots(), title);
        this.icons = new HashMap<>();
    }

    protected Menu(MinecraftPlugin plugin, Rows rows, String title, Map<Integer, Icon> icons) {
        this.plugin = plugin;
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

    // this will be called by interaction with a slot
    public void click(int slot, Player player) {
        final Icon icon = this.icons.get(slot);

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

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

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
}