package com.github.loomdev.example.plugin.listeners;

import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.config.Configuration;
import org.loomdev.api.event.Subscribe;
import org.loomdev.api.event.entity.PlayerEvent;

public class PlayerJoinListener {

    private final Configuration config;
    private final BossBar bossBar;

    public PlayerJoinListener(Configuration config) {
        this.config = config;
        this.bossBar = BossBar.builder()
                .text("Welcome")
                .color(BossBar.Color.PINK)
                .percent(100)
                .build();
    }

    @Subscribe
    public void onPlayerJoin(PlayerEvent.Join event) {
        var welcomeMessage = this.config.getString("messages.join");
        event.getPlayer().sendMessage(welcomeMessage);

        this.bossBar.addPlayer(event.getPlayer());
    }
}
