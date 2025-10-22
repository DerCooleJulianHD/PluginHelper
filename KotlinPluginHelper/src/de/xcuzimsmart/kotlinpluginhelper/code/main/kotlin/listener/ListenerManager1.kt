package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.listener

import de.xcuzimsmart.pluginhelper.code.main.kotlin.plugin.SpigotPlugin
import org.bukkit.Bukkit
import java.util.function.Consumer
import kotlin.collections.HashMap
import kotlin.collections.MutableMap

class ListenerManager {
    val bundles: MutableMap<String?, ListenerBundle?> = HashMap<String?, ListenerBundle?>()

    fun register(name: String?, bundle: ListenerBundle) {
        bundles.put(name, bundle)

        val actives = bundle.actives

        actives.values.forEach(Consumer { listener: EventListener? ->
            if (!listener!!.isRegistered()) {
                Bukkit.getPluginManager().registerEvents(listener, SpigotPlugin.Companion.getInstance())
                listener.setRegistered(true)
            }
        })
    }

    fun unregister(name: String?) {
        val bundle = getBundle(name)

        if (bundle == null) return

        val actives = bundle.actives

        if (!actives.isEmpty()) {
            actives.values.forEach(Consumer { listener: EventListener? ->
                if (listener!!.isRegistered()) {
                    Bukkit.getPluginManager().registerEvents(listener, SpigotPlugin.Companion.getInstance())
                    listener.setRegistered(true)
                }
            })
        }

        bundles.remove(name)
    }

    fun getBundle(name: String?): ListenerBundle? {
        return bundles.get(name)
    }
}
