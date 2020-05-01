package com.kinomora.window;

import com.kinomora.ItemType;
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ItemDescPanel extends JPanel {

    public ItemType type;
    public TitledBorder border;

    public ItemDescPanel() {
        super(new BorderLayout());
        this.border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true), "Item");
        this.setBorder(border);
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
        this.add(new JLabel(icon, SwingConstants.LEADING), BorderLayout.PAGE_START);
        this.border.setTitle(name);
    }

    private void setItemDesc(String desc) {
        JTextArea description = new JTextArea();
        description.setLayout(new BorderLayout());
        description.setEnabled(false);
        description.setText(desc);
        description.setBorder(new TitledBorder("Description"));
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setLineWrap(true);
        description.setOpaque(false);
        this.add(description);

        this.add(new ChampionPanel(), BorderLayout.SOUTH);
    }

    private void setItemStats() {
        StatsPanel statsPanel = new StatsPanel();
    }

    private void removeItemInfo() {
        this.removeAll();
        this.updateUI();
        this.border.setTitle("Item");
    }
}
