package pluginutility.menu;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.InventoryView;
import pluginutility.MinecraftPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import pluginutility.builder.ItemStackBuilder;
import pluginutility.cooldown.CooldownTimer;
import pluginutility.menu.icons.Icon;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public abstract class Menu extends ItemMovable implements InventoryHolder {
    private final MinecraftPlugin plugin;
    private final CooldownTimer cooldownTimer;
    private final Rows rows;
    private final String title;
    private final Inventory inventory;
    private InventoryView view;
    // this map contains all the items
    private Map<Integer, Icon> icons;

    private Player viewer;

    public Menu(MinecraftPlugin plugin, Rows rows, String title, Map<Integer, Icon> icons, CooldownTimer cooldown) {
        this.plugin = plugin;
        this.cooldownTimer = cooldown;
        this.rows = rows;
        this.title = title;
        this.inventory = Bukkit.createInventory(this, rows.getSlots(), title);
        this.icons = icons;
    }

    public Menu(MinecraftPlugin plugin, Rows rows, String title, CooldownTimer cooldown) {
        this(plugin, rows, title, new HashMap<>(), cooldown);
    }

    public Menu(MinecraftPlugin plugin, Rows rows, String title) {
        this(plugin, rows, title, new HashMap<>(), new CooldownTimer(plugin, 0));
    }

    // this will be called by interaction with a slot
    public void click(int slot, Player player) {
        // only executing when player is viewing this menu, so that only viewers can interact with icons
        if (!isViewing(player)) return;

        // this is the icon which player has been interacted with.
        final Icon icon = getIcon(slot);

        // if icon is valid... the action of icon will be executed
        if (icon != null) icon.click(player);
    }

    public void updateInventory(boolean clear) { // this method has to be called when something changed
        // checking if the inventory has been initialized
        if (this.inventory == null) return;

        if (clear /* while true, the inventory will be cleared */) this.inventory.clear();

        // asking for items that will be set in inventory if it's empty
        if (this.icons.isEmpty()) return;

        // setting each item to inventory slots
        this.icons.forEach(this.inventory::setItem);
    }

    // improves menu style by adding glass-panes to empty slots
    public void setGlassEnabled() {
        // looping every slot in inventory...
        for (int slot = 0; slot < rows.getSlots(); slot++) {
            // ...and check if there will be an item. While true this slot will be ignored
            if (this.icons.containsKey(slot)) continue;

            // placing the glass pane in our inventory
            this.inventory.setItem(slot, new ItemStackBuilder(Material.STAINED_GLASS_PANE, 15, 1, ""));
        }
    }

    // does open player the menu inventory
    public void open(Player player) {
        // set player as our viewer
        setViewer(player);
        // calling custom event method to execute some custom code on open.
        this.onMenuOpening(player);
        this.view = player.openInventory(this.getInventory());
        // play sound to improve style
        player.playSound(player.getLocation(), Sound.CLICK, 3, 1);
    }

    public boolean isViewing(Player player) {
        if (this.view != null) {
            return player.getOpenInventory().equals(this.view);
        } else return false;
    }

    public void updateInventory() {
        this.updateInventory(true);
    }

    // adds an item to inventory
    public void setIcon(int slot, Icon icon) {
        icons.put(slot, icon); // adding it to the map
    }

    // sets all item to inventory for filling.
    public void setIcons(Map<Integer, Icon> icons) {
        this.icons = icons;
    }

    // removes an item from the menu
    public void removeIcon(int slot) {
        icons.remove(slot); // removing it form the map
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

    public CooldownTimer getCooldownTimer() {
        return cooldownTimer;
    }

    private void setViewer(Player viewer) {
        this.viewer = viewer;
    }

    public abstract void onMenuOpening(Player whoOpened);

    // returns the item from a specific slot
    @Nullable
    public Icon getIcon(int slot) {
        return this.icons.get(slot);
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Nullable
    public InventoryView getView() {
        return view;
    }

    @Nullable
    public Player getViewer() {
        return viewer;
    }
}