package com.kinomora.window.overview.itemstab;

import com.kinomora.Inventory;
import com.kinomora.Item;
import com.kinomora.ItemType;
import com.kinomora.window.utility.ItemButton;
import com.kinomora.window.overview.ItemsOverviewTab;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InventoryPanel extends JPanel {

    public final ItemsOverviewTab parent;
    public final Inventory inventory = new Inventory();
    public final ItemButton[] buttonsArray = new ItemButton[10];

    public InventoryPanel(ItemsOverviewTab parent) {
        super(new GridLayout(5, 2));
        this.parent = parent;
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "Inventory",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                Font.getFont(Font.MONOSPACED)
        ));

        for (int i = 0; i < 10; i++) {
            ItemButton button = new ItemButton(i, true, parent);
            //adds each button to an arraylist of buttons
            buttonsArray[i] = button;

            //Add the button to the inventory panel
            this.add(button, i);
        }
    }

    //resorts the inventory so that there are empty bottoms a
    public void sortInventory() {
        int i = 0;
        //update all the the button icons with our inventory for if the inventory is less than full (10 items)
        for (; i < inventory.items.size(); i++) {
            //set the button to display the item in the inventory
            buttonsArray[i].setType(inventory.items.get(i).type);
        }
        //clears the remaining items in the inventory if the inventory has less than 10 items
        for (; i < 10; i++) {
            //set any button in the inventory panel to empty if there is not item in that inventory slot
            buttonsArray[i].setType(null);
        }

        this.parent.itemsPanel.updateItems();
    }

    public void addItem(ItemType itemType) {
        if (!inventory.isFull()) {
            this.inventory.add(new Item(itemType));
            sortInventory();
        }
    }

    public void removeItem(int index) {
        if (index < this.inventory.items.size()) {
            this.inventory.items.remove(index);
            sortInventory();
        }
    }
}
