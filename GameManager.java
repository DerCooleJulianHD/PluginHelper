
public class GameManager {

    static File dir;
    final String[] names;

    public GameManager(File dir) {
        GameManager.dir = dir;
        if (!dir.exists()) dir.mkdirs();

        this.names = dir.list();
    }

    public void loadGame(GameMap map) {
        final Game game = new Game(map);
    }

    public void loadGame(int id) {
        if (names.length != 0) {
            if (id >= 0 && id < names.length || id == names.length) {
                final String name = this.list().get(id);
                if (name != null)
                    loadGame(new OriginalGameMap(name));
            }
        }
    }

    public void loadRandomGame() {
        final Random random = new Random();
        final int id = random.nextInt(names.length);
        this.loadGame(id);
    }

    public static File getDir() {
        return dir;
    }

    public String[] getNames() {
        return names;
    }

    public List<String> list() {
        return names == null ? new ArrayList<>() : Arrays.stream(names).toList();
    }
}

