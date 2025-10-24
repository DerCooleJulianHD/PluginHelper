package de.xcuzimsmart.pluginhelper.code.main.java.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface Abstract {
    // description: class cannot be final, because this might be used abstract, without being an abstract class at all.
}
