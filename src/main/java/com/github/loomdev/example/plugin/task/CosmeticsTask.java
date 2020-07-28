package com.github.loomdev.example.plugin.task;

import org.loomdev.api.Loom;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.scheduler.TaskRunnable;
import org.loomdev.api.util.ChatColor;

public class CosmeticsTask extends TaskRunnable {

    BossBar bar;
    int state = 0;

    public CosmeticsTask() {
        bar = BossBar.builder()
                .text(ChatColor.translate('&', "&f&lLoom Demo Server - loomdev.org"))
                .percent(0)
                .color(BossBar.Color.WHITE)
                .style(BossBar.Style.PROGRESS)
                .build();
    }

    @Override
    public void run() {
        if (state == 0) {
            bar.setColor(BossBar.Color.GREEN);
            state = 1;
        } else {
            bar.setColor(BossBar.Color.WHITE);
            state = 0;
        }

        Loom.getServer().getOnlinePlayers().forEach(player -> bar.addPlayer(player));
    }
}
