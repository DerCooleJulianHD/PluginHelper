package pluginutility.menu;

import org.bukkit.event.Event;

public abstract class ItemMovable {

    private Event.Result result = Event.Result.DEFAULT;

    // sets item moving in or stealing from menu (not)possible
    public void setItemMoveable(boolean b) {
        this.setResult(b ? Event.Result.ALLOW : (result == Event.Result.DENY ? result = Event.Result.DEFAULT : result));
    }

    public boolean isItemMovable() {
        return getResult() == Event.Result.DEFAULT;
    }

    protected Event.Result getResult() {
        return result;
    }

    protected void setResult(Event.Result result) {
        this.result = result;
    }
}
