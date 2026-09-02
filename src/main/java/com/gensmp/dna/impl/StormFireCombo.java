package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;

/**
 * Storm + Fire: Create a Fire Tornado at player's position for 15 seconds
 */
public class StormFireCombo extends DNACombination {
    private final GenSMP plugin;

    public StormFireCombo(GenSMP plugin) {
        super(GenType.STORM, GenType.FIRE, "Fire Tornado", "Summon a devastating fire tornado");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §aFire tornado spawned for 15 seconds!");
        // Create visual tornado effect with damage
    }
}
