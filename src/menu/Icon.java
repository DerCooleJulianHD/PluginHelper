package menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public record Icon(ItemStack itemStack, Consumer<Player> action) implements Cloneable {

    public void click(Player player) {
        if (action != null)
            action.accept(player);
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
