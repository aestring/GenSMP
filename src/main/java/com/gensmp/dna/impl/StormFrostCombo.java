package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;

/**
 * Storm + Frost: Create frozen dripstone falling from 25 blocks high, 5 total
 */
public class StormFrostCombo extends DNACombination {
    private final GenSMP plugin;

    public StormFrostCombo(GenSMP plugin) {
        super(GenType.STORM, GenType.FROST, "Icy Downpour", "Drop frozen dripstone from the sky");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aIcy downpour falling from the sky!");
        // Spawn 5 falling frozen dripstone projectiles
    }
}
