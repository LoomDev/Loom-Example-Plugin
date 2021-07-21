package org.loomdev.example;

import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.config.Configuration;
import org.loomdev.api.plugin.Config;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.lifecycle.LifecycleEvent;
import org.loomdev.api.plugin.lifecycle.LifecycleHook;
import org.loomdev.api.server.Server;
import org.loomdev.example.command.NameItemCommand;
import org.loomdev.example.command.PingCommand;
import org.loomdev.example.command.WhoAmICommand;
import org.loomdev.example.command.weather.PlayerWeatherCommand;
import org.loomdev.example.listener.ConnectionListener;

@Plugin(
        id = "loom-example",
        name = "Loom Example",
        description = "A basic Loom API example plugin.",
        version = "1.0-SNAPSHOT",
        authors = "The Loom development team"
)
public class LoomExample {

    @Inject
    public Server server;

    @Inject
    public Logger logger;

    @Inject
    @Config
    private Configuration config;

    @LifecycleHook
    public void onPluginEnable(LifecycleEvent.Enable event) {
        var apiVersion = server.getApiVersion();
        logger.info("Enabling under Loom {} ({}).", apiVersion.getName(), apiVersion.getReleaseType());

        var commandManager = server.getCommandManager();
        commandManager.registerCommand(new NameItemCommand());
        commandManager.registerCommand(new PingCommand());
        commandManager.registerCommand(new PlayerWeatherCommand());
        commandManager.registerCommand(new WhoAmICommand());

        var eventManager = server.getEventManager();
        eventManager.registerListener(new ConnectionListener(config));
    }
}
