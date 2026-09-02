package com.gensmp.ability.impl;

import com.gensmp.GenSMP;
import com.gensmp.ability.Ability;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Storm Gen Active Ability: Dash forward in the direction the player is looking
 */
public class StormAbility extends Ability {
    private final GenSMP plugin;

    public StormAbility(GenSMP plugin) {
        super("storm_ability", "Forward Dash", 30);
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        Vector direction = player.getLocation().getDirection().normalize();
        Vector dash = direction.multiply(2.0); // Dash velocity
        player.setVelocity(player.getVelocity().add(dash));
        player.sendMessage("§6[GenSMP] §aStorm dash activated!");
    }
}
