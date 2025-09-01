
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {

    boolean requiresPlayer() default false;

    String name();

    String permission() default "";

    String description() default "";
}

