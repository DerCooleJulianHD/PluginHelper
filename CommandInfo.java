package minecraft.lib.plugins.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {

    boolean requiresPlayer() default false;

    String name();

    String permission() default "";

    String description() default "";
}
