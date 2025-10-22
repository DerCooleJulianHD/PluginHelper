package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.listener

import org.bukkit.event.Listener

abstract class EventListener(val key: String?, var name: String?) : Listener {
    var isRegistered: Boolean = false

    constructor(k: String?, clazz: Class<out EventListener?>) : this(k, clazz.getSimpleName())
}
