package com.kinomora;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;

public class FileManager {
    public static File resources = new File("resources");
    public static Gson gson = new GsonBuilder().registerTypeAdapter(Recipe.class, new RecipeTypeAdapter()).registerTypeAdapter(ItemType.class, new ItemTypeTypeAdapter()).create();

    //Load the items from the items.json file
    public static void loadItems() throws Exception {
        File itemsFile = new File(resources, "items.json");
        JsonReader jsonReader = new JsonReader(new FileReader(itemsFile));

        //loads entire items.json file as a json array object
        JsonArray jsonArray = gson.fromJson(jsonReader, JsonArray.class);

        for (JsonElement element : jsonArray) {
            if (element instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) element;
                ItemType newItem = gson.fromJson(jsonObject, ItemType.class);
                ItemType.REGISTERED.put(newItem.id,newItem);
                ItemType.itemIDLookup.put(newItem.name, newItem.id);
                if (Integer.parseInt(jsonObject.get("ID").getAsString()) < 10) {
                    ItemType.BASIC_TYPES.add(newItem);
                }
            }
        }
    }

    //Load the recipes from the recipes.json file
    public static void loadRecipes() throws Exception {
        File recipesFile = new File(resources, "recipes.json");
        JsonReader jsonReader = new JsonReader(new FileReader(recipesFile));

        //loads entire recipes.json file as a json array object
        JsonArray jsonArray = gson.fromJson(jsonReader, JsonArray.class);

        for (JsonElement element : jsonArray) {
            if (element instanceof JsonObject) {
                //creates a new Json object that stores the data from the recipes.json object currently being read
                JsonObject jsonObject = (JsonObject) element;

                //Creates a new recipe object from the jsonObject and the 3 values pulled from the json recipe object passed into the recipe class constructor
                Recipe newRecipe = gson.fromJson(jsonObject, Recipe.class);

                //Sends the newRecipe object to the crafting manager to add the recipe to the Map of recipes
                CraftingManager.addRecipe(newRecipe);
            }
        }
    }
}
