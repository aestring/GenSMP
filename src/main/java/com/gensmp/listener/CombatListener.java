package com.gensmp.listener;

import com.gensmp.GenSMP;
import com.gensmp.gen.GenType;
import com.gensmp.player.PlayerGenData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Player;

/**
 * Handles combat-related Gen effects
 */
public class CombatListener implements Listener {
    private final GenSMP plugin;

    public CombatListener(GenSMP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof Player)) return;

        Player attacker = (Player) event.getDamager();
        Player victim = (Player) event.getEntity();

        PlayerGenData attackerData = plugin.getPlayerDataManager().getPlayerData(attacker);
        
        // Apply damage modifiers based on attacker's Gen
        if (attackerData.hasGen(GenType.STRENGTH) && attackerData.getSlot1Unlocked()) {
            event.setDamage(event.getDamage() * 1.1); // 10% damage boost
        }
    }
}
