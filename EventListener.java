
public class EventListener implements Listener {

    public static final Logger logger = LogManager.getLogger(EventListener.class);
    
    final String key;
    String name;

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


