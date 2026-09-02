package com.gensmp.util;

import com.gensmp.GenSMP;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Manages plugin configuration loading and saving
 */
public class ConfigManager {
    private final GenSMP plugin;
    private FileConfiguration config;
    private File configFile;

    public ConfigManager(GenSMP plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    private void loadConfig() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        configFile = new File(plugin.getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void reloadConfig() {
        loadConfig();
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save config.yml: " + e.getMessage());
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    // Convenience methods
    public int getCooldown(String abilityType) {
        return config.getInt("cooldowns." + abilityType, 30);
    }

    public int getDNACooldown() {
        return config.getInt("cooldowns.dna", 90);
    }

    public int getPurgeDuration() {
        return config.getInt("purge.duration", 600);
    }

    public double getStarterGenChance(String genId) {
        return config.getDouble("starter-gens." + genId, 0.0);
    }
}
