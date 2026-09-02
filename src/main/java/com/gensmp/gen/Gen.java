package com.gensmp.gen;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a Gen with all its properties
 */
public class Gen {
    private final GenType type;
    private String description;
    private String passiveDescription;
    private String abilityDescription;
    private ItemStack icon;

    public Gen(GenType type) {
        this.type = type;
        this.description = "A " + type.getDisplayName() + " Gen";
        this.passiveDescription = "Passive effect";
        this.abilityDescription = "Active ability";
        this.icon = new ItemStack(type.getIcon());
    }

    public GenType getType() {
        return type;
    }

    public String getId() {
        return type.getId();
    }

    public String getDisplayName() {
        return type.getDisplayName();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassiveDescription() {
        return passiveDescription;
    }

    public void setPassiveDescription(String passiveDescription) {
        this.passiveDescription = passiveDescription;
    }

    public String getAbilityDescription() {
        return abilityDescription;
    }

    public void setAbilityDescription(String abilityDescription) {
        this.abilityDescription = abilityDescription;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }
}
