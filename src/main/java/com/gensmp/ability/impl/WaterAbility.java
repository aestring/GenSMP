package com.gensmp.ability.impl;

import com.gensmp.GenSMP;
import com.gensmp.ability.Ability;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Water Gen Active Ability: Give Water Breathing and Dolphin's Grace for 15 seconds at level 2
 */
public class WaterAbility extends Ability {
    private final GenSMP plugin;

    public WaterAbility(GenSMP plugin) {
        super("water_ability", "Aquatic Blessing", 30);
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        int durationTicks = 15 * 20; // 15 seconds

        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, durationTicks, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, durationTicks, 1));
        player.sendMessage("§6[GenSMP] §aWater abilities activated!");
    }
}
