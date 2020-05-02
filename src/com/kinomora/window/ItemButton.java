package com.kinomora.window;

import com.kinomora.CraftingManager;
import com.kinomora.ItemType;
import com.kinomora.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class ItemButton extends JButton implements ActionListener, MouseListener {

    public ItemType type;
    public final boolean isInventory;
    public final ItemDescPanel itemDescPanel;
    public final InventoryPanel inventoryPanel;
    public final int index;
    private boolean hasMouseExited;

    public ItemButton(int index, boolean isInventory, ItemDescPanel itemDescPanel, InventoryPanel inventoryPanel) {
        //local variables
        this.isInventory = isInventory;
        this.itemDescPanel = itemDescPanel;
        this.inventoryPanel = inventoryPanel;
        this.index = index;

        //Listeners
        this.addMouseListener(this);
        this.addActionListener(this);

        //object configuration
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setIcon(new ImageIcon("empty.png"));
        this.setEnabled(false);
    }

    public void setType(ItemType type) {
        if (type == null) {
            this.setEnabled(false);
            this.setIcon(new ImageIcon("empty.png"));
        }
        else {
            this.setEnabled(true);
            this.setIcon(type.getIcon());
        }
        this.type = type;
    }

    //Action Listener events
    @Override
    public void actionPerformed(ActionEvent e) {
        this.hasMouseExited = false;
    }

    //Mouse listener events
    @Override
    public void mouseEntered(MouseEvent e) {
        this.itemDescPanel.setItemInfo(this.type);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.itemDescPanel.setItemInfo(null);
        this.hasMouseExited = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!hasMouseExited) {
            //Left click is add-to-inventory, no crafting
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (isInventory) {
                    this.inventoryPanel.removeItem(index);
                    this.itemDescPanel.setItemInfo(null);
                }
                else {
                    this.inventoryPanel.addItem(type);
                }
            }
        }

        //Right click is craft item from inventory items
        if(e.getButton() == MouseEvent.BUTTON3)
        {
            //Create a recipe object for the itemType that we are hovering over
            Recipe recipeForItem = CraftingManager.getRecipe(type);

            //Check if the inventory has the items needed to craft this item
            CraftingManager.craft(this.inventoryPanel.inventory, type);
                inventoryPanel.sortInventory();
        }
    }
}
