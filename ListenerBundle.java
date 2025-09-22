
public final class ListenerBundle {

    final Plugin plugin;
    static HashMap<String, EventListener> activeListeners;

    public ListenerBundle(Plugin plugin) {
        this.plugin = plugin;
        ListenerBundle.activeListeners = this;
    }

    public void register(EventListener listener) {
        if (isActive(listener.getKey())) return;
        activeListeners.put(listener.getKey(), listener);
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(listener, plugin);
    }
    
    void unregisterListener(String k) {
        if (!isActive(k)) return;
        EventListener listener = getListenerByKey(k);
        if (listener == null) return;
        HandlerList.unregisterAll(listener);
    }

    public void unregister(String k) {
        this.unregisterListener(k);
        this.remove(k);
    }

    public void unregisterAll() {
        if (this.isEmpty()) return;
        for (String k : activeListeners.keySet()) this.unregisterListener(k);
        this.clear();
    }

    public EventListener getListenerByName(String name) {
        if (this.isEmpty()) return null;

        for (String key : activeListeners.keySet()) {
            EventListener listener = getListenerByKey(key);
            if (listener == null) continue;
            if (listener.getName().equals("unknown")) continue;
            if (name.equals(listener.getName())) return listener;
        }

        return null;
    }

    @Nullable
    public EventListener getListenerByKey(String k) {
        return this.get(k);
    }

    public boolean isActive(String k) {
        return !this.isEmpty() && this.containsKey(k);
    }

    public static HashMap<String, EventListener> getActiveListeners() {
        return ListenerBundle.activeListeners;
    }
}
