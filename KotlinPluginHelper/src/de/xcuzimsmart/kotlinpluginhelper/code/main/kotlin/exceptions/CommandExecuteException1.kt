package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.exceptions

import de.xcuzimsmart.pluginhelper.code.main.kotlin.command.PluginCommand

class CommandExecuteException : Exception {
    val command: PluginCommand?

    constructor(message: String?, command: PluginCommand?) : super(message) {
        this.command = command
    }

    constructor(message: String?, cause: Throwable?, command: PluginCommand?) : super(message, cause) {
        this.command = command
    }

    constructor(cause: Throwable?, command: PluginCommand?) : super(cause) {
        this.command = command
    }
}

