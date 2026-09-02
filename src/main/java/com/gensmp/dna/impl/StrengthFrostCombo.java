package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Strength + Frost: Apply freeze effect to hit players for 15 seconds
 */
public class StrengthFrostCombo extends DNACombination {
    private final GenSMP plugin;

    public StrengthFrostCombo(GenSMP plugin) {
        super(GenType.STRENGTH, GenType.FROST, "Frozen Strikes", "Freeze enemies with each hit for 15 seconds");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aFrozen strikes activated for 15 seconds!");
        // Combat listener will apply freeze to hit targets
    }
}
