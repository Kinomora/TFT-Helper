package com.kinomora;

import com.kinomora.window.Window;

import javax.swing.*;
import java.io.File;
import java.net.Inet4Address;

public class Main {

    public static File icons = new File("icons");

    public static void main(String[] args) throws Exception {
        //set look and feel
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        //load the items from items.json
        FileManager.loadItems();

        //load the recipes from recipes.json - must be done after items
        FileManager.loadRecipes();

        //Set up the GUI window - must be done after loading items and recipes
        Window window = new Window("TFT Helper - Set 3");
        window.pack();
        window.setSize(696, 902);
        window.setResizable(false);
        window.setVisible(true);
    }
}
