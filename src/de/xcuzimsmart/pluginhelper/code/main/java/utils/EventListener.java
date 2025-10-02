package de.xcuzimsmart.pluginhelper.code.main.java.utils;

import org.bukkit.event.Listener;

public abstract class EventListener implements Listener {
    
    final String key;
    String name;

    public EventListener(String k, String name) {
        this.key = k;
        this.name = name;
    }

    public EventListener(String k, Class<? extends EventListener> clazz) {
        this(k, clazz.getSimpleName());
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
