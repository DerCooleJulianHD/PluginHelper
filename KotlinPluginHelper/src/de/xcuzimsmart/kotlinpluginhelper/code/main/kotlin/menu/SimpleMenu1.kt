package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.menu

class SimpleMenu(title: String?, glass: Boolean) : Menu(Rows.THREE, title, true) {
    init {
        if (glass) setGlassPanes(15)
    }
}
