package com.gensmp.gen;

import com.gensmp.GenSMP;
import com.gensmp.util.ConfigManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages all Gens, their properties, and item representations
 */
public class GenManager {
    private final GenSMP plugin;
    private final Map<String, Gen> gens = new HashMap<>();

    public GenManager(GenSMP plugin) {
        this.plugin = plugin;
        initializeGens();
    }

    private void initializeGens() {
        for (GenType type : GenType.values()) {
            Gen gen = new Gen(type);
            gens.put(type.getId(), gen);
        }

        // Load descriptions from config if available
        loadDescriptions();
    }

    private void loadDescriptions() {
        ConfigManager config = plugin.getConfigManager();
        // Descriptions can be loaded from config.yml if needed
    }

    public Gen getGen(String id) {
        return gens.get(id.toLowerCase());
    }

    public Gen getGen(GenType type) {
        return gens.get(type.getId());
    }

    public List<Gen> getAllGens() {
        return new ArrayList<>(gens.values());
    }

    /**
     * Creates a Gen item with proper metadata for identification
     */
    public ItemStack createGenItem(GenType type) {
        Gen gen = getGen(type);
        if (gen == null) return null;

        ItemStack item = new ItemStack(type.getIcon());
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            // Set display name
            meta.setDisplayName("§6" + gen.getDisplayName() + " Gen");

            // Set lore
            List<String> lore = new ArrayList<>();
            lore.add("§7" + gen.getDescription());
            lore.add("");
            lore.add("§ePassive: §7" + gen.getPassiveDescription());
            lore.add("§eAbility: §7" + gen.getAbilityDescription());
            meta.setLore(lore);

            // Set PDC tag for persistent identification
            meta.getPersistentDataContainer().set(
                    plugin.getNamespacedKey("gen_id"),
                    PersistentDataType.STRING,
                    type.getId()
            );

            item.setItemMeta(meta);
        }

        return item;
    }

    /**
     * Checks if an item is a Gen item
     */
    public boolean isGenItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        return meta.getPersistentDataContainer().has(
                plugin.getNamespacedKey("gen_id"),
                PersistentDataType.STRING
        );
    }

    /**
     * Gets the Gen type from a Gen item
     */
    public GenType getGenTypeFromItem(ItemStack item) {
        if (!isGenItem(item)) return null;
        ItemMeta meta = item.getItemMeta();
        String genId = meta.getPersistentDataContainer().get(
                plugin.getNamespacedKey("gen_id"),
                PersistentDataType.STRING
        );
        return GenType.fromId(genId);
    }
}
