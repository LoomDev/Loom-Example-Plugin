package org.loomdev.example.command.weather;

import com.google.common.base.Enums;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
    public void execute(@NotNull CommandContext context) {
        var args = context.getArguments();

        context.getSource().ifPlayer(player -> {
            if (args.length == 0) {
                player.getWeather().ifPresentOrElse(weather -> {
                    player.sendMessage("You are currently viewing \"" + weather.name().toLowerCase() + "\" weather.");
                }, () -> {
                    player.sendMessage("Your weather is currently in sync with the server.");
                });

                return;
            }

            if (args[0].equalsIgnoreCase("reset")) {
                player.resetWeather();
                player.sendMessage("Your weather is now in sync with the server.");
                return;
            }

            var selectedWeather = Enums.getIfPresent(Weather.class, args[0].toUpperCase()).toJavaUtil();

            if (selectedWeather.isEmpty()) {
                player.sendMessage(Component.text("Selected an invalid weather type.", NamedTextColor.RED));
                return;
            }

            player.setWeather(selectedWeather.get());
            player.sendMessage("You're now viewing \"" + selectedWeather.get().name().toLowerCase() + "\" weather!");
        }).orElse(source -> {
            source.sendMessage("Player Weather is only applicable to players.");
        });
    }

    @Override
    @NotNull
    public List<String> suggest(@NotNull CommandContext context) {
        return Arrays.stream(Weather.values())
                .map(Enum::name)
                .map(String::toLowerCase)
                .sorted()
                .collect(Collectors.toList());
    }
}
