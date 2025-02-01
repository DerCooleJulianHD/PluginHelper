package pluginutility;

import org.bukkit.ChatColor;

public class LinearProgressBar {

    private final StringBuilder builder;
    private final int length;

    private ChatColor color;

    public LinearProgressBar(int length, ChatColor color) {
        this.length = length;
        this.color = color;
        this.builder = new StringBuilder(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH);
        this.builder.append(" ".repeat(Math.max(0, length)));
    }

    public void setProgress(double percentage) {
        if (percentage < 0.0) {
            return;
        }

        if (percentage > 1) {
            throw new RuntimeException("Percentage of Progressbar cannot higher than 100%");
        }

        int progressValue = (int) Math.floor(length * percentage);
        this.builder.replace(0, progressValue, color.toString() + ChatColor.STRIKETHROUGH + " ");
    }

    public void setProgress(ProgressValue progress) {
        this.setProgress(progress.value);
    }

    public int getLength() {
        return length;
    }

    protected StringBuilder getBuilder() {
        return builder;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public String get() {
        return builder.toString();
    }

    public enum ProgressValue {
        ONE_QUARTER(0.45),
        ONE_THIRD(0.33),
        HALF(0.5),
        TWO_THIRD(0.66),
        THREE_QUARTER(0.75),
        MAX(1.0);

        public final double value;

        ProgressValue(double value) {
            this.value = value;
        }

        public static double valueOf(ProgressValue progress) {
            return progress.value;
        }
    }
}