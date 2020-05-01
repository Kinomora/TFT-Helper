package com.kinomora.window.overview;

import com.kinomora.FileManager;
import com.kinomora.ItemType;
import com.kinomora.window.InventoryPanel;
import com.kinomora.window.ItemButton;
import com.kinomora.window.ItemDescPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ItemsOverviewTab {

    public JPanel itemsTab = new JPanel();
    public ItemDescPanel itemDescPanel = new ItemDescPanel();
    public InventoryPanel inventoryPanel = new InventoryPanel(itemDescPanel);

    /**
     * Creates and populates the Items(Overview) tab which contains the Inventory, Item Descriptions, Recommended Champions, and Inventory Item Crafting Calculator
     */
    public ItemsOverviewTab() {
        generateTab();
    }

    //This contains all the code for adding content to the Champion tab
    private void generateTab() {

        //Set the layout for the window
        itemsTab.setLayout(new BorderLayout());

        //Top half of the Items(overview) tab, contains the inventory and item(desc, etc) windows
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inventoryPanel, BorderLayout.WEST);
        topPanel.add(itemDescPanel, BorderLayout.CENTER);

        //Bottom half of the Items(overview) tab, contains the Items and Craftable tabs
        JTabbedPane subTabbedPane = new JTabbedPane();
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        //Calls the methods to create the two subtabs for "Items" and "Craftables" in the bottom panel
        populateItemSubtab(subTabbedPane, bottomPanel);
        populateCraftingSubtab(subTabbedPane);

        //Add the top panel and the bottom pane to the main Items(Overview) window
        itemsTab.add(topPanel, BorderLayout.PAGE_START);
        itemsTab.add(subTabbedPane, BorderLayout.CENTER);
    }

    private void populateItemSubtab(JTabbedPane subTabbedPane, JPanel bottomPanel) {
        subTabbedPane.addTab("Items", bottomPanel);

        //Adds all items from the /resources/icons folder to the bottomPanel
        for (File icon : new File(FileManager.resources, "icons").listFiles()) {
            ItemButton item = new ItemButton(0, false, itemDescPanel, inventoryPanel);
            ItemType type = ItemType.getItemTypeFromName(icon.getName().substring(0, icon.getName().indexOf('.')));
            item.setType(type);
            item.setMargin(new Insets(0, 0, 0, 0));
            bottomPanel.add(item);
        }
    }

    private void populateCraftingSubtab(JTabbedPane subTabbedPane) {
        subTabbedPane.addTab("Craftables", new JPanel());
        //TODO Generate code that will need to be updated as the inventory is updated, to display new craftable items
    }

    /**
     * Simply returns the JPanel object for itemsTab
     *
     * @return JPanel object of itemsTab
     */
    public JPanel getItemsTab() {
        return itemsTab;
    }
}
