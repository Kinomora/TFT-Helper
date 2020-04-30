package com.kinomora;

import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ItemTypeTypeAdapter extends TypeAdapter<ItemType> {

    @Override
    public ItemType read(JsonReader jsonReader) throws IOException {
        JsonObject itemJsonObject = FileManager.gson.fromJson(jsonReader, JsonObject.class);

        //Pull the itemname and description from the items.json file
        String itemID = itemJsonObject.get("ID").getAsString();
        String itemName = itemJsonObject.get("Name").getAsString();
        String itemDescription = itemJsonObject.get("Description").getAsString();

        //return a new ItemType from the items.json file
        return new ItemType(itemID, itemName, itemDescription);
    }

    @Override
    public void write(JsonWriter jsonWriter, ItemType itemType) throws IOException {
        //will never write to file
    }
}
