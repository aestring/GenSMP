package com.gensmp.dna.impl;

import com.gensmp.GenSMP;
import com.gensmp.dna.DNACombination;
import com.gensmp.gen.GenType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Strength + Fire: Shoot 3 fireballs sequentially (DNA version with 90s cooldown)
 */
public class StrengthFireCombo extends DNACombination {
    private final GenSMP plugin;

    public StrengthFireCombo(GenSMP plugin) {
        super(GenType.STRENGTH, GenType.FIRE, "Inferno Barrage", "Fire 3 explosive fireballs");
        this.plugin = plugin;
    }

    @Override
    public void execute(Player player) {
        Vector direction = player.getLocation().getDirection().normalize();
        player.sendMessage("§6[GenSMP] §aInferno barrage activated!");

        for (int i = 0; i < 3; i++) {
            final int index = i;
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                Fireball fireball = player.getWorld().spawn(player.getEyeLocation(), Fireball.class);
                fireball.setShooter(player);
                fireball.setVelocity(direction.multiply(1.5));
                fireball.setYield(2.0f);
            }, index * 5L);
        }
    }
}
