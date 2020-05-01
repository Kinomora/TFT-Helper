package com.kinomora.window.overview;

import com.kinomora.FileManager;
import com.kinomora.ItemType;
import com.kinomora.window.InventoryPanel;
import com.kinomora.window.ItemButton;
import com.kinomora.window.ItemDescPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ItemsOverviewPane extends JFrame {

    public void ItemsOverviewPane(ItemDescPanel itemDescPanel, InventoryPanel inventoryPanel) {
        JTabbedPane subTabbedPane = new JTabbedPane();
        subTabbedPane.setBackground(Color.BLUE);
        JPanel items = new JPanel();
        items.setLayout(new FlowLayout(FlowLayout.LEADING));

        for (File icon : new File(FileManager.resources, "icons").listFiles()) {
            ItemButton item = new ItemButton(0, false, itemDescPanel, inventoryPanel);
            ItemType type = ItemType.getItemTypeFromName(icon.getName().substring(0, icon.getName().indexOf('.')));
            item.setType(type);
            item.setMargin(new Insets(0, 0, 0, 0));
            items.add(item);
        }
    }
}
