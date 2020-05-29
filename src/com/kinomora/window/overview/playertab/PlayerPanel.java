package com.kinomora.window.overview.playertab;

import com.kinomora.window.overview.PlayerOverviewTab;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PlayerPanel extends JPanel {

    public TitledBorder border;

    public PlayerPanel(PlayerOverviewTab parent) {
        super(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "Players",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                Font.getFont(Font.MONOSPACED),
                Color.BLACK
        ));
        this.add(new JPanel(), BorderLayout.CENTER);
    }
}
