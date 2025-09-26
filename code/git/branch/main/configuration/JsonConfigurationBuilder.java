package de.xcuzimsmart.pluginhelper.code.git.branch.main.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonConfigurationBuilder {

    public static Gson build(JsonProperties properties) {
        final GsonBuilder builder = new GsonBuilder();

        if (!properties.innerClazzSerialisation()) builder.disableInnerClassSerialization();
        if (!properties.htmlEscaping()) builder.disableHtmlEscaping();
        if (properties.prettyPrinting()) builder.setPrettyPrinting();

        return builder.create();
    }
}
