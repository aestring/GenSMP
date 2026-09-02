package com.gensmp.recipe;

import com.gensmp.GenSMP;
import com.gensmp.gen.GenType;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

/**
 * Manages crafting recipes for Gen items
 */
public class GenRecipeManager {
    private final GenSMP plugin;

    public GenRecipeManager(GenSMP plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers all Gen crafting recipes
     */
    public void registerRecipes() {
        for (GenType type : GenType.values()) {
            registerGenRecipe(type);
        }
    }

    /**
     * Registers a crafting recipe for a specific Gen
     */
    private void registerGenRecipe(GenType type) {
        ItemStack result = plugin.getGenManager().createGenItem(type);
        NamespacedKey key = new NamespacedKey(plugin, "gen_" + type.getId());

        ShapedRecipe recipe = new ShapedRecipe(key, result);

        // Shape pattern (each Gen has unique recipe)
        recipe.shape(
                "ABC",
                "DEF",
                "GHI"
        );

        // Set ingredients based on Gen type
        setRecipeIngredients(recipe, type);

        plugin.getServer().addRecipe(recipe);
    }

    /**
     * Sets the ingredients for a Gen recipe based on type
     */
    private void setRecipeIngredients(ShapedRecipe recipe, GenType type) {
        switch (type) {
            case STRENGTH:
                recipe.setIngredient('A', Material.IRON_BLOCK);
                recipe.setIngredient('B', Material.DIAMOND_BLOCK);
                recipe.setIngredient('C', Material.IRON_BLOCK);
                recipe.setIngredient('D', Material.DIAMOND);
                recipe.setIngredient('E', Material.GOLDEN_APPLE);
                recipe.setIngredient('F', Material.DIAMOND);
                recipe.setIngredient('G', Material.IRON_BLOCK);
                recipe.setIngredient('H', Material.DIAMOND_BLOCK);
                recipe.setIngredient('I', Material.IRON_BLOCK);
                break;
            case STORM:
                recipe.setIngredient('A', Material.LIGHTNING_ROD);
                recipe.setIngredient('B', Material.AMETHYST_BLOCK);
                recipe.setIngredient('C', Material.LIGHTNING_ROD);
                recipe.setIngredient('D', Material.AMETHYST_CLUSTER);
                recipe.setIngredient('E', Material.LIGHTNING_ROD);
                recipe.setIngredient('F', Material.AMETHYST_CLUSTER);
                recipe.setIngredient('G', Material.LIGHTNING_ROD);
                recipe.setIngredient('H', Material.AMETHYST_BLOCK);
                recipe.setIngredient('I', Material.LIGHTNING_ROD);
                break;
            // Add more recipes for other Gen types
            default:
                // Default recipe using material-appropriate blocks
                recipe.setIngredient('A', type.getIcon());
                recipe.setIngredient('B', Material.DIAMOND_BLOCK);
                recipe.setIngredient('C', type.getIcon());
                recipe.setIngredient('D', Material.DIAMOND);
                recipe.setIngredient('E', type.getIcon());
                recipe.setIngredient('F', Material.DIAMOND);
                recipe.setIngredient('G', type.getIcon());
                recipe.setIngredient('H', Material.DIAMOND_BLOCK);
                recipe.setIngredient('I', type.getIcon());
                break;
        }
    }
}
