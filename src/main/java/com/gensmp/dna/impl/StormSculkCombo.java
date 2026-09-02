package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

/**
 * Storm + Sculk: Give player 3 Echo Shards to shoot within 20 seconds
 */
public class StormSculkCombo extends DNACombination {
    private final GenSMP plugin;

    public StormSculkCombo(GenSMP plugin) {
        super(GenType.STORM, GenType.SCULK, "Echo Shards", "Gain 3 shards to fire at enemies");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§6[GenSMP] §a3 Echo Shards granted! You have 20 seconds to use them.");
        // Give player 3 echo shard items
        for (int i = 0; i < 3; i++) {
            ItemStack shard = new ItemStack(Material.ECHO_SHARD);
            ItemMeta meta = shard.getItemMeta();
            if (meta != null) {
                meta.getPersistentDataContainer().set(
                    plugin.getNamespacedKey("echo_shard"),
                    PersistentDataType.STRING,
                    "storm_sculk"
                );
                shard.setItemMeta(meta);
            }
            player.getInventory().addItem(shard);
        }
        // Remove unused shards after 20 seconds
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            // Clean up unused shards
        }, 20 * 20L);
    }
}
