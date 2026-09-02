package com.gensmp;

import com.gensmp.ability.AbilityManager;
import com.gensmp.command.GenSMPCommand;
import com.gensmp.cooldown.CooldownManager;
import com.gensmp.dna.DNARegistry;
import com.gensmp.gen.GenManager;
import com.gensmp.gui.GenMenuManager;
import com.gensmp.listener.*;
import com.gensmp.passive.PassiveManager;
import com.gensmp.player.PlayerDataManager;
import com.gensmp.recipe.GenRecipeManager;
import com.gensmp.util.ConfigManager;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * GenSMP - A DNA/Gen-based ability system for Minecraft
 * Main plugin class
 */
public class GenSMP extends JavaPlugin {

    private static GenSMP instance;

    // Managers
    private ConfigManager configManager;
    private GenManager genManager;
    private PlayerDataManager playerDataManager;
    private AbilityManager abilityManager;
    private PassiveManager passiveManager;
    private CooldownManager cooldownManager;
    private DNARegistry dnaRegistry;
    private GenMenuManager genMenuManager;
    private GenRecipeManager recipeManager;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Enabling GenSMP v" + getDescription().getVersion());

        // Initialize managers in order
        this.configManager = new ConfigManager(this);
        this.cooldownManager = new CooldownManager();
        this.genManager = new GenManager(this);
        this.playerDataManager = new PlayerDataManager(this);
        this.abilityManager = new AbilityManager(this);
        this.passiveManager = new PassiveManager(this);
        this.dnaRegistry = new DNARegistry(this);
        this.genMenuManager = new GenMenuManager(this);
        this.recipeManager = new GenRecipeManager(this);

        // Register listeners
        registerListeners();

        // Register commands
        registerCommands();

        // Register crafting recipes
        recipeManager.registerRecipes();

        getLogger().info("GenSMP enabled successfully!");
    }

    @Override
    public void onDisable() {
        if (playerDataManager != null) {
            playerDataManager.saveAllPlayers();
        }
        getLogger().info("GenSMP disabled.");
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new GenConsumeListener(this), this);
        getServer().getPluginManager().registerEvents(new CombatListener(this), this);
        getServer().getPluginManager().registerEvents(new AbilityListener(this), this);
        getServer().getPluginManager().registerEvents(new PassiveListener(this), this);
    }

    private void registerCommands() {
        getCommand("gensmp").setExecutor(new GenSMPCommand(this));
    }

    public static GenSMP getInstance() {
        return instance;
    }

    /**
     * Creates a NamespacedKey for GenSMP
     */
    public NamespacedKey getNamespacedKey(String key) {
        return new NamespacedKey(this, key);
    }

    // Getter methods for managers
    public ConfigManager getConfigManager() {
        return configManager;
    }

    public GenManager getGenManager() {
        return genManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public AbilityManager getAbilityManager() {
        return abilityManager;
    }

    public PassiveManager getPassiveManager() {
        return passiveManager;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public DNARegistry getDNARegistry() {
        return dnaRegistry;
    }

    public GenMenuManager getGenMenuManager() {
        return genMenuManager;
    }

    public GenRecipeManager getRecipeManager() {
        return recipeManager;
    }
}
