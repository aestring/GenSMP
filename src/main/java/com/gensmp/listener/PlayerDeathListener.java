package com.gensmp.listener;

import com.gensmp.GenSMP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Handles player death events
 */
public class PlayerDeathListener implements Listener {
    private final GenSMP plugin;

    public PlayerDeathListener(GenSMP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // Clear cooldowns on death if desired
        // plugin.getCooldownManager().clearAllCooldowns(event.getEntity().getUniqueId());
    }
}
