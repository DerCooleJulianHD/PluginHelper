package de.xcuzimsmart.pluginhelper.code.main.java.menu;

import java.util.HashMap;
import java.util.Map;

public abstract class PagedMenu extends Menu {

    final Map<Integer, Menu> pages = new HashMap<>();

    public PagedMenu(Rows rows, String title, boolean keepOpen) {
        super(rows, title, keepOpen);
    }

    public final void addPage(Menu menu) {
        pages.put((pages.isEmpty() ? 1 : pages.size()), menu);
    }

    public Menu getPage(int id) {
        if (pages.isEmpty()) return this;
        if (id < 1 || id > pages.size()) return this;
        if (!pages.containsKey(id)) return this;

        return pages.get(id);
    }
}
