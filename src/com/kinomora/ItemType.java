package com.kinomora;

import com.kinomora.window.TintedIcon;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//These objects are unique items types, and is what creates the item object
public class ItemType {
    //Map<String(ItemID), String(ItemName)
    public static final Map<String, String> itemIDLookup = new HashMap<>();
    //Map<String(ItemName), ItemType(object)
    public static final Map<String, ItemType> REGISTERED = new HashMap<>();
    public static final List<ItemType> BASIC_TYPES = new ArrayList<>();
    public final String id;
    public final String name;
    public final String description;

    public ItemType(String itemID, String name, String description) {
        this.id = itemID;
        this.name = name;
        this.description = description;
    }

    public boolean isBasic() {
        return CraftingManager.getRecipe(this) == null;
    }

    public static ItemType getItemTypeFromName(String name) {
        return REGISTERED.get(name);
    }

    public static ItemType getIDFromName(String name){
        return REGISTERED.get(itemIDLookup.get(name));
    }

    public TintedIcon getIcon(float scale){
        File file = new File(FileManager.resources,"icons/"+this.id+".png");
        TintedIcon icon = new TintedIcon(file.getPath(), scale);
        return icon;
    }

    //Returns the name of the itemtype
    @Override
    public String toString() {
        return this.name;
    }
}
