package com.github.loomdev.example.plugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(@NotNull CommandContext commandContext) {
        commandContext.getSource()
            .ifConsole(console -> {
                console.sendMessage("Pong ;-)");
            })
            .ifPlayer(player -> {
                var message = Component.text("Pong")
                        .color(NamedTextColor.GOLD)
                        .hoverEvent(HoverEvent.showText(Component.text("Hi ⊂(◉‿◉)つ")));

                player.sendMessage(message);
            });
    }
}
