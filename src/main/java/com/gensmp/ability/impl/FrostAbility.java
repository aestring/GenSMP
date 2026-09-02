package com.gensmp.ability.impl;

import com.gensmp.GenSMP;
import com.gensmp.ability.Ability;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.util.Vector;

/**
 * Frost Gen Active Ability: Throw a snowball that freezes the target for 5 seconds
 */
public class FrostAbility extends Ability {
    private final GenSMP plugin;

    public FrostAbility(GenSMP plugin) {
        super("frost_ability", "Frost Snowball", 30);
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        Vector direction = player.getLocation().getDirection().normalize();
        Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
        snowball.setShooter(player);
        snowball.setVelocity(direction.multiply(1.5));
        player.sendMessage("§6[GenSMP] §aFrost snowball thrown!");
    }
}
