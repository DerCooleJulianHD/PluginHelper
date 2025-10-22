package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.bundle

interface Bundle<T> {
    fun add(name: String?, t: T?)

    fun remove(name: String?)

    fun get(name: String?): T?

    fun contains(name: String?): Boolean

    val actives: MutableMap<String?, T?>?
}
