package com.gensmp.ability.impl;

import com.gensmp.GenSMP;
import com.gensmp.ability.Ability;
import org.bukkit.entity.Player;

/**
 * Mounts Gen Active Ability: Open GUI to teleport one of player's tamed animals to them
 */
public class MountsAbility extends Ability {
    private final GenSMP plugin;

    public MountsAbility(GenSMP plugin) {
        super("mounts_ability", "Mount Teleport", 30);
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aOpening tamed animals menu...");
        // Implementation handled by GUI system
    }
}
