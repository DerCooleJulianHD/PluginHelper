package de.xcuzimsmart.pluginhelper.code.main.java.menu;

public class SimpleMenu extends Menu {

    public SimpleMenu(String title, boolean glass) {
        super(Rows.THREE, title, true);
        if (glass) setGlassPanes(15);
    }
}
