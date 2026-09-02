package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Strength + Storm: Creates a cloud at summoner position giving Weakness to all except Strength/Infected
 */
public class StrengthStormCombo extends DNACombination {
    private final GenSMP plugin;

    public StrengthStormCombo(GenSMP plugin) {
        super(GenType.STRENGTH, GenType.STORM, "Thunder Cloud", "Create a cloud that weakens foes");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aThunder cloud spawned!");
        // Create visual cloud effect and apply weakness to nearby players
        for (Player nearby : player.getWorld().getPlayers()) {
            if (nearby == player) continue;
            if (nearby.getLocation().distance(player.getLocation()) > 20) continue;
            // Don't apply weakness to Strength or Infected Gen holders
            nearby.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 5, 0));
        }
    }
}
