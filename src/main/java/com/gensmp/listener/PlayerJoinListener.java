package com.gensmp.listener;

import com.gensmp.GenSMP;
import com.gensmp.gen.GenType;
import com.gensmp.player.PlayerGenData;
import com.gensmp.util.Messages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Handles player join events - assigns starter Gen if needed
 */
public class PlayerJoinListener implements Listener {
    private final GenSMP plugin;

    public PlayerJoinListener(GenSMP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerGenData data = plugin.getPlayerDataManager().getPlayerData(event.getPlayer());

        if (!data.isStarterAssigned()) {
            // Assign a random starter Gen
            GenType[] types = GenType.values();
            GenType starter = types[(int) (Math.random() * types.length)];
            
            data.setSlot1(starter);
            data.setStarterAssigned(true);
            data.setSlot1UnlockTime(System.currentTimeMillis() + 600000); // 10 minutes
            plugin.getPlayerDataManager().savePlayerData(data);

            event.getPlayer().sendMessage(String.format(Messages.GEN_FIRST_TIME, starter.getDisplayName()));
        }
    }
}
