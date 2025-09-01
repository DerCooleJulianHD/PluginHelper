
public class MessageBuilder {

    public static String buildMessageOutput(String prefix, String message) {
        StringBuilder out = new StringBuilder();
        if (prefix != null) out.append(prefix);
        out.append(message);
        return ChatColor.translateAlternateColorCodes('&', out.toString());
    }

    public static String build(Plugin plugin, String message) {
        if (!(plugin instanceof Prefixable prefixable)) return buildMessageOutput(null, message);
        return buildMessageOutput(prefixable.getPrefix(), message);
    }

    public static String message(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
