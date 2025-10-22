package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.listener

import de.xcuzimsmart.pluginhelper.code.main.kotlin.bundle.Bundle
import de.xcuzimsmart.pluginhelper.code.main.kotlin.plugin.SpigotPlugin
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.plugin.PluginManager
import kotlin.collections.HashMap
import kotlin.collections.MutableMap

class ListenerBundle : Bundle<EventListener?> {
    val plugin: SpigotPlugin? = SpigotPlugin.Companion.getInstance()

    val pluginManager: PluginManager = Bukkit.getPluginManager()

    val actives: MutableMap<String?, EventListener?> = HashMap<String?, EventListener?>()

    override fun add(k: String?, listener: EventListener?) {
        if (contains(k)) return

        actives.put(k, listener)
        pluginManager.registerEvents(listener, plugin)
    }

    override fun remove(k: String?) {
        if (!contains(k)) return

        val listener = get(k)

        if (listener == null) return

        actives.remove(k)
        HandlerList.unregisterAll(listener)
    }

    override fun get(k: String?): EventListener? {
        return actives.get(k)
    }

    override fun contains(k: String?): Boolean {
        return actives.containsKey(k)
    }

    fun isRegistered(k: String?): Boolean {
        return contains(k)
    }

    override fun getActives(): MutableMap<String?, EventListener?> {
        return actives
    }
}
