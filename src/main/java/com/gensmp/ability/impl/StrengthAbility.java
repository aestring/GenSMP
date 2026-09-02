package com.gensmp.ability.impl;

import com.gensmp.GenSMP;
import com.gensmp.ability.Ability;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Strength Gen Active Ability: Increase damage by 10% and stun shields for 5 seconds
 */
public class StrengthAbility extends Ability {
    private final GenSMP plugin;

    public StrengthAbility(GenSMP plugin) {
        super("strength_ability", "Strength Boost", 30);
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aStrength ability activated!");
        // The actual damage boost is handled by the CombatListener
        // Give player a temporary tag that combat listener checks
    }
}
