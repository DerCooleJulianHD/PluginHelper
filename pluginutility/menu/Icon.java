package pluginutility.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.function.Consumer;

public record Icon(ItemStack itemStack, Consumer<Player> action) implements Cloneable {

    // this method will be called by interaction with an item in inventory
    public void click(Player player) {
        // checking if our action parameter isn't null
        if (action != null)
            action.accept(player); // execute the action
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
