package com.kinomora;

import com.kinomora.window.utility.TintedIcon;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
        try {
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("icons/"+this.id+".png");
            TintedIcon icon = new TintedIcon(ImageIO.read(inputStream), scale);
            return icon;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Returns the name of the itemtype
    @Override
    public String toString() {
        return this.name;
    }
}
