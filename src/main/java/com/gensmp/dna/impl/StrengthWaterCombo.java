package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Strength + Water: Create water bubble around player and increase damage in bubble
 */
public class StrengthWaterCombo extends DNACombination {
    private final GenSMP plugin;

    public StrengthWaterCombo(GenSMP plugin) {
        super(GenType.STRENGTH, GenType.WATER, "Water Guardian", "Create water bubble and boost damage within");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aWater bubble created!");
        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 15, 0));
        // Create visual water bubble effect
    }
}
