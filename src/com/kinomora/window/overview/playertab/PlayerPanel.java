package com.kinomora.window.overview.playertab;

import com.kinomora.window.overview.PlayerOverviewTab;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PlayerPanel extends JPanel {

    public final PlayerOverviewTab parent;
    public TitledBorder border;

    public PlayerPanel(PlayerOverviewTab parent){
        super(new GridLayout(9, 5));
        this.parent = parent;
        this.border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), "Players");
        this.setBorder(border);
    }
}
