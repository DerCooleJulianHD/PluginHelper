package menu;

import MinecraftPlugin;
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

    public Rows getRows() {
        return rows;
    }


    public String getTitle() {
        return title;
    }


    public Map<Integer, Icon> getIcons() {
        return icons;
    }


    public void setIcon(int slot, Icon icon) {
        icons.put(slot, icon);
        this.updateInventory();
    }


    public void setIcons(Map<Integer, Icon> icons) {
        this.icons = icons;
        this.updateInventory();
    }


    public void removeIcon(int slot) {
        icons.remove(slot);
        this.updateInventory();
    }


    public void click(int slot, Player player) {
        final Icon icon = this.icons.get(slot);

        if (icon != null)
            icon.click(player);
    }


    @Deprecated
    public void updateInventory() {
        if (this.inventory == null) return;
        this.inventory.clear();

        if (this.icons.isEmpty()) return;
        this.icons.forEach((slot, icon) -> {
            this.inventory.setItem(slot, icon.itemStack());
        });
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public static Menu createMenu(MinecraftPlugin plugin, Rows rows, String title) {
        class DefaultMenu extends Menu {
            public DefaultMenu() {
                super(plugin, rows, title);
            }
        }

        return new DefaultMenu();
    }
}