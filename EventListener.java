package minecraft.lib.plugins.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.event.Listener;

public class EventListener implements Listener {

    public static final Logger logger = LogManager.getLogger(EventListener.class);

    private final String key;
    private String name;

    public EventListener(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public EventListener(String key, Class<? extends EventListener> clazz) {
        this(key, clazz.getName());
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
