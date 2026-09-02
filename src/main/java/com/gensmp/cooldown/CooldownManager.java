package com.gensmp.cooldown;

import java.util.*;

/**
 * Manages cooldowns for abilities and DNA abilities
 */
public class CooldownManager {
    private final Map<String, Long> cooldowns = new HashMap<>();

    /**
     * Checks if a cooldown is active
     */
    public boolean isOnCooldown(UUID uuid, String abilityId) {
        String key = uuid + ":" + abilityId;
        Long cooldownEnd = cooldowns.get(key);

        if (cooldownEnd == null) {
            return false;
        }

        if (System.currentTimeMillis() >= cooldownEnd) {
            cooldowns.remove(key);
            return false;
        }

        return true;
    }

    /**
     * Gets remaining cooldown in milliseconds
     */
    public long getRemainingCooldown(UUID uuid, String abilityId) {
        String key = uuid + ":" + abilityId;
        Long cooldownEnd = cooldowns.get(key);

        if (cooldownEnd == null) {
            return 0;
        }

        long remaining = cooldownEnd - System.currentTimeMillis();
        return Math.max(0, remaining);
    }

    /**
     * Gets remaining cooldown in seconds
     */
    public long getRemainingCooldownSeconds(UUID uuid, String abilityId) {
        return getRemainingCooldown(uuid, abilityId) / 1000;
    }

    /**
     * Sets a cooldown
     */
    public void setCooldown(UUID uuid, String abilityId, long durationSeconds) {
        String key = uuid + ":" + abilityId;
        cooldowns.put(key, System.currentTimeMillis() + (durationSeconds * 1000));
    }

    /**
     * Clears a cooldown
     */
    public void clearCooldown(UUID uuid, String abilityId) {
        String key = uuid + ":" + abilityId;
        cooldowns.remove(key);
    }

    /**
     * Clears all cooldowns for a player
     */
    public void clearAllCooldowns(UUID uuid) {
        cooldowns.entrySet().removeIf(entry -> entry.getKey().startsWith(uuid + ":"));
    }
}
