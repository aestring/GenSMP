package com.gensmp.listener;

import com.gensmp.GenSMP;
import com.gensmp.gen.GenType;
import com.gensmp.player.PlayerGenData;
import com.gensmp.util.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Handles Gen consumption events - triggered by right-clicking a Gen item
 */
public class GenConsumeListener implements Listener {
    private final GenSMP plugin;

    public GenConsumeListener(GenSMP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onGenConsume(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;

        if (!plugin.getGenManager().isGenItem(item)) return;

        Player player = event.getPlayer();
        GenType genType = plugin.getGenManager().getGenTypeFromItem(item);
        if (genType == null) return;

        event.setCancelled(true);

        PlayerGenData data = plugin.getPlayerDataManager().getPlayerData(player);

        // Check if player already has 2 gens
        if (data.isFull()) {
            player.sendMessage(Messages.GEN_ALREADY_HAVE_TWO);
            return;
        }

        // Add gen to empty slot
        if (data.getSlot1() == null) {
            data.setSlot1(genType);
            data.setSlot1UnlockTime(System.currentTimeMillis() + 600000); // 10 minutes
        } else {
            data.setSlot2(genType);
            data.setSlot2UnlockTime(System.currentTimeMillis() + 600000);
        }

        plugin.getPlayerDataManager().savePlayerData(data);
        player.sendMessage(String.format(Messages.GEN_OBTAINED, genType.getDisplayName()));
        
        // Remove the item from player's inventory
        if (item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        } else {
            player.getInventory().removeItem(item);
        }
    }
}
