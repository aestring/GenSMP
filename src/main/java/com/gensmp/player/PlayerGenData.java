package com.gensmp.player;

import com.gensmp.gen.GenType;

import java.util.UUID;

/**
 * Represents a player's Gen data
 */
public class PlayerGenData {
    private final UUID uuid;
    private GenType slot1;
    private GenType slot2;
    private long slot1UnlockTime;
    private long slot2UnlockTime;
    private boolean starterAssigned;
    private long lastAbilityUse;
    private long lastAbility2Use;
    private long lastDNAUse;

    public PlayerGenData(UUID uuid) {
        this.uuid = uuid;
        this.slot1 = null;
        this.slot2 = null;
        this.slot1UnlockTime = 0;
        this.slot2UnlockTime = 0;
        this.starterAssigned = false;
        this.lastAbilityUse = 0;
        this.lastAbility2Use = 0;
        this.lastDNAUse = 0;
    }

    public UUID getUUID() {
        return uuid;
    }

    public GenType getSlot1() {
        return slot1;
    }

    public void setSlot1(GenType type) {
        this.slot1 = type;
    }

    public GenType getSlot2() {
        return slot2;
    }

    public void setSlot2(GenType type) {
        this.slot2 = type;
    }

    public boolean hasGen(GenType type) {
        return slot1 == type || slot2 == type;
    }

    public int getActiveGenCount() {
        int count = 0;
        if (slot1 != null) count++;
        if (slot2 != null) count++;
        return count;
    }

    public boolean isFull() {
        return slot1 != null && slot2 != null;
    }

    public long getSlot1UnlockTime() {
        return slot1UnlockTime;
    }

    public void setSlot1UnlockTime(long unlockTime) {
        this.slot1UnlockTime = unlockTime;
    }

    public long getSlot2UnlockTime() {
        return slot2UnlockTime;
    }

    public void setSlot2UnlockTime(long unlockTime) {
        this.slot2UnlockTime = unlockTime;
    }

    public boolean isSlot1Unlocked() {
        if (slot1 == null) return false;
        return System.currentTimeMillis() >= slot1UnlockTime;
    }

    public boolean isSlot2Unlocked() {
        if (slot2 == null) return false;
        return System.currentTimeMillis() >= slot2UnlockTime;
    }

    public long getSlot1RemainingTime() {
        if (slot1 == null) return 0;
        long remaining = slot1UnlockTime - System.currentTimeMillis();
        return Math.max(0, remaining);
    }

    public long getSlot2RemainingTime() {
        if (slot2 == null) return 0;
        long remaining = slot2UnlockTime - System.currentTimeMillis();
        return Math.max(0, remaining);
    }

    public boolean isStarterAssigned() {
        return starterAssigned;
    }

    public void setStarterAssigned(boolean assigned) {
        this.starterAssigned = assigned;
    }

    public long getLastAbilityUse() {
        return lastAbilityUse;
    }

    public void setLastAbilityUse(long time) {
        this.lastAbilityUse = time;
    }

    public long getLastAbility2Use() {
        return lastAbility2Use;
    }

    public void setLastAbility2Use(long time) {
        this.lastAbility2Use = time;
    }

    public long getLastDNAUse() {
        return lastDNAUse;
    }

    public void setLastDNAUse(long time) {
        this.lastDNAUse = time;
    }

    /**
     * Withdraws the first Gen and moves the second to the first slot
     */
    public GenType withdraw() {
        GenType withdrawn = slot1;
        slot1 = slot2;
        slot2 = null;
        slot1UnlockTime = slot2UnlockTime;
        slot2UnlockTime = 0;
        return withdrawn;
    }
}
