package com.kinomora.window;

import com.kinomora.window.overview.ChampionsOverviewTab;
import com.kinomora.window.overview.GameOverviewTab;
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
        GameOverviewTab gameOverviewTab = new GameOverviewTab();
        ItemsOverviewTab itemsOverviewTab = new ItemsOverviewTab();
        ChampionsOverviewTab championsOverviewTab = new ChampionsOverviewTab();

        //Add the tabs to the main window
        /*tabs.addTab("Game", gameOverviewTab.getGameTab());*/
        tabs.addTab("Items", itemsOverviewTab);
        /*tabs.addTab("Champions", championsOverviewTab.getGameTab());*/

        //???
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
