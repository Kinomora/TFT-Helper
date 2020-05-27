package com.kinomora.window.overview;

import com.kinomora.window.overview.playertab.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class PlayerOverviewTab extends JPanel {

    public final PlayerPanel playerPanel;

    public PlayerOverviewTab() {
        this.playerPanel = new PlayerPanel(this);

        this.setLayout(new BorderLayout());

        this.add(playerPanel, BorderLayout.CENTER);
    }
}
