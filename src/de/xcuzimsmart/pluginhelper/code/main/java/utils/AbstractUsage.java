package de.xcuzimsmart.pluginhelper.code.main.java.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface AbstractUsage {
    // description: class cannot be final, because this might be used abstract, without being an abstract class at all.
}
