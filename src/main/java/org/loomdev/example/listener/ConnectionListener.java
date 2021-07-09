package org.loomdev.example.listener;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.config.Configuration;
import org.loomdev.api.event.Subscribe;
import org.loomdev.api.event.entity.PlayerEvent;
import org.loomdev.example.util.ComponentUtils;

public class ConnectionListener {

    private final Component joinMessage;

    public ConnectionListener(@NotNull Configuration config) {
        this.joinMessage = ComponentUtils.parseComponent(config.getString("messages.join"));
    }

    @Subscribe
    public void onJoin(PlayerEvent.Join event) {
        event.getPlayer().sendMessage(joinMessage);
    }
}
