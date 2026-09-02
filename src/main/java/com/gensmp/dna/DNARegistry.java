package com.gensmp.dna;

import com.gensmp.GenSMP;
import com.gensmp.dna.impl.*;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for all 45 possible DNA combinations
 * Only explicitly specified combinations are implemented
 */
public class DNARegistry {
    private final GenSMP plugin;
    private final Map<String, DNACombination> combinations = new HashMap<>();

    public DNARegistry(GenSMP plugin) {
        this.plugin = plugin;
        registerCombinations();
    }

    private void registerCombinations() {
        // Strength combinations
        register(new StrengthStormCombo(plugin));
        register(new StrengthFireCombo(plugin));
        register(new StrengthFrostCombo(plugin));
        register(new StrengthWaterCombo(plugin));
        register(new StrengthSculkCombo(plugin));
        register(new StrengthSoulCombo(plugin));
        register(new StrengthInfectedCombo(plugin));
        register(new StrengthSightCombo(plugin));
        register(new StrengthMountsCombo(plugin));

        // Storm combinations
        register(new StormFireCombo(plugin));
        register(new StormFrostCombo(plugin));
        register(new StormWaterCombo(plugin));
        register(new StormSculkCombo(plugin));
        register(new StormSoulCombo(plugin));

        // Add remaining 30 combinations as unimplemented placeholders
    }

    private void register(DNACombination combo) {
        combinations.put(combo.getId(), combo);
    }

    /**
     * Gets a DNA combination by the two Gen types (order-independent)
     */
    public DNACombination getCombination(GenType gen1, GenType gen2) {
        // Try both orders
        String key1 = gen1.getId() + "_" + gen2.getId();
        String key2 = gen2.getId() + "_" + gen1.getId();

        DNACombination combo = combinations.get(key1);
        if (combo != null) return combo;
        return combinations.get(key2);
    }

    /**
     * Executes a DNA ability
     */
    public void executeDNA(GenType gen1, GenType gen2, Player player) {
        DNACombination combo = getCombination(gen1, gen2);
        if (combo != null) {
            combo.execute(player);
        } else {
            player.sendMessage("§6[GenSMP] §cThis DNA combination has no ability yet.");
        }
    }
}
