package com.gensmp.passive;

import com.gensmp.GenSMP;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;

/**
 * Manages passive Gen effects that apply automatically
 */
public class PassiveManager {
    private final GenSMP plugin;

    public PassiveManager(GenSMP plugin) {
        this.plugin = plugin;
    }

    /**
     * Applies passive effects for a player's active Gens
     * Called periodically (e.g., every 20 ticks)
     */
    public void applyPassiveEffects(Player player) {
        // Passive effects implementation
        // Each Gen has specific passive bonuses that apply automatically
    }

    /**
     * Gets the passive effect description for a Gen
     */
    public String getPassiveDescription(GenType type) {
        return switch (type) {
            case STRENGTH -> "Deal 10% more damage";
            case STORM -> "Gain 5% movement speed";
            case FIRE -> "Gain fire resistance in 10 block radius";
            case FROST -> "Move faster in snow";
            case WATER -> "Breathe underwater";
            case SCULK -> "See through walls in 20 block radius";
            case SOUL -> "Gain 5% chance on hit to spawn healing soul";
            case INFECTED -> "Resistance to poison and wither";
            case SIGHT -> "Glow reveals 3 nearest enemies";
            case MOUNTS -> "Tamed animals gain +1 armor";
        };
    }
}
