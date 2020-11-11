package com.github.loomdev.example.plugin;

import com.github.loomdev.example.plugin.command.ExampleCommand;
import com.github.loomdev.example.plugin.task.TabListTask;
import com.google.inject.Inject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.config.Configuration;
import org.loomdev.api.event.Subscribe;
import org.loomdev.api.event.player.connection.PlayerJoinEvent;
import org.loomdev.api.event.server.ServerPingEvent;
import org.loomdev.api.plugin.annotation.Config;
import org.loomdev.api.plugin.annotation.LoomPlugin;
import org.loomdev.api.plugin.hooks.Hook;
import org.loomdev.api.plugin.hooks.PluginDisableHook;
import org.loomdev.api.plugin.hooks.PluginEnableHook;
import org.loomdev.api.scheduler.ScheduledTask;
import org.loomdev.api.server.Server;
import org.loomdev.api.util.ChatColor;

import java.util.concurrent.TimeUnit;

@LoomPlugin(
    id = "loom-demo",
    name = "Demo",
    description = "A Loom API demo plugin.",
    version = "1.0-SNAPSHOT",
    authors = "Loom contributors",
    minimumApiVersion = ApiVersion.UNKNOWN
)
public class DemoPlugin {

    public static DemoPlugin instance;

    @Inject
    private Server server;

    @Inject
    private Logger logger;

    @Inject
    @Config(copyDefault = true, path = "config.yml")
    public Configuration config;

    private ScheduledTask tabListTask;

    @Inject
    public DemoPlugin() {
        instance = this;
    }

    @Hook
    public void onEnable(PluginEnableHook hook) {
        logger.info("Hey Hey, enabling the plugin.");

        // Register command
        server.getCommandManager().register(this, new ExampleCommand());

        // Start scheduled tasks
        tabListTask = ScheduledTask.builder()
                .interval(1)
                .execute(new TabListTask())
                .complete(this);

        logger.info("Scheduled task");
    }

    @Hook
    public void onDisable(PluginDisableHook hook) {
        tabListTask.cancelTask(true);

        logger.info("Bye, disabling the plugin.");
    }

    @Subscribe
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(Component.text(ChatColor.translate('&', config.getString("messages.join"))));
    }

    @Subscribe
    public void onServerPinged(ServerPingEvent event) {
        Component motd = Component.text()
                .append(Component.text("Loom")
                        .color(TextColor.fromHexString("#ffc130"))
                        .decoration(TextDecoration.BOLD, true))
                .append(Component.newline())
                .append(Component.text("Loom Demo Server!"))
                .build();

        event.setMotd(motd);
        event.setFavicon("data:image/png;base64,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");
    }
}
