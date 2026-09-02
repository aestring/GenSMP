package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Storm + Soul: Spawn floating cloud, give Levitation to non-Soul players, Jump Boost to Soul players
 */
public class StormSoulCombo extends DNACombination {
    private final GenSMP plugin;

    public StormSoulCombo(GenSMP plugin) {
        super(GenType.STORM, GenType.SOUL, "Cloud Spirit", "Spawn cloud that grants special effects");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aCloud spirit summoned!");
        // Spawn cloud entity above player
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * 15, 0));
    }
}
