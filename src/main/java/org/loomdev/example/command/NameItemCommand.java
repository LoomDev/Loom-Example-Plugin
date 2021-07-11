package org.loomdev.example.command;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandContext;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.util.ChatColor;
import org.loomdev.api.util.EquipmentSlot;

/**
 * This command displays an example of our {@link ItemProperty}
 * system for manipulating item metadata.
 */
public class NameItemCommand extends Command {

    public NameItemCommand() {
        super("nameitem");
    }

    @Override
    public void execute(@NotNull CommandContext context) {
        var arguments = context.getArguments();

        context.getSource().ifPlayer(player -> {
            // There's always at least AIR being held
            var heldItem = player.getEquipment(EquipmentSlot.MAIN_HAND).orElseThrow();

            if (heldItem.isEmpty()) {
                player.sendMessage("Hold an item in your main hand to rename it.");
                return;
            }

            if (arguments.length < 1) {
                player.sendMessage("Specify a new name for the item you're holding.");
                return;
            }

            var translatedName = ChatColor.translate('&', arguments[0]);
            var data = heldItem.getProperty(ItemProperty.Name);

            data.setName(translatedName);
            heldItem.setProperty(ItemProperty.Name, data);
            player.sendMessage("Named item!");
        }).orElse(source -> {
            source.sendMessage("This command may only be used by players.");
        });
    }
}
