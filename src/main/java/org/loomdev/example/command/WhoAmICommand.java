package org.loomdev.example.command;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;

public class WhoAmICommand extends Command {

    public WhoAmICommand() {
        super("whoami");
    }

    @Override
    public void execute(@NotNull CommandContext context) {
        context.getSource().ifPlayer(player -> {
            player.sendMessage(Component.text("You're a player!"));
        }).ifConsole(console -> {
            console.sendMessage(Component.text("You're the console!"));
        }).orElse(other -> {
            other.sendMessage(Component.text("You're neither a player nor the console!"));
        });
    }
}
