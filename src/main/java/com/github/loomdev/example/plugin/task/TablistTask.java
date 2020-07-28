package com.github.loomdev.example.plugin.task;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.loomdev.api.Loom;
import org.loomdev.api.particle.Particle;
import org.loomdev.api.particle.data.DustData;
import org.loomdev.api.scheduler.TaskRunnable;
import org.loomdev.api.sound.Sound;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class TablistTask extends TaskRunnable {

    private Particle particle;
    private static int colorIndex = 0;

    @Override
    public void run() {
        if (colorIndex < 1) {
            colorIndex += 0.01;
            Color color = Color.getHSBColor(colorIndex, 1, 1);
            TextComponent header = TextComponent.builder()
                    .append(TextComponent.of(" ")).append(TextComponent.newline())
                    .append(TextComponent.of("-  Loom  -").color(TextColor.of(color.getRed(), color.getGreen(), color.getBlue())).decoration(TextDecoration.BOLD, true))
                    .append(TextComponent.newline()).append(TextComponent.of("Demo Server").color(TextColor.fromHexString("#AAAAAA")).decoration(TextDecoration.ITALIC, true))
                    .append(TextComponent.newline()).append(TextComponent.of(" ")).build();

            TextComponent footer = TextComponent.of(" ").append(TextComponent.newline())
                    .append(TextComponent.of("The next-generation Minecraft server software")).append(TextComponent.newline())
                    .append(TextComponent.of("with a modern and extensive plugin API.")).append(TextComponent.newline())
                    .append(TextComponent.of("loomdev.org").color(TextColor.fromHexString("#ffd6ab"))).append(TextComponent.newline());

            Loom.getServer().getOnlinePlayers().forEach(player -> {
                player.setTabListHeader(header);
                player.setTabListFooter(footer);

                particle = Particle.builder(Particle.Type.DUST)
                        .data(new DustData(org.loomdev.api.util.Color.fromRgb(150, 11, 90), 2))
                        .amount(3)
                        .build();
                player.getWorld().spawnParticle(particle, player.getLocation());

                Sound sound = Sound.builder(Sound.Type.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE)
                        .volume((float) ThreadLocalRandom.current().nextDouble(0, 1))
                        .pitch((float) ThreadLocalRandom.current().nextDouble(0, 1))
                        .build();
                player.playSound(sound, player.getLocation());
            });
        } else {
            colorIndex = 0;
        }
    }
}
