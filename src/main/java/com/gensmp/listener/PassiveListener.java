package com.gensmp.listener;

import com.gensmp.GenSMP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Handles passive Gen effects
 */
public class PassiveListener implements Listener {
    private final GenSMP plugin;

    public PassiveListener(GenSMP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Passive effects are applied through other means
        // This listener can be expanded for passive event triggers
    }
}
