package de.xcuzimsmart.pluginhelper.code.main.java.timer;

import org.bukkit.ChatColor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimerColor {

    ChatColor brackets();

    ChatColor timer();

}
