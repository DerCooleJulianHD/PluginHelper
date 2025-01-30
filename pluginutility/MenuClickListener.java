package pluginutility;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MenuClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // check for instance of who clicked
        if (!(event.getWhoClicked() instanceof final Player player)) return;

        final Inventory inventory = event.getClickedInventory();
        // looking for a valid inventory...
        if (inventory == null) return;

        // ...and check if inventory which has been clicked was a menu inventory...
        if (!(inventory.getHolder() instanceof final Menu menu)) return;

        // this is the slot the player has been clicking on
        final int slot = event.getSlot();

        // asking if the right click type has been used
        if (event.isLeftClick()) menu.click(slot, player); // execute menu interaction

        // making item steal not possible if menu hasn't item-moving ability
        if (!menu.isItemMovable()) event.setCancelled(true); // canceling event if parameters doesn't trigger
    }
}
