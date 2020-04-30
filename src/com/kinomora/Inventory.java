package com.kinomora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {
    public final List<Item> items;

    //Items I have on my bench
    public Inventory() {
        items = new ArrayList<>();
    }

    //adding an item/items to my inventory
    public void add(Item... items){
        this.items.addAll(Arrays.asList(items));
    }

    //remove an item from my inventory
    public void remove(Item... items){
        for(Item item : items) {
            this.items.remove(item);
        }
    }

    @Override
    public String toString() {
        return items.toString();
    }
}