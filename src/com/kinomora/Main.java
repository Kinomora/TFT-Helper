package com.kinomora;

import com.kinomora.window.Window;

import javax.swing.*;
import java.io.File;

public class Main {

    public static File icons = new File("icons");
    public static Inventory inventory = new Inventory();

    public static void main(String[] args) throws Exception {
        //set look and feel
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        //load the items from items.json
        FileManager.loadItems();

        //load the recipes from recipes.json - must be done after items
        //FileManager.loadRecipes();

        //Set up the GUI window - must be done after loading items and recipes
        Window window = new Window("TFT Helper - Set 3");
        window.pack();
        window.setSize(691, 890);
        window.setResizable(false);
        window.setVisible(true);

        //Generate item objects (for my inventory)
        Item BFSword = new Item(ItemType.get("B.F. Sword"));
        Item NLR = new Item(ItemType.get("Needlessly Large Rod"));
        Item Tear = new Item(ItemType.get("Tear of the Goddess"));
        Item Belt = new Item(ItemType.get("Giant's Belt"));

        //Add things to my inventory
        inventory.add(Tear, Tear, NLR, Belt, BFSword);

        //Print inventory and craftable
        System.out.println("Current Inventory: " + inventory);
        System.out.println("");
        System.out.println("Craftable items:");
        for (Recipe recipe : CraftingManager.getAllCraftables(inventory)) {
            System.out.println(recipe);
        }
    }
}
