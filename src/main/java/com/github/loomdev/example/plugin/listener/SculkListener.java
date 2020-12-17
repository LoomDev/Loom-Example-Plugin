package com.github.loomdev.example.plugin.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.loomdev.api.Loom;
import org.loomdev.api.event.EventOrder;
import org.loomdev.api.event.Subscribe;
import org.loomdev.api.event.block.SculkSensorEvent;

public class SculkListener {

    @Subscribe(ignoreCancelled = true, order = EventOrder.EARLIEST)
    public void onActivate(SculkSensorEvent.Activate event) {
        var type = event.getVibrationType().name().replace("_", " ").toLowerCase();
        var component = Component.text("shh.. i sensed the vibration of " + type).color(TextColor.color(173, 190, 219));
        Loom.getPlayerManager().broadcastMessage(component);
    }
}
