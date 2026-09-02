package com.gensmp.ability.impl;

import com.gensmp.GenSMP;
import com.gensmp.ability.Ability;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Fire Gen Active Ability: Shoot 3 fireballs sequentially in the direction the player is looking
 */
public class FireAbility extends Ability {
    private final GenSMP plugin;

    public FireAbility(GenSMP plugin) {
        super("fire_ability", "Fireball Barrage", 30);
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        Vector direction = player.getLocation().getDirection().normalize();
        player.sendMessage("§6[GenSMP] §aFiring fireballs!");

        // Fire 3 fireballs with slight delays
        for (int i = 0; i < 3; i++) {
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                Fireball fireball = player.getWorld().spawn(player.getEyeLocation(), Fireball.class);
                fireball.setShooter(player);
                fireball.setVelocity(direction.multiply(1.5));
                fireball.setYield(2.0f); // Explosion power
            }, i * 5L);
        }
    }
}
