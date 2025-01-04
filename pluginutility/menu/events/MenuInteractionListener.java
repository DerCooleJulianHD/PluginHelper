package pluginutility.menu.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pluginutility.MinecraftPlugin;
import pluginutility.listener.PluginListener;
import pluginutility.menu.Menu;

public class MenuInteractionListener extends PluginListener implements Listener {

    // will be called at an interaction with a menu by clicking in inventory
    public MenuInteractionListener(MinecraftPlugin plugin) {
        super(plugin);
        this.enable();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // asking for the right instance of user if he's a player
        if (!(event.getWhoClicked() instanceof final Player player)) return;

        final Inventory inventory = event.getClickedInventory();
        final int slot = event.getSlot();

        // checking if inventory usage was on a valid slot and inventory instance
        if (inventory == null) return;

        // ...now checking if inventory was a menu instance...
        if (!(inventory.getHolder() instanceof final Menu menu)) return;

        // looking if item usage was valid
        if (!event.isLeftClick()) return;

        // calling the custom interaction code
        menu.click(slot, player);
        menu.onInteract();

        // ... setting true that players can't steal or move items.
        event.setCancelled(true);
    }
}
