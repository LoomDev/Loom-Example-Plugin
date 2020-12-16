package com.github.loomdev.example.plugin.command;

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
        var source = context.getSource();

        source.ifPlayer(player -> {
            player.sendMessage(Component.text("You're a player!"));
        }).ifConsole(console -> {
            console.sendMessage(Component.text("You're the console!"));
        }).orElse(other -> {
            other.sendMessage(Component.text("You're neither the player nor the console!"));
        });
    }
}
