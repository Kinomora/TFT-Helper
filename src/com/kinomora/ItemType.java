package com.kinomora;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

//These objects are unique items types, and is what creates the item object
public class ItemType {
    public final static Map<String, ItemType> REGISTERED = new HashMap<>();
    public final String id;
    public final String name;
    public final String description;

    public ItemType(String itemID, String name, String description) {
        this.id = itemID;
        this.name = name;
        this.description = description;
    }

    public static ItemType get(String name) {
        return REGISTERED.get(name);
    }

    public Icon getIcon(){
        File file = new File(FileManager.resources,"icons/"+this.id+".png");
        return new ImageIcon(file.getPath());
    }

    //Returns the name of the itemtype
    @Override
    public String toString() {
        return this.name;
    }
}
