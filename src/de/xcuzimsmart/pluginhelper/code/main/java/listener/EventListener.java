package de.xcuzimsmart.pluginhelper.code.main.java.listener;

import org.bukkit.event.Listener;

public abstract class EventListener implements Listener {
    
    final String key;
    String name;

    boolean registered = false;

    public EventListener(String k, String name) {
        this.key = k;
        this.name = name;
    }

    public EventListener(String k, Class<? extends EventListener> clazz) {
        this(k, clazz.getSimpleName());
    }

    public final String getKey() {
        return key;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isRegistered() {
        return registered;
    }
}
