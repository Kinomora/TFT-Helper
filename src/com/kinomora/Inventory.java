package com.kinomora;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
    public final List<Item> items;

    //Items I have on my bench
    public Inventory() {
        items = new LinkedList<>();
    }

    //adding an item/items to my inventory
    public void add(Item... items) {
        this.items.addAll(Arrays.asList(items));
    }

    //remove an item from my inventory
    public void remove(Item... items) {
        for (Item item : items) {
            this.items.remove(item);
        }
    }

    public boolean isFull() {
        return this.items.size() == 10;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}