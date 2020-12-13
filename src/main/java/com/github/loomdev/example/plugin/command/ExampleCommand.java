package com.github.loomdev.example.plugin.command;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.command.CommandSource;

public class ExampleCommand extends Command {

    public ExampleCommand() {
        super("example", "example-alias", "ex");

        setDescription("This is an example command");
        setUsage("/example");
    }

    @Override
    public void execute(@NotNull CommandContext context) {
        if (context.getAlias().equals("example-alias")) {
            context.getSource().sendMessage("You used an alias for this command- stellar!");
        } else {
            context.getSource().sendMessage("You used the Example command... Good job!");
        }
    }
}
