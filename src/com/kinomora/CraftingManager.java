package com.kinomora;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CraftingManager {
    //creates a hashmap of craftable itemtypes and their recipes
    private static final Map<Pair<ItemType, ItemType>, Recipe> RECIPES = new HashMap<>();


    //add a recipe from a source (code, json, ect)
    public static void addRecipe(Recipe recipe) {
        //adds to the RECIPES map a recipe where item1 and item2 are paired ingredients.
        //RECIPES.put(new Pair(recipe.input1, recipe.input2), recipe); - old code for adding recipes to the recipes map
        RECIPES.put(new Pair(recipe.input1, recipe.input2), recipe);
    }

    //returns a recipe for a given itemtype output ie How craft an item, what two items you need
    public static Recipe getRecipe(ItemType itemType1, ItemType itemType2) {
        return RECIPES.get(new Pair(itemType1, itemType2));
    }

    //uses two actual items to craft 1
    public static Item craft(Inventory inventory, Item item1, Item item2) {
        //Gets the recipe for the two items provided
        Recipe recipe = getRecipe(item1.type, item2.type);

        //Returns the item that is crafted with the two provided items
        if (recipe == null) {
            return null;
        }
        else {
            inventory.remove(item1, item2);
            Item craftingResult = new Item(recipe.output);
            inventory.add(craftingResult);
            return craftingResult;
        }
    }

    //finds all possible items that can be crafted with a given inventory
    public static Set<Recipe> getAllCraftables(Inventory inventory) {
        //this is a set of all possible craftable recipes and stores the recipes as they are discovered
        Set<Recipe> craftables = new HashSet<>();

        //iterates through the inventory
        for (int i = 0; i < inventory.items.size(); i++) {
            for (int j = 0; j < inventory.items.size(); j++) {
                if (i == j) {
                    //this prevents attempting to craft an item with itself
                    continue;
                }

                //iterate through the inventory and get the item in inventory slot i and get the recipe of crafting it with j
                Recipe recipeResult = getRecipe(inventory.items.get(i).type, inventory.items.get(j).type);
                if (recipeResult != null) {
                    craftables.add(recipeResult);
                }
            }
        }
        return craftables;
    }
}
