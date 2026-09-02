package com.gensmp.player;

import com.gensmp.GenSMP;
import com.gensmp.gen.GenType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Manages player data persistence and retrieval
 */
public class PlayerDataManager {
    private final GenSMP plugin;
    private final Map<UUID, PlayerGenData> playerData = new HashMap<>();
    private final File dataFolder;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public PlayerDataManager(GenSMP plugin) {
        this.plugin = plugin;
        this.dataFolder = new File(plugin.getDataFolder(), "playerdata");

        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        // Load all existing player data
        loadAllPlayers();
    }

    /**
     * Gets or creates player data
     */
    public PlayerGenData getPlayerData(UUID uuid) {
        return playerData.computeIfAbsent(uuid, k -> loadPlayerData(uuid));
    }

    /**
     * Gets or creates player data for an online player
     */
    public PlayerGenData getPlayerData(Player player) {
        return getPlayerData(player.getUniqueId());
    }

    /**
     * Loads player data from file
     */
    private PlayerGenData loadPlayerData(UUID uuid) {
        File playerFile = new File(dataFolder, uuid + ".json");
        PlayerGenData data = new PlayerGenData(uuid);

        if (playerFile.exists()) {
            try (FileReader reader = new FileReader(playerFile)) {
                JsonObject json = gson.fromJson(reader, JsonObject.class);

                // Load Gens
                if (json.has("slot1") && !json.get("slot1").isJsonNull()) {
                    data.setSlot1(GenType.fromId(json.get("slot1").getAsString()));
                }
                if (json.has("slot2") && !json.get("slot2").isJsonNull()) {
                    data.setSlot2(GenType.fromId(json.get("slot2").getAsString()));
                }

                // Load unlock times
                if (json.has("slot1UnlockTime")) {
                    data.setSlot1UnlockTime(json.get("slot1UnlockTime").getAsLong());
                }
                if (json.has("slot2UnlockTime")) {
                    data.setSlot2UnlockTime(json.get("slot2UnlockTime").getAsLong());
                }

                // Load starter assigned flag
                if (json.has("starterAssigned")) {
                    data.setStarterAssigned(json.get("starterAssigned").getAsBoolean());
                }
            } catch (IOException e) {
                plugin.getLogger().warning("Could not load player data for " + uuid + ": " + e.getMessage());
            }
        }

        return data;
    }

    /**
     * Saves player data to file
     */
    public void savePlayerData(PlayerGenData data) {
        File playerFile = new File(dataFolder, data.getUUID() + ".json");

        try (FileWriter writer = new FileWriter(playerFile)) {
            JsonObject json = new JsonObject();
            json.addProperty("uuid", data.getUUID().toString());
            json.addProperty("slot1", data.getSlot1() != null ? data.getSlot1().getId() : null);
            json.addProperty("slot2", data.getSlot2() != null ? data.getSlot2().getId() : null);
            json.addProperty("slot1UnlockTime", data.getSlot1UnlockTime());
            json.addProperty("slot2UnlockTime", data.getSlot2UnlockTime());
            json.addProperty("starterAssigned", data.isStarterAssigned());
            json.addProperty("lastSave", System.currentTimeMillis());

            gson.toJson(json, writer);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save player data for " + data.getUUID() + ": " + e.getMessage());
        }
    }

    /**
     * Saves all loaded player data
     */
    public void saveAllPlayers() {
        for (PlayerGenData data : playerData.values()) {
            savePlayerData(data);
        }
    }

    /**
     * Loads all player data files from disk
     */
    private void loadAllPlayers() {
        File[] files = dataFolder.listFiles((dir, name) -> name.endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                String uuidStr = file.getName().replace(".json", "");
                try {
                    UUID uuid = UUID.fromString(uuidStr);
                    playerData.put(uuid, loadPlayerData(uuid));
                } catch (IllegalArgumentException e) {
                    plugin.getLogger().warning("Invalid player data file: " + file.getName());
                }
            }
        }
    }

    /**
     * Unloads player data from memory
     */
    public void unloadPlayer(UUID uuid) {
        PlayerGenData data = playerData.remove(uuid);
        if (data != null) {
            savePlayerData(data);
        }
    }
}
