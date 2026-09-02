package com.gensmp.dna;

import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;

/**
 * Abstract base class for DNA combination abilities
 */
public abstract class DNACombination {
    protected final GenType firstGen;
    protected final GenType secondGen;
    protected final String id;
    protected final String displayName;
    protected final String description;
    protected final int cooldownSeconds;

    public DNACombination(GenType firstGen, GenType secondGen, String displayName, String description) {
        this.firstGen = firstGen;
        this.secondGen = secondGen;
        this.id = firstGen.getId() + "_" + secondGen.getId();
        this.displayName = displayName;
        this.description = description;
        this.cooldownSeconds = 90;
    }

    public GenType getFirstGen() {
        return firstGen;
    }

    public GenType getSecondGen() {
        return secondGen;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public int getCooldownSeconds() {
        return cooldownSeconds;
    }

    /**
     * Executes the DNA ability for a player
     */
    public abstract void execute(Player player);

    /**
     * Checks if this combination matches the given pair (order-independent)
     */
    public boolean matches(GenType gen1, GenType gen2) {
        return (firstGen == gen1 && secondGen == gen2) || (firstGen == gen2 && secondGen == gen1);
    }
}
