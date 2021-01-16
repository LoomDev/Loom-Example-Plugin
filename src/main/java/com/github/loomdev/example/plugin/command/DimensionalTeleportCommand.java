package com.github.loomdev.example.plugin.command;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.sound.SoundEvent;
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
        context.getSource().ifPlayer(player -> {
            var arguments = context.getArguments();

            if (arguments.length < 1) {
                context.getSource().sendMessage(Component.text("Specify a dimension.", ChatColor.RED));
                return;
            }

            Loom.getServer().getWorldManager().getWorld(NamespacedKey.of(arguments[0])).ifPresent(dimension -> {
                player.teleport(dimension.getBlock(0, 90, 0).getLocation());
                player.sendMessage("Teleported!");
                player.playSound(Sound.builder(SoundEvent.BLOCK_ENDER_CHEST_OPEN).build());
            });
        }).orElse(source -> {
            source.sendMessage(Component.text("This command is only usable by players.", ChatColor.RED));
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
