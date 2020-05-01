package com.kinomora.window;

import com.kinomora.FileManager;
import com.kinomora.ItemType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Window extends JFrame implements KeyListener {

    public ItemDescPanel itemDescPanel = new ItemDescPanel();
    public InventoryPanel inventoryPanel = new InventoryPanel(itemDescPanel);

    //This is the main window class that creates the program window, the Game and Item(overview) tabs, and all the contents in them
    public Window(String title) throws HeadlessException {
        super(title);
        this.addKeyListener(this);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //The main window that holds the overview tabs
        JTabbedPane tabs = new JTabbedPane();

        //Creating the main overview tabs
        JPanel gameTab = new JPanel();
        JPanel itemsTab = new JPanel();
        JPanel championsTab = new JPanel();

        itemsTab.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.YELLOW);
        topPanel.add(inventoryPanel, BorderLayout.WEST);
        topPanel.add(itemDescPanel, BorderLayout.CENTER);

        JTabbedPane subTabbedPane = new JTabbedPane();
        subTabbedPane.setBackground(Color.BLUE);
        JPanel items = new JPanel();
        items.setLayout(new FlowLayout(FlowLayout.LEADING));

        for (File icon : new File(FileManager.resources, "icons").listFiles()) {
            ItemButton item = new ItemButton(0,false, itemDescPanel, inventoryPanel);
            ItemType type = ItemType.getItemTypeFromName(icon.getName().substring(0, icon.getName().indexOf('.')));
            item.setType(type);
            item.setMargin(new Insets(0, 0, 0, 0));
            items.add(item);
        }

        subTabbedPane.addTab("Items", items);
        subTabbedPane.addTab("Craftables", new JPanel());

        itemsTab.add(topPanel, BorderLayout.PAGE_START);
        itemsTab.add(subTabbedPane, BorderLayout.CENTER);

        tabs.addTab("Game", gameTab);
        tabs.addTab("Items", itemsTab);
        tabs.addTab("Champions", championsTab);

        this.setContentPane(tabs);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /*System.out.println(e);
        if (e.getKeyChar() == ' ') {
            System.out.println("Setting default item");
            itemDescPanel.setItemInfo(new ItemType("bfs.png", "BF Sword", "This is a Big Fucking Sword"));
        }
        else if (e.getKeyChar() == 'r') {
            System.out.println("Clearing item");
            itemDescPanel.setItemInfo(null);
        }*/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*System.out.println(e);
        if (e.getKeyChar() == ' ') {
            System.out.println("Setting default item");
            itemDescPanel.setItemInfo(new ItemType("bfs.png", "BF Sword", "This is a Big Fucking Sword"));
        }
        else if (e.getKeyChar() == 'r') {
            System.out.println("Clearing item");
            itemDescPanel.setItemInfo(null);
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println(e);
    }
}
