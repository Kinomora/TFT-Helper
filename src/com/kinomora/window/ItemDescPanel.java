package com.kinomora.window;

import com.kinomora.ItemType;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ItemDescPanel extends JPanel {

    public ItemType type;

    public ItemDescPanel() {
        super(new BorderLayout());

        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true), "Item"));
    }

    public void setItemInfo(ItemType type) {
        if (type == null) {
            if (this.type != null) {
                this.removeItemInfo();
                this.type = null;
            }
            return;
        }

        if (this.type == null) {
            this.type = type;
            this.setItemName(type.getIcon(), type.name);
            this.setItemDesc(type.description);
            this.setItemStats();
            this.updateUI();
        }
    }

    private void setItemName(Icon icon, String name) {
        this.add(new JLabel(name, icon, SwingConstants.LEADING), BorderLayout.PAGE_START, 0);
    }

    private void setItemDesc(String desc) {
        JTextPane description = new JTextPane();
        description.setLayout(new BorderLayout());
        description.setEnabled(false);
        description.setText(desc);
        description.setBorder(new TitledBorder("Description"));
        this.add(description, BorderLayout.LINE_START, 1);
    }

    private void setItemStats() {
        StatsPanel statsPanel = new StatsPanel();
        this.add(statsPanel, BorderLayout.EAST, 2);
    }

    private void removeItemInfo() {
        this.removeAll();
        this.updateUI();
    }
}
