package com.kinomora.window;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class StatsPanel extends JPanel {

    public StatsPanel() {
        super(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3, true),
                "Stats",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                Font.getFont(Font.MONOSPACED),
                Color.BLACK
        ));
        this.add(new JPanel(), BorderLayout.CENTER);
    }
}
