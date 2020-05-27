package com.kinomora.window;

import com.kinomora.window.overview.PlayerOverviewTab;
import com.kinomora.window.overview.ChampionPoolTab;
import com.kinomora.window.overview.ItemsOverviewTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements KeyListener {



    //This is the main window class that creates the program window, the Game and Item(overview) tabs, and all the contents in them
    public Window(String title) throws HeadlessException {
        super(title);
        this.addKeyListener(this);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //The main window that holds the overview tabs
        JTabbedPane tabs = new JTabbedPane();

        //Creating the main overview tabs
        ChampionPoolTab championPoolTab = new ChampionPoolTab();
        ItemsOverviewTab itemsOverviewTab = new ItemsOverviewTab();
        PlayerOverviewTab playerOverviewTab = new PlayerOverviewTab();

        //Add the tabs to the main window
        tabs.addTab("Champions", championPoolTab);
        tabs.addTab("Players", playerOverviewTab);
        tabs.addTab("Items", itemsOverviewTab);

        this.setContentPane(tabs);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
