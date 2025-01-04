package pluginutility.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pluginutility.cooldown.CooldownTimer;

import java.util.function.Consumer;

public record Icon(Menu menu, ItemStack itemStack, Consumer<Player> action) implements Cloneable {

    // this method will be called by interaction with an item in inventory
    public void click(Player player) {
        // checking if our action parameter isn't null
        if (action != null) {
            final CooldownTimer cooldownTimer = menu.getCooldownTimer();
            // ask for cooldown
            if (cooldownTimer.isCooldown(player)) {
                cooldownTimer.indicatePlayer(player);
                return;
            }

            // execute the action
            action.accept(player);
        }
    }

    @Override
    public Icon clone() {
        try {
            return (Icon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
