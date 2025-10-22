package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.exceptions

class InvalidParameterException : Exception {
    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)
}

