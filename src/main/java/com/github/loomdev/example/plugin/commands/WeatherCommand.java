package com.github.loomdev.example.plugin.commands;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.util.Weather;

import java.util.Arrays;
import java.util.List;

public class WeatherCommand extends Command {

    public WeatherCommand() {
        super("Weather");
    }

    @Override
    public void execute(@NotNull CommandContext commandContext) {
        var source = commandContext.getSource();
        var args = commandContext.getArguments();

        source.ifPlayer(player -> {
            var weather = Weather.valueOf(args[0]);
            var ticks = args.length >= 2 ? Integer.parseInt(args[1]) : 0;
            var world = player.getWorld();

            if (ticks != 0) {
                world.setWeather(weather, ticks);
                player.sendMessage("Set weather to " + weather.name() + " for " + ticks + " ticks");
                return;
            }

            world.setWeather(weather);
            player.sendMessage("Set weather to " + weather.name());
        });
    }

    @Override
    public @NotNull List<String> suggest(@NotNull CommandContext context) {
        var options = Arrays.stream(Weather.values()).map(Enum::name).toArray(String[]::new);
        return ImmutableList.copyOf(options);
    }
}
