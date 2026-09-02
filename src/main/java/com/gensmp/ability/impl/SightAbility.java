package com.gensmp.ability.impl;

import com.gensmp.GenSMP;
import com.gensmp.ability.Ability;
import org.bukkit.entity.Player;

/**
 * Sight Gen Active Ability: Reveal all nearby players' health for 10 seconds
 */
public class SightAbility extends Ability {
    private final GenSMP plugin;

    public SightAbility(GenSMP plugin) {
        super("sight_ability", "Health Reveal", 30);
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aPlayers' health revealed for 10 seconds!");
        // Implementation handled by listeners that apply glow to nearby players
    }
}
