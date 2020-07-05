package com.github.loomdev.example.plugin;

import org.apache.logging.log4j.Logger;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.plugin.annotation.PluginDirectory;
import org.loomdev.api.server.Server;

import javax.inject.Inject;
import java.nio.file.Path;

@Plugin(
        id = "loom-example-plugin",
        name = "Example Plugin",
        description = "An example plugin for Loom",
        version = "V0.1",
        authors = {
                "me",
                "you",
                "some other person"
        }
)
public class ExamplePlugin {

    @Inject
    private Server server;

    @Inject
    private PluginManager pluginManager;

    @Inject
    @PluginDirectory
    private Path pluginFolder;

    @Inject
    private Logger logger;

    @Inject
    public ExamplePlugin(Logger logger) {
        logger.info("Hello from the plugin");
        logger.info(this.server != null);
    }

}
