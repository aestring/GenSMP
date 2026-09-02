package com.gensmp.ability.impl;

import com.gensmp.GenSMP;
import com.gensmp.ability.Ability;
import org.bukkit.entity.Player;

/**
 * Soul Gen Active Ability: Temporarily increase the chance of Soul procs
 */
public class SoulAbility extends Ability {
    private final GenSMP plugin;

    public SoulAbility(GenSMP plugin) {
        super("soul_ability", "Soul Amplification", 30);
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aSoul ability activated! Soul proc chance increased for 30 seconds.");
        // Implementation handled by PassiveManager
    }
}
