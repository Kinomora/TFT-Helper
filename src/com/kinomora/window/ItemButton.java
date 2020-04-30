package com.kinomora.window;

import com.kinomora.Inventory;
import com.kinomora.ItemType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ItemButton extends JButton implements MouseListener {

    public ItemType type;
    public final boolean isInventory;
    public final ItemDescPanel itemDescPanel;

    public ItemButton(boolean isInventory, ItemDescPanel itemDescPanel) {
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setIcon(new ImageIcon("empty.png"));
        this.setEnabled(false);
        this.isInventory = isInventory;
        this.itemDescPanel = itemDescPanel;
        this.addMouseListener(this);
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
        //if in isinventory=true, remove item from inventory on click
        //if not in inventory, on click add to inventory
        //item will be new item of itemtype of type variable
        //possibly store "Inventory" as a variable in main

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
