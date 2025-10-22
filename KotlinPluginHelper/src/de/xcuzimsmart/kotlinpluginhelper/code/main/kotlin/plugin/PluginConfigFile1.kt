package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.plugin

import de.xcuzimsmart.pluginhelper.code.main.kotlin.configuration.YamlConfigurator

class PluginConfigFile(loadOnInit: Boolean) :
    YamlConfigurator(SpigotPlugin.Companion.getInstance().getDataFolder(), "config.yml", loadOnInit), Prefixable {
    // returns the String that is set in plugin config.
    override fun getPrefix(): String? {
        return if (isSet("prefx")) readString("prefix") else ""
    }

    // sets the String value in plugin config on key 'prefix'
    override fun setPrefix(s: String?) {
        writeString("prefix", s)
    }

    fun setPrefix() {
        this.setPrefix("")
    }
}
