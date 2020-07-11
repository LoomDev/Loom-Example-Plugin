package com.github.loomdev.example.plugin;

import com.google.inject.internal.asm.$ByteVector;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.decoration.ArmorStand;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Subscribe;
import org.loomdev.api.event.block.BlockBrokenEvent;
import org.loomdev.api.event.block.entity.ArmorStandPlacedEvent;
import org.loomdev.api.event.player.connection.PlayerJoinedEvent;
import org.loomdev.api.plugin.LoomPlugin;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.plugin.annotation.PluginDirectory;
import org.loomdev.api.server.Server;

import javax.inject.Inject;
import java.nio.file.Path;

@LoomPlugin(
        id = "loom-example-plugin",
        name = "Example Plugin",
        description = "An example plugin for Loom",
        version = "V0.1",
        authors = { "me", "you", "some other person" }
)
public class ExamplePlugin implements Plugin {

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
        logger.info("Plugin load");
    }

    @Override
    public void onPluginEnable() {
        logger.info("Hello, enabling the plugin.");
        logger.info(this.server != null);
    }

    @Override
    public void onPluginDisable() {
        logger.info("Bye, disabling the plugin.");
    }

    @Subscribe
    public void onArmorStandPlaced(ArmorStandPlacedEvent event) {
        event.getPlayer().sendMessage(TextComponent.of("You placed and armor stand!").color(TextColor.fromHexString("#efefef")));

        ArmorStand armorStand = event.getArmorStand();
        armorStand.setArmsVisible(true);
        armorStand.setRightArmPose(ArmorStand.DEFAULT_RIGHT_ARM_ROTATION.add(-120, 0, 0));
    }

    @Subscribe
    public void onPlayerJoined(PlayerJoinedEvent event) {
        event.setJoinMessage(
                TextComponent.of("[")
                        .color(TextColor.fromHexString("#404040"))
                        .append(
                                TextComponent.of("+")
                                        .color(TextColor.fromHexString("#20d6a6"))
                        )
                        .append(
                                TextComponent.of("] ")
                                        .color(TextColor.fromHexString("#404040"))
                        )
                        .append(
                                event.getPlayer().getDisplayName()
                                        .color(TextColor.fromHexString("#FFDF00"))
                        )
        );
    }
}
