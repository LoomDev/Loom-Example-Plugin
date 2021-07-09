package org.loomdev.example.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class ComponentUtils {

    private static final GsonComponentSerializer SERIALIZER = GsonComponentSerializer.gson();

    @NotNull
    public static Component parseComponent(@NotNull String component) {
        return SERIALIZER.deserialize(component);
    }
}
