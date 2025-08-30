package minecraft.lib.plugins.listener;

import minecraft.lib.plugins.extension.PluginLibrary;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nullable;
import java.util.HashMap;

public final class ListenerBundle extends HashMap<String, EventListener> {

    public final PluginLibrary library;
    private static ListenerBundle activeListeners;

    public ListenerBundle(PluginLibrary library) {
        this.library = library;
        ListenerBundle.activeListeners = this;
    }

    public void register(EventListener listener) {
        if (isActive(listener.getKey())) return;
        this.put(listener.getKey(), listener);
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(listener, library.getJavaPlugin());
    }

    private void unregisterListener(String key) {
        if (!isActive(key)) return;
        EventListener listener = getListenerByKey(key);
        if (listener == null) return;
        HandlerList.unregisterAll(listener);
    }

    public void unregister(String key) {
        this.unregisterListener(key);
        this.remove(key);
    }

    public void unregisterAll() {
        if (this.isEmpty()) return;
        for (String key : this.keySet()) this.unregisterListener(key);
        this.clear();
    }

    public EventListener getListenerByName(String name) {
        if (this.isEmpty()) return null;

        for (String key : this.keySet()) {
            EventListener listener = getListenerByKey(key);
            if (listener == null) continue;
            if (listener.getName().equals("unknown")) continue;
            if (name.equals(listener.getName())) return listener;
        }

        return null;
    }

    @Nullable
    public EventListener getListenerByKey(String key) {
        return this.get(key);
    }

    public boolean isActive(String key) {
        return !this.isEmpty() && this.containsKey(key);
    }

    public static HashMap<String, EventListener> getActiveListeners() {
        return ListenerBundle.activeListeners;
    }
}
