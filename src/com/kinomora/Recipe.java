package com.kinomora;

public class Recipe {

    public final ItemType output;
    public final ItemType input1;
    public final ItemType input2;

    public Recipe(ItemType output, ItemType input1, ItemType input2) {

        this.output = output;
        this.input1 = input1;
        this.input2 = input2;
    }

    @Override
    public String toString() {
        return output + " = " + input1 + " + " + input2;
    }
}
