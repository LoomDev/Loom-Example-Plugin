package com.github.loomdev.example.plugin;

import com.github.loomdev.example.plugin.commands.PingCommand;
import com.github.loomdev.example.plugin.commands.PlayerWeatherCommand;
import com.github.loomdev.example.plugin.commands.WeatherCommand;
import com.github.loomdev.example.plugin.commands.WhoAmICommand;
import com.github.loomdev.example.plugin.listeners.PlayerChatListener;
import com.github.loomdev.example.plugin.listeners.PlayerJoinListener;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.config.Configuration;
import org.loomdev.api.plugin.Config;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.lifecycle.LifecycleEvent;
import org.loomdev.api.plugin.lifecycle.LifecycleHook;
import org.loomdev.api.server.Server;

@Plugin(
        id = "example-plugin",
        name = "Example Plugin",
        description = "A basic example plugin",
        version = "0.1.0",
        authors = "The loom team"
)
public class ExamplePlugin {

    @Inject
    public Logger logger;

    @Inject
    public Server server;

    @Inject @Config
    private Configuration configuration;

    @LifecycleHook
    public void onEnable(LifecycleEvent.Enable event) {
        var commandManager = server.getCommandManager();
        commandManager.registerCommand(new PingCommand());
        commandManager.registerCommand(new WhoAmICommand());
        commandManager.registerCommand(new WeatherCommand());
        commandManager.registerCommand(new PlayerWeatherCommand());

        var eventManager = server.getEventManager();
        eventManager.registerListener(new PlayerChatListener());
        eventManager.registerListener(new PlayerJoinListener(configuration));
    }

    @LifecycleHook
    public void onDisable(LifecycleEvent.Disable event) {
        logger.info("Bye bye :wave:");
    }

}
