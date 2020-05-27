package com.kinomora.window.overview;

import com.kinomora.window.overview.championpooltab.ChampPoolProbabilityPanel;
import com.kinomora.window.overview.championpooltab.RemainingChampionsCountPanel;

import javax.swing.*;
import java.awt.*;

public class ChampionPoolTab extends JPanel {

    public final RemainingChampionsCountPanel remainingChampionsCountPanel;
    public final ChampPoolProbabilityPanel champPoolProbabilityPanel;

    public ChampionPoolTab() {
        this.remainingChampionsCountPanel = new RemainingChampionsCountPanel(this);
        this.champPoolProbabilityPanel = new ChampPoolProbabilityPanel(this);

        //Set the layout for the window
        this.setLayout(new BorderLayout());

        //Add the "currently available champions" panel to the top half of the page
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(remainingChampionsCountPanel, BorderLayout.NORTH);

        //Add the probability list of the champion tiers to
        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(champPoolProbabilityPanel, BorderLayout.CENTER);
    }
}
