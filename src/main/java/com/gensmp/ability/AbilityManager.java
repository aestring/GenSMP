package com.gensmp.ability;

import com.gensmp.GenSMP;
import com.gensmp.ability.impl.*;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages all individual Gen abilities
 */
public class AbilityManager {
    private final GenSMP plugin;
    private final Map<GenType, Ability> abilities = new HashMap<>();

    public AbilityManager(GenSMP plugin) {
        this.plugin = plugin;
        registerAbilities();
    }

    private void registerAbilities() {
        abilities.put(GenType.STRENGTH, new StrengthAbility(plugin));
        abilities.put(GenType.STORM, new StormAbility(plugin));
        abilities.put(GenType.FIRE, new FireAbility(plugin));
        abilities.put(GenType.FROST, new FrostAbility(plugin));
        abilities.put(GenType.WATER, new WaterAbility(plugin));
        abilities.put(GenType.SCULK, new SculkAbility(plugin));
        abilities.put(GenType.SOUL, new SoulAbility(plugin));
        abilities.put(GenType.INFECTED, new InfectedAbility(plugin));
        abilities.put(GenType.SIGHT, new SightAbility(plugin));
        abilities.put(GenType.MOUNTS, new MountsAbility(plugin));
    }

    public Ability getAbility(GenType type) {
        return abilities.get(type);
    }

    public void executeAbility(GenType type, Player player) {
        Ability ability = getAbility(type);
        if (ability != null) {
            ability.execute(player);
        }
    }
}
