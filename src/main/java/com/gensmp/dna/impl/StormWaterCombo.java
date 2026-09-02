package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;

/**
 * Storm + Water: Create a tsunami in the direction player is looking, 5 blocks tall
 */
public class StormWaterCombo extends DNACombination {
    private final GenSMP plugin;

    public StormWaterCombo(GenSMP plugin) {
        super(GenType.STORM, GenType.WATER, "Tsunami", "Unleash a tidal wave of destruction");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aTsunami incoming!");
        // Create moving water wall effect
    }
}
