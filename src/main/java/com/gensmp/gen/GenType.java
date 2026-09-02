package com.gensmp.gen;

import org.bukkit.Material;

/**
 * Enumeration of all 10 Gen types
 */
public enum GenType {
    STRENGTH("strength", "Strength", Material.GOLDEN_APPLE),
    STORM("storm", "Storm", Material.LIGHTNING_ROD),
    FIRE("fire", "Fire", Material.CAMPFIRE),
    FROST("frost", "Frost", Material.POWDER_SNOW),
    WATER("water", "Water", Material.HEART_OF_THE_SEA),
    SCULK("sculk", "Sculk", Material.SCULK_CATALYST),
    SOUL("soul", "Soul", Material.SOUL_CAMPFIRE),
    INFECTED("infected", "Infected", Material.ROTTEN_FLESH),
    SIGHT("sight", "Sight", Material.TINTED_GLASS),
    MOUNTS("mounts", "Mounts", Material.SADDLE);

    private final String id;
    private final String displayName;
    private final Material icon;

    GenType(String id, String displayName, Material icon) {
        this.id = id;
        this.displayName = displayName;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Material getIcon() {
        return icon;
    }

    public static GenType fromId(String id) {
        for (GenType type : values()) {
            if (type.id.equalsIgnoreCase(id)) {
                return type;
            }
        }
        return null;
    }

    public static GenType fromDisplayName(String displayName) {
        for (GenType type : values()) {
            if (type.displayName.equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        return null;
    }
}
