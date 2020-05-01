package com.kinomora.window;

import com.kinomora.Inventory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ChampionPanel extends JPanel {

    public final Inventory inventory = new Inventory();
    public final JLabel[] championsArray = new JLabel[6];

    public ChampionPanel() {
        //Maximum of 6 champions are recommended for an item
        super(new GridLayout(1, 6));
        this.setEnabled(false);
        this.setBorder(new TitledBorder("Champions"));
        this.setOpaque(false);

        for (int i = 0; i < 6; i++) {
            //TODO: change this so that you import the recommended champions from a file such as the items file or something
            JLabel championIcon = new JLabel(new ImageIcon("empty.png"));
            championsArray[i] = championIcon;

            //TODO: Change the border to the actual champion name instead of hard-coding it
            championIcon.setBorder(new TitledBorder("Champion " + (i + 1)));

            //Add the button to the inventory panel
            this.add(championIcon, i);
        }
    }

}
