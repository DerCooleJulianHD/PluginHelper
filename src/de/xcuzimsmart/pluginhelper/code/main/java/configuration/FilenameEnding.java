package de.xcuzimsmart.pluginhelper.code.main.java.configuration;

import jdk.jfr.MetadataDefinition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@MetadataDefinition
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
public @interface FilenameEnding {
    String[] endings();
}
