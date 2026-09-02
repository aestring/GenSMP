package com.gensmp.ability.impl;

import com.gensmp.GenSMP;
import com.gensmp.ability.Ability;
import org.bukkit.entity.Player;

/**
 * Sculk Gen Active Ability: Spawn a sculk shield that orbits around the player
 */
public class SculkAbility extends Ability {
    private final GenSMP plugin;

    public SculkAbility(GenSMP plugin) {
        super("sculk_ability", "Sculk Shield", 30);
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        // Create a visual effect around the player using particles
        // This will be implemented in a more advanced version with display entities
        player.sendMessage("§6[GenSMP] §aSculk shield activated!");
    }
}
