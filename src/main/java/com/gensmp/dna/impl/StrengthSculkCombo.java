package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;

/**
 * Strength + Sculk: Create a Sonic Boom in the direction player is looking
 */
public class StrengthSculkCombo extends DNACombination {
    private final GenSMP plugin;

    public StrengthSculkCombo(GenSMP plugin) {
        super(GenType.STRENGTH, GenType.SCULK, "Sonic Boom", "Release a destructive sonic wave");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aSonic boom released!");
        // Warden-style attack in player's looking direction
    }
}
