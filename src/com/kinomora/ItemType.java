package com.kinomora;

import com.kinomora.window.TintedIcon;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

//These objects are unique items types, and is what creates the item object
public class ItemType {
    //Map<String(ItemID), String(ItemName)
    public final static Map<String, String> itemIDLookup = new HashMap<>();
    //Map<String(ItemName), ItemType(object)
    public final static Map<String, ItemType> REGISTERED = new HashMap<>();
    public final String id;
    public final String name;
    public final String description;

    public ItemType(String itemID, String name, String description) {
        this.id = itemID;
        this.name = name;
        this.description = description;
    }

    public static ItemType getItemTypeFromName(String name) {
        return REGISTERED.get(name);
    }

    public static ItemType getIDFromName(String name){
        return REGISTERED.get(itemIDLookup.get(name));
    }

    public Icon getIcon(){
        File file = new File(FileManager.resources,"icons/"+this.id+".png");
        return new TintedIcon(file.getPath());
    }

    //Returns the name of the itemtype
    @Override
    public String toString() {
        return this.name;
    }
}
