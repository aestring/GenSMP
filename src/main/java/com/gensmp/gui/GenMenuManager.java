package com.gensmp.gui;

import com.gensmp.GenSMP;
import org.bukkit.entity.Player;

/**
 * Manages GUI menus for player interactions
 */
public class GenMenuManager {
    private final GenSMP plugin;

    public GenMenuManager(GenSMP plugin) {
        this.plugin = plugin;
    }

    /**
     * Opens the main Gen selection menu for a player
     */
    public void openGenMenu(Player player) {
        // Implement GUI using inventory or adventure components
    }

    /**
     * Opens the DNA combination display menu
     */
    public void openDNAMenu(Player player) {
        // Display all possible DNA combinations
    }

    /**
     * Opens the mount selection menu for Mounts Gen ability
     */
    public void openMountMenu(Player player) {
        // List player's tamed animals for selection
    }
}
