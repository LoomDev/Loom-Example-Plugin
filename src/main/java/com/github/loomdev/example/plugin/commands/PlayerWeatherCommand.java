package com.github.loomdev.example.plugin.commands;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.util.Weather;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerWeatherCommand extends Command {

    public PlayerWeatherCommand() {
        super("pweather");
    }

    @Override
    public void execute(@NotNull CommandContext commandContext) {
        var source = commandContext.getSource();
        var args = commandContext.getArguments();

        source.ifPlayer(player -> {
            if (args.length == 0) {
                var weatherOpt = player.getWeather();
                if (weatherOpt.isEmpty()) {
                    player.sendMessage("No player weather");
                    return;
                }

                player.sendMessage("Player weather: " + weatherOpt.map(Enum::name).get());
                return;
            }

            if (args[0].equalsIgnoreCase("RESET")) {
                player.resetWeather();
                player.sendMessage("Player weather reset.");
                return;
            }

            var weather = Weather.valueOf(args[0]);
            player.setWeather(weather);
            player.sendMessage("Player weather set to " + weather.name());
        });
    }

    @Override
    public @NotNull List<String> suggest(@NotNull CommandContext context) {
        var options = Arrays.stream(Weather.values()).map(Enum::name).collect(Collectors.toList());
        options.add("RESET");
        return ImmutableList.copyOf(options);
    }
}
