package com.kinomora.window;

import com.kinomora.CraftingManager;
import com.kinomora.ItemType;
import com.kinomora.window.overview.ItemsOverviewTab;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class ItemButton extends JButton implements ActionListener, MouseListener {

    public static final float INV_ICON_SCALE = 0.7f;
    public static final float ITEMS_ICON_SCALE = 0.7f;
    public static final ImageIcon EMPTY_ICON = new ImageIcon();

    static {
        BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_BYTE_GRAY);
        EMPTY_ICON.setImage(img.getScaledInstance((int)(INV_ICON_SCALE * img.getWidth()), (int)(INV_ICON_SCALE * img.getHeight()), Image.SCALE_SMOOTH));
    }

    public ItemType type;
    public final boolean isInventory;
    public final ItemsOverviewTab itemsTab;
    public final int index;
    private boolean hasMouseExited;

    public ItemButton(int index, boolean isInventory, ItemsOverviewTab itemsTab) {
        //local variables
        this.index = index;
        this.isInventory = isInventory;
        this.itemsTab = itemsTab;

        //Listeners
        this.addMouseListener(this);
        this.addActionListener(this);

        //object configuration
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setIcon(EMPTY_ICON);
        this.setEnabled(false);
    }

    public void setType(ItemType type) {
        if (type == null) {
            this.setEnabled(false);
            this.setIcon(EMPTY_ICON);
        } else {
            float scale = isInventory ? INV_ICON_SCALE : ITEMS_ICON_SCALE;

            this.setEnabled(true);
            this.setIcon(type.getIcon(scale));
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
        this.itemsTab.itemDescPanel.setItemInfo(this.type);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.itemsTab.itemDescPanel.setItemInfo(null);
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
                    this.itemsTab.inventoryPanel.removeItem(index);
                    this.itemsTab.itemDescPanel.setItemInfo(null);
                } else {
                    this.itemsTab.inventoryPanel.addItem(type);
                }
            }
        }

        //Right click is craft item from inventory items
        if (e.getButton() == MouseEvent.BUTTON3) {
            //Check if the inventory has the items needed to craft this item
            CraftingManager.craft(this.itemsTab.inventoryPanel.inventory, type);
            this.itemsTab.inventoryPanel.sortInventory();
        }
    }

    public void setCraftable(boolean isCraftable) {
        ((TintedIcon)this.getIcon()).tint(!isCraftable);
    }
}
