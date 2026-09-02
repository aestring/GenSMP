package com.gensmp.ability;

import org.bukkit.entity.Player;

/**
 * Abstract base class for all Gen abilities
 */
public abstract class Ability {
    protected final String id;
    protected final String name;
    protected final int cooldownSeconds;

    public Ability(String id, String name, int cooldownSeconds) {
        this.id = id;
        this.name = name;
        this.cooldownSeconds = cooldownSeconds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCooldownSeconds() {
        return cooldownSeconds;
    }

    /**
     * Executes the ability for a player
     */
    public abstract void execute(Player player);
}
