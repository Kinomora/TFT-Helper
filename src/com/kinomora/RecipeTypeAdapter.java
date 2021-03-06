package com.kinomora;

import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class RecipeTypeAdapter extends TypeAdapter<Recipe> {

    @Override
    public Recipe read(JsonReader jsonReader) throws IOException {
        JsonObject recipeJsonObject = FileManager.gson.fromJson(jsonReader, JsonObject.class);

        ItemType outputItemID = ItemType.getIDFromName(recipeJsonObject.get("Output").getAsString());
        ItemType inputItem1ID = ItemType.getIDFromName(recipeJsonObject.getAsJsonArray("Inputs").get(0).getAsString());
        ItemType inputItem2ID = ItemType.getIDFromName(recipeJsonObject.getAsJsonArray("Inputs").get(1).getAsString());

        return new Recipe(outputItemID, inputItem1ID, inputItem2ID);
    }

    @Override
    public void write(JsonWriter jsonWriter, Recipe recipe) throws IOException {
        //will never write json recipes
    }
}
