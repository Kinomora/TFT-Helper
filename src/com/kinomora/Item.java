package com.kinomora;

//This class is the physical object on your bench, you might have multiple of the same item type (bf sword, etc) and each object is one copy of this
public class Item {
    public final ItemType type;

    public Item(ItemType type) {
        this.type = type;
    }

    //Return the name of the item
    @Override
    public String toString() {
        return this.type.toString();
    }
}
