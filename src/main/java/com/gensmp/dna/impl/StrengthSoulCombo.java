package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Strength + Soul: 50% chance on hit for 15 seconds to summon soul and give Strength
 */
public class StrengthSoulCombo extends DNACombination {
    private final GenSMP plugin;

    public StrengthSoulCombo(GenSMP plugin) {
        super(GenType.STRENGTH, GenType.SOUL, "Soul Strike", "50% chance on hit to summon healing soul");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aSoul strike mode activated for 15 seconds!");
        // Combat listener will handle 50% proc chance
    }
}
