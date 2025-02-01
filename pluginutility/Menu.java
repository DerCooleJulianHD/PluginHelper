package pluginutility;

import jdk.jfr.Description;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.InventoryView;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Menu implements InventoryHolder {
    protected final MinecraftPlugin plugin;
    protected final Rows rows;
    protected final String title;
    protected final Inventory inventory;
    protected final Map<Integer, Icon> icons; @Description("this map contains all the items")
    protected final List<Player> viewers;
    protected InventoryView view;
    protected boolean keepOpen;

    protected Result result = Result.ALLOW;

    public Menu(MinecraftPlugin plugin, Rows rows, String title, Map<Integer, Icon> icons, boolean keepOpen) {
        this.plugin = plugin;
        this.rows = rows;
        this.title = title;
        this.inventory = Bukkit.createInventory(this, rows.getSlots(), title);
        this.icons = icons;
        this.viewers = new ArrayList<>();
        this.keepOpen = keepOpen;
    }

    public Menu(MinecraftPlugin plugin, Rows rows, String title, boolean keepOpen) {
        this(plugin, rows, title, new HashMap<>(), keepOpen);
    }

    @Description("this will be called by interaction with a slot")
    public void click(int slot, Player player) {
        // only executing when player is viewing this menu, so that only viewers can interact with icons
        if (!isViewing(player)) return;

        // this is the icon which player has been interacted with.
        final Icon icon = getIcon(slot);

        // if icon is valid... the action of icon will be executed
        if (icon == null) return;

        // check is item clickable
        if (icon instanceof final Icon.Interactive clickable) {
            // executing code when clicking on icon
            clickable.onClick(player);

            if (!isKeepOpen()) player.closeInventory(); // closing menu when is not keep open.
            else updateInventory(); // otherwise we're updating the inventory
        }
    }

    @Description("improves menu style by adding glass-panes to empty slots")
    public void setGlassPanes(int color) {
        // looping every slot in inventory...
        for (int slot = 0; slot < rows.getSlots(); slot++) this.setGlassPanes(color, slot);
    }

    public void setGlassPanes(int color, int slot) {
        // ...and check if there will be an item. While true this slot will be ignored
        if (this.icons.containsKey(slot)) return;
        // placing the glass pane in our inventory
        this.inventory.setItem(slot, new ItemStackBuilder(Material.STAINED_GLASS_PANE, color, 1, ""));
    }

    @Description("does open player the menu inventory")
    public void open(Player player) {
        // set player as our viewer
        setViewer(player);
        // calling custom event method to execute some custom code on open.
        this.onMenuOpen(player);
        this.view = player.openInventory(this.getInventory());
        this.updateInventory();
        // play sound to improve style
        player.playSound(player.getLocation(), Sound.CLICK, 3, 1);
    }

    @Description("check if player is locking in this menu")
    public boolean isViewing(Player player) {
        if (this.view != null && this.viewers.contains(player)) return player.getOpenInventory().equals(this.view);
        else return false;
    }

    @Description("this method has to be called when something changed")
    public void updateInventory() {
        this.setIcons();
        this.icons.forEach(inventory::setItem);
        this.viewers.forEach(Player::updateInventory);
    }

    @Description("sets item moving in or stealing from menu (not)possible")
    public void setItemMoveable(boolean b) {
        this.result = (b ? Result.ALLOW : Result.DENY);
    }

    public boolean isItemMovable() {
        return result == Result.ALLOW;
    }

    @Description("when return true, the inventory will not close by clicking on icons")
    public boolean isKeepOpen() {
        return keepOpen;
    }

    public void setKeepOpen(boolean keepOpen) {
        this.keepOpen = keepOpen;
    }

    @Description("adds an item to inventory")
    public void setIcon(int slot, Icon icon) {
        icons.put(slot, icon); // adding it to the map
    }

    @Description("sets all item to inventory for filling")
    public abstract void setIcons();

    @Description("removes an item from the menu")
    public void removeIcon(int slot) {
        icons.remove(slot); // removing it form the map
        this.inventory.clear(slot); // removes out of inventory
    }

    @Description("Identifier to indicate how big the menu is")
    public Rows getRows() {
        return rows;
    }

    @Description("the title of the menu inventory")
    public String getTitle() {
        return title;
    }

    @Description("this contains all the items that will be set in inventory by opening it up")
    public Map<Integer, Icon> getIcons() {
        return icons;
    }

    @Description("returns the plugin which is using this api")
    public MinecraftPlugin getPlugin() {
        return plugin;
    }

    @Description("adding a player to the viewers of this menu")
    public void setViewer(Player viewer) {
        this.viewers.add(viewer);
    }

    @Description("defines what should happen when player opens this menu")
    public void onMenuOpen(Player whoOpened) {}

    @Nullable
    @Description("returns the item from a specific slot")
    public Icon getIcon(int slot) {
        return this.icons.get(slot);
    }

    @Override
    @Description("returns the inventory the player will be looking in")
    public Inventory getInventory() {
        return this.inventory;
    }

    @Nullable
    @Description("returns the inventoryView the player is viewing in")
    public InventoryView getView() {
        return view;
    }

    @Nullable
    @Description("a list that contains all the players, which has this menu open")
    public List<Player> getViewers() {
        return viewers;
    }


    @Description("sizes of menu inventory")
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

        public int getValue() {
            return i;
        }

        public int getSlots() {
            return this.getValue() * 9;
        }

        @Nullable
        @Description(" returns the rows object by only passing in the correct number of how many rows")
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

    public enum Result {
        ALLOW,
        DENY
    }
}