package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Strength + Infected: Apply Weakness on hit, give Absorption when ability ends
 */
public class StrengthInfectedCombo extends DNACombination {
    private final GenSMP plugin;

    public StrengthInfectedCombo(GenSMP plugin) {
        super(GenType.STRENGTH, GenType.INFECTED, "Plague Strike", "Weaken enemies, grant self absorption");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aPlague strike activated!");
        // After 15 seconds, grant absorption
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 5, 0));
        }, 20 * 15L);
    }
}
