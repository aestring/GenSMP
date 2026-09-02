package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;

/**
 * Strength + Sight: Give 50% of nearby players Glowing, deal bonus damage to them
 */
public class StrengthSightCombo extends DNACombination {
    private final GenSMP plugin;

    public StrengthSightCombo(GenSMP plugin) {
        super(GenType.STRENGTH, GenType.SIGHT, "Marked Target", "Glow 50% of nearby players for bonus damage");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aTargets marked for death!");
        // Mark nearby players with glow
    }
}
