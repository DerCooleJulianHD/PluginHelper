
public class EventListener implements Listener {
    
    final String k;
    String name;

    public EventListener(String k, String name) {
        this.k = k;
        this.name = name;
    }

    public EventListener(String k, Class<? extends EventListener> clazz) {
        this(k, clazz.getName());
    }

    public String getKey() {
        return k;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
