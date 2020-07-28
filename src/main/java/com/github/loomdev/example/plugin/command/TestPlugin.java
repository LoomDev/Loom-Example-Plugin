package com.github.loomdev.example.plugin.command;

import com.github.loomdev.example.plugin.DemoPlugin;
import net.kyori.adventure.text.TextComponent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.ArmorStand;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.math.EulerAngle;
import org.loomdev.api.util.ChatColor;

import java.security.SecureRandom;
import java.util.Optional;

public class TestPlugin extends Command {

    private static BossBar bar;

    public TestPlugin() {
        super("test");

        setPermission("aaaa");
        setUsage("/asdf aa");
        setDescription("AAAAAAAAAAAAAAAAAAAAAAAAaaaa");
    }

    @Override
    public void execute(@NonNull CommandSource source, String[] args) {
        if (source instanceof Player) {
            Player player = (Player) source;

            // Spawn armor stand with some random rotations
            Optional<Entity> entityOptional = player.getWorld().spawnEntity(EntityType.ARMOR_STAND, player.getLocation());
            entityOptional.ifPresent(entity -> { // Check if the entity exists (aka if it was spawned correctly)
                ArmorStand armorStand = (ArmorStand) entity; // Cast the entity to ArmorStand so you can use armor stand specific methods
                armorStand.setRightArmPose(EulerAngle.of(10, 50, 69)); // Euler angles are how armor stand positions are described
                armorStand.setLeftArmPose(EulerAngle.of(10, 50, 69));  // Euler angles are pitch, roll, and yaw (airplane shit)
                armorStand.setBasePlateHidden(true);
                armorStand.setArmsVisible(true);
                armorStand.setHeadPose(EulerAngle.of(123, 23, 90));

                armorStand.setCustomName(TextComponent.of("Sebastian is a toaster").color(ChatColor.GOLD)); // Any entity can have custom names
                armorStand.setCustomNameVisible(true); // Setting the custom name as visible makes their name tag display
            });
        }
    }

    /*private static final SecureRandom random = new SecureRandom();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }*/
}
