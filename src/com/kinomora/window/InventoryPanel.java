package com.kinomora.window;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InventoryPanel extends JPanel {

        public InventoryPanel(ItemDescPanel itemDescPanel) {
        super(new GridLayout(5, 2));
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3, true),
                "Inventory",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                Font.getFont(Font.MONOSPACED),
                Color.BLUE
        ));

        for (int i = 0; i < 10; i++) {
            ItemButton button = new ItemButton(true, itemDescPanel);

            this.add(button);
        }
    }




}
