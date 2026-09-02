package com.gensmp.listener;

import com.gensmp.GenSMP;
import com.gensmp.cooldown.CooldownManager;
import com.gensmp.gen.GenType;
import com.gensmp.player.PlayerGenData;
import com.gensmp.util.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * Handles ability activation through player interactions (left/right click)
 */
public class AbilityListener implements Listener {
    private final GenSMP plugin;

    public AbilityListener(GenSMP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAbilityActivate(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        PlayerGenData data = plugin.getPlayerDataManager().getPlayerData(player);
        CooldownManager cooldowns = plugin.getCooldownManager();

        // Left click = Ability 1 (Slot 1)
        if (event.getAction().toString().contains("LEFT")) {
            GenType gen1 = data.getSlot1();
            if (gen1 == null) return;

            if (!data.isSlot1Unlocked()) {
                long remaining = data.getSlot1RemainingTime() / 1000;
                player.sendMessage(Messages.ABILITY_LOCKED);
                return;
            }

            String abilityKey = "ability_" + gen1.getId();
            if (cooldowns.isOnCooldown(player.getUniqueId(), abilityKey)) {
                long remaining = cooldowns.getRemainingCooldownSeconds(player.getUniqueId(), abilityKey);
                player.sendMessage(String.format(Messages.ABILITY_COOLDOWN, remaining));
                return;
            }

            plugin.getAbilityManager().executeAbility(gen1, player);
            cooldowns.setCooldown(player.getUniqueId(), abilityKey, 30);
        }
    }
}
