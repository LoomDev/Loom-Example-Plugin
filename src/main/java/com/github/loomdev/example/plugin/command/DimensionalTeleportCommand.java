package com.github.loomdev.example.plugin.command;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.util.ChatColor;
import org.loomdev.api.util.NamespacedKey;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DimensionalTeleportCommand extends Command {

    public DimensionalTeleportCommand() {
        super("dimtp");
    }

    @Override
    public void execute(@NotNull CommandContext context) {
        var arguments = context.getArguments();

        /*if (arguments.length < 1) {
            context.getSource().sendMessage(Component.text("Specify a dimension.").color(ChatColor.RED));
            return;
        }*/

        var level = Loom.getServer().getWorldManager().getWorld(NamespacedKey.of("minecraft:the_end"));
        level.ifPresent(dimension -> {
            ((Player) context.getSource()).teleport(dimension.getBlock(0, 90, 0).getLocation());
            context.getSource().sendMessage("Teleported!");
        });
    }

    @Override
    @NotNull
    public List<String> suggest(@NotNull CommandContext context) {
        var arguments = context.getArguments();

        if (arguments.length == 1) {
            return Loom.getServer().getWorldManager().getWorlds().stream()
                    .map(world -> world.getDimension().toString())
                    .filter(key -> key.toLowerCase().contains(arguments[0].toLowerCase()))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
