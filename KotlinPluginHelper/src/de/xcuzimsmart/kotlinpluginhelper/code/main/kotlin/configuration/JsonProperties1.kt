package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.configuration

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonProperties(
    val prettyPrinting: Boolean = false,
    val htmlEscaping: Boolean = false,
    val innerClazzSerialisation: Boolean = false
)
