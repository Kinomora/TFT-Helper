package com.kinomora.window.overview;

import com.kinomora.window.InventoryPanel;
import com.kinomora.window.ItemDescPanel;
import com.kinomora.window.ItemsPanel;

import javax.swing.*;
import java.awt.*;

public class ItemsOverviewTab extends JPanel {

    public final ItemsPanel itemsPanel;
    public final ItemDescPanel itemDescPanel;
    public final InventoryPanel inventoryPanel;

    /**
     * Creates and populates the Items(Overview) tab which contains the Inventory, Item Descriptions, Recommended Champions, and Inventory Item Crafting Calculator
     */
    public ItemsOverviewTab() {
        this.itemDescPanel = new ItemDescPanel(this);
        this.inventoryPanel = new InventoryPanel(this);
        this.itemsPanel = new ItemsPanel(this);

        //Set the layout for the window
        this.setLayout(new BorderLayout());

        //Top half of the Items(overview) tab, contains the inventory and item(desc, etc) windows
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inventoryPanel, BorderLayout.WEST);
        topPanel.add(itemDescPanel, BorderLayout.CENTER);

        //Add the top panel and the bottom pane to the main Items(Overview) window
        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(this.itemsPanel, BorderLayout.CENTER);
    }
}
