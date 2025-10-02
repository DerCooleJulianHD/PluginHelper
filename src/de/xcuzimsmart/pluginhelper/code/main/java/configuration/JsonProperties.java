package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonProperties {

    boolean prettyPrinting() default false;

    boolean htmlEscaping() default false;

    boolean innerClazzSerialisation() default false;
}
