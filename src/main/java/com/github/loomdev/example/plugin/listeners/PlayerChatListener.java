package com.github.loomdev.example.plugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.loomdev.api.event.Subscribe;
import org.loomdev.api.event.entity.PlayerEvent;

public class PlayerChatListener {

    private final PlayerEvent.Chat.Format chatFormat;

    public PlayerChatListener() {
        chatFormat = (playerName, message) -> {
            var formattedMessage = Component.text();

            playerName = playerName
                    .color(NamedTextColor.GOLD)
                    .hoverEvent(HoverEvent.showText(Component.text("An awesome person (づ｡◕‿‿◕｡)づ")));

            formattedMessage.append(playerName);
            formattedMessage.append(Component.text(": ").color(NamedTextColor.GRAY));
            formattedMessage.append(message.color(NamedTextColor.GRAY));

            return formattedMessage.build();
        };
    }

    @Subscribe
    public void onPlayerChat(PlayerEvent.Chat event) {
        event.setFormat(chatFormat);
    }

}
