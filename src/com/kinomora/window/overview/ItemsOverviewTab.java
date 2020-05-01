package com.kinomora.window.overview;

import com.kinomora.CraftingManager;
import com.kinomora.FileManager;
import com.kinomora.ItemType;
import com.kinomora.window.InventoryPanel;
import com.kinomora.window.ItemButton;
import com.kinomora.window.ItemDescPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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

        //Add the top panel and the bottom pane to the main Items(Overview) window
        itemsTab.add(topPanel, BorderLayout.PAGE_START);
        itemsTab.add(createItemsSubpanel(), BorderLayout.CENTER);
    }

    /**
     * ItemsSuppanel within the Items Overview tab
     */
    private JPanel createItemsSubpanel() {
        // Make the Items subtab to the
        JPanel itemsSubPanel = new JPanel();

        itemsSubPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        itemsSubPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "Items",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                Font.getFont(Font.MONOSPACED)
        ));

        //Adds all items from the /resources/icons folder to the bottomPanel
        for (File icon : new File(FileManager.resources, "icons").listFiles()) {
            ItemButton item = new ItemButton(0, false, itemDescPanel, inventoryPanel);
            ItemType type = ItemType.getItemTypeFromName(icon.getName().substring(0, icon.getName().indexOf('.')));
            item.setType(type);
            item.setMargin(new Insets(0, 0, 0, 0));
            itemsSubPanel.add(item);
        }
        return itemsSubPanel;
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
