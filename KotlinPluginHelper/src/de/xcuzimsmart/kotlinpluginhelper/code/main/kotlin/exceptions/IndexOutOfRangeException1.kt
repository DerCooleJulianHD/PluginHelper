package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.exceptions

import java.security.InvalidParameterException

class IndexOutOfRangeException : InvalidParameterException {
    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)
}

