package com.kinomora.window;

import com.kinomora.CraftingManager;
import com.kinomora.Inventory;
import com.kinomora.ItemType;
import com.kinomora.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ItemButton extends JButton implements ActionListener, MouseListener {

    public ItemType type;
    public final boolean isInventory;
    public final ItemDescPanel itemDescPanel;
    public final InventoryPanel inventoryPanel;
    public final int index;

    public ItemButton(int index, boolean isInventory, ItemDescPanel itemDescPanel, InventoryPanel inventoryPanel) {
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setIcon(new ImageIcon("empty.png"));
        this.setEnabled(false);
        this.isInventory = isInventory;
        this.itemDescPanel = itemDescPanel;
        this.addMouseListener(this);
        this.addActionListener(this);
        this.inventoryPanel = inventoryPanel;
        this.index = index;
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
        if (isInventory) {
            this.inventoryPanel.removeItem(index);
            this.itemDescPanel.setItemInfo(null);
        }
        else {
            this.inventoryPanel.addItem(type);
        }
    }

    //Mouse listener events
    @Override
    public void mouseEntered(MouseEvent e) {
        this.itemDescPanel.setItemInfo(this.type);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.itemDescPanel.setItemInfo(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO: Remove this test code
        if (this.inventoryPanel.inventory.items.size() > 1) {
            System.out.println("Craftable items:");
            for (Recipe recipe : CraftingManager.getAllCraftables(this.inventoryPanel.inventory)) {
                System.out.println(recipe);
            }
            System.out.println("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
