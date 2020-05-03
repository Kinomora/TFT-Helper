package com.kinomora;

import javafx.util.Pair;

import java.util.*;

public class CraftingManager {
    //creates a hashmap of craftable itemtypes and their recipes
    private static final Map<Pair<ItemType, ItemType>, Recipe> RECIPES = new HashMap<>();
    private static final Map<ItemType, Recipe> RECIPES_FROM_OUTPUT = new HashMap<>();

    //add a recipe from a source (code, json, ect)
    public static void addRecipe(Recipe recipe) {
        //adds to the RECIPES map a recipe where item1 and item2 are paired ingredients.
        RECIPES.put(new Pair(recipe.input1, recipe.input2), recipe);

        //Populated the map with a search for the recipe for a given output
        RECIPES_FROM_OUTPUT.put(recipe.output, recipe);
    }

    //returns a recipe for a given itemtype output ie How craft an item, what two items you need
    public static Recipe getRecipe(ItemType itemType1, ItemType itemType2) {
        return RECIPES.get(new Pair(itemType1, itemType2));
    }

    //returns the recipe for a given output item
    public static Recipe getRecipe(ItemType output) {
        return RECIPES_FROM_OUTPUT.get(output);
    }

    // Uses two actual items from the inventory to craft 1
    public static Item craft(Inventory inventory, Item item1, Item item2) {
        //Gets the recipe for the two items provided
        Recipe recipe = getRecipe(item1.type, item2.type);

        // Returns the item that is crafted with the two provided items
        if (recipe == null) {
            return null;
        }
        // Adds the output of the recipe and removes the two ingredients from the inventory
        else {
            inventory.remove(item1, item2);
            Item craftingResult = new Item(recipe.output);
            inventory.add(craftingResult);
            return craftingResult;
        }
    }

    /**
     * @param inventory
     * @param output
     * @return
     */
    public static Item craft(Inventory inventory, ItemType output) {
        if (!canCraft(inventory, output)) {
            return null;
        }

        Recipe recipe = getRecipe(output);
        boolean hasItem1BeenRemoved = false, hasItem2BeenRemoved = false;

        // Adds the output of the recipe and removes the two ingredients from the inventory
        for (Iterator<Item> iterator = inventory.items.iterator(); iterator.hasNext(); ) {
            Item item = iterator.next();
            if (!hasItem1BeenRemoved && item.type == recipe.input1) {
                iterator.remove();
                hasItem1BeenRemoved = true;
            }
            else if (!hasItem2BeenRemoved && item.type == recipe.input2) {
                iterator.remove();
                hasItem2BeenRemoved = true;
            }
        }

        Item craftingResult = new Item(recipe.output);
        inventory.add(craftingResult);
        return craftingResult;
    }

    /**
     * Prevents you from crafting something if you don't have the items available to craft it
     *
     * @param inventory The inventory to craft the item from
     * @param itemType  The item to be crafted
     * @return Whether or not you can craft the item from the provided inventory
     */
    public static boolean canCraft(Inventory inventory, ItemType itemType) {
        Recipe recipe = getRecipe(itemType);
        return getAllCraftables(inventory).contains(recipe);
    }

    // Finds all possible items that can be crafted with a given inventory
    public static Set<Recipe> getAllCraftables(Inventory inventory) {
        // This is a set of all possible craftable recipes and stores the recipes as they are discovered
        // Sets only allow unique values so "reversable" crafting recipes (BF sword + rod, Rod + bf sword) will not be added twice
        Set<Recipe> craftables = new HashSet<>();

        //Iterates through the inventory
        for (int i = 0; i < inventory.items.size(); i++) {
            for (int j = 0; j < inventory.items.size(); j++) {
                if (i == j) {
                    // This prevents attempting to craft an item with itself
                    continue;
                }

                // Iterate through the inventory and get the item in inventory slot i and get the recipe of crafting it with j
                Recipe recipeResult = getRecipe(inventory.items.get(i).type, inventory.items.get(j).type);
                if (recipeResult != null) {
                    craftables.add(recipeResult);
                }
            }
        }
        return craftables;
    }
}
