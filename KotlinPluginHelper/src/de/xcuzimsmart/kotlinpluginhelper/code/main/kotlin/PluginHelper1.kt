package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin

import de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin.PluginConfigFile
import de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin.SpigotPlugin
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class PluginHelper(
    override val plugin: Plugin?,
    override val asJavaPlugin: JavaPlugin?,
    override val configuration: de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin.PluginConfigFile?,
    override val pluginName: String?
) : de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin.SpigotPlugin() {
    override fun onEnable() {
        this.setPrefix(getPrefix())
        if (_root_ide_package_.de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin.SpigotPlugin.config?.isEmpty == true) _root_ide_package_.de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin.SpigotPlugin.Companion.config?.setPrefix(prefix);
    }

    companion object {
        fun get(): PluginHelper? {
            return instance as PluginHelper?
        }
    }
}
