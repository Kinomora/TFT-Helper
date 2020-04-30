package com.kinomora;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;

public class FileManager {
    public static File resources = new File("resources");
    public static Gson gson = new GsonBuilder().registerTypeAdapter(Recipe.class, new RecipeTypeAdapter()).registerTypeAdapter(ItemType.class, new ItemTypeTypeAdapter()).create();


    public static void loadItems() throws Exception{
        File itemsFile = new File(resources, "items.json");
        JsonReader jsonReader = new JsonReader(new FileReader(itemsFile));

        //loads entire items.json file as a json array object
        JsonArray jsonArray = gson.fromJson(jsonReader, JsonArray.class);

        for (JsonElement element : jsonArray) {
            if (element instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) element;
                ItemType newItem = gson.fromJson(jsonObject, ItemType.class);
                ItemType.REGISTERED.put(newItem.id,newItem);
            }
        }
    }

    public static void loadRecipes() throws Exception {
        File recipesFile = new File(resources, "recipes.json");
        JsonReader jsonReader = new JsonReader(new FileReader(recipesFile));

        //loads entire recipes.json file as a json array object
        JsonArray jsonArray = gson.fromJson(jsonReader, JsonArray.class);

        for (JsonElement element : jsonArray) {
            if (element instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) element;
                Recipe newRecipe = gson.fromJson(jsonObject, Recipe.class);
                CraftingManager.addRecipe(newRecipe);
            }
        }
    }
}
