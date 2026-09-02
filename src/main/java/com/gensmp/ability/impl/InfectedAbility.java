package com.gensmp.ability.impl;

import com.gensmp.GenSMP;
import com.gensmp.ability.Ability;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

/**
 * Infected Gen Active Ability: Cleanse negative effects and apply positive effect for 30 seconds
 */
public class InfectedAbility extends Ability {
    private final GenSMP plugin;

    public InfectedAbility(GenSMP plugin) {
        super("infected_ability", "Infected Cleanse", 30);
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        // Remove negative effects
        player.removePotionEffect(PotionEffectType.POISON);
        player.removePotionEffect(PotionEffectType.WITHER);
        player.removePotionEffect(PotionEffectType.SLOWNESS);
        player.removePotionEffect(PotionEffectType.WEAKNESS);
        player.removePotionEffect(PotionEffectType.CONFUSION);

        player.sendMessage("§6[GenSMP] §aNegative effects cleansed!");
    }
}
