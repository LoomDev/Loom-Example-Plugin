package com.github.loomdev.example.plugin.task;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.loomdev.api.Loom;
import org.loomdev.api.scheduler.ScheduledTaskRunnable;

import java.awt.*;

public class TabListTask extends ScheduledTaskRunnable {

    private static int hue = 0;

    @Override
    public void run() {
        hue += 0.01;

        if (hue > 1) {
            hue = 0;
        }

        Color color = Color.getHSBColor(hue, 1, 1);
        TextColor textColor = TextColor.color(color.getRed(), color.getGreen(), color.getBlue());

        TextComponent header = Component.empty()
                .append(Component.text(" "))
                .append(Component.newline())
                .append(Component.text("-  Loom  -").color(textColor).decoration(TextDecoration.BOLD, true))
                .append(Component.newline())
                .append(Component.text("Demo Server").color(TextColor.fromHexString("#AAAAAA")).decoration(TextDecoration.ITALIC, true))
                .append(Component.newline())
                .append(Component.text(" "));

        TextComponent footer = Component.empty()
                .append(Component.text(" ")).append(Component.newline())
                .append(Component.text("The next-generation Minecraft server software")).append(Component.newline())
                .append(Component.text("with a modern and extensive plugin API.")).append(Component.newline())
                .append(Component.text("loomdev.org").color(TextColor.fromHexString("#ffd6ab"))).append(Component.newline());

        Loom.getServer().getPlayerManager().getOnlinePlayers().forEach(player -> {
            player.setTabListHeader(header);
            player.setTabListFooter(footer);
        });
    }
}
