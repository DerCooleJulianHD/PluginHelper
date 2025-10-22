package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.configuration

import de.xcuzimsmart.pluginhelper.code.main.kotlin.utils.Loadable
import java.io.File

abstract class Config(// the folder the config file is in.
    val dir: File?, fileName: String, loadOnInit: Boolean
) : Loadable {
    // returns the config file.
    val file: File?

    var loaded: Boolean = false

    init {
        this.file = File(dir, fileName)
        if (loadOnInit) load()
    }

    constructor(dir: String, fileName: String, loadOnInit: Boolean) : this(File(dir), fileName, loadOnInit)

    // returns true if config file does end with 'filename.{ending}'
    fun hasEnding(fileName: String?, ending: String): Boolean {
        if (fileName == null) return false
        return fileName.endsWith(ending)
    }

    // returns true when config has been loaded.
    override fun isLoaded(): Boolean {
        return loaded
    }

    // sets if config has been loaded or not.
    override fun setLoaded(loaded: Boolean) {
        this.loaded = loaded
    }

    // returns true when 'getDir()' does not return null and file is exist.
    fun exists(): Boolean {
        return dir != null && dir.exists() && file!!.exists()
    }
}
