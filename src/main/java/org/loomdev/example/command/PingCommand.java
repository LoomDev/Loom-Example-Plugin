package org.loomdev.example.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(@NotNull CommandContext context) {
        context.getSource().ifPlayer(player -> {
            var message = Component.text("Pong!")
                    .color(TextColor.fromHexString("#7dfaa7"))
                    .hoverEvent(HoverEvent.showText(Component.text("Hi ⊂(◉‿◉)つ")));

            player.sendMessage(message);
        }).orElse(source -> {
            source.sendMessage("Oh, hi there; pong!");
        });
    }
}
