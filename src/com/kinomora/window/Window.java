package com.kinomora.window;

import com.kinomora.FileManager;
import com.kinomora.ItemType;
import com.kinomora.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Window extends JFrame implements KeyListener {

    public ItemDescPanel itemDescPanel = new ItemDescPanel();
    public InventoryPanel inventoryPanel = new InventoryPanel(itemDescPanel);

    public Window(String title) throws HeadlessException {
        super(title);
        this.addKeyListener(this);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        JPanel itemsTab = new JPanel();
        itemsTab.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.YELLOW);
        topPanel.add(inventoryPanel, BorderLayout.WEST);
        topPanel.add(itemDescPanel, BorderLayout.CENTER);

        JTabbedPane otherTabbedPane = new JTabbedPane();
        otherTabbedPane.setBackground(Color.BLUE);
        JPanel items = new JPanel();
        items.setLayout(new FlowLayout(FlowLayout.LEADING));

        for (File icon : new File(FileManager.resources, "icons").listFiles()) {
            ItemButton item = new ItemButton(0,false, itemDescPanel, inventoryPanel);
            ItemType type = ItemType.get(icon.getName().substring(0, icon.getName().indexOf('.')));
            item.setType(type);
            item.setMargin(new Insets(0, 0, 0, 0));
            items.add(item);
        }

        otherTabbedPane.addTab("Items", items);
        otherTabbedPane.addTab("Craftables", new JPanel());

        itemsTab.add(topPanel, BorderLayout.PAGE_START);
        itemsTab.add(otherTabbedPane, BorderLayout.CENTER);

        JPanel gameTab = new JPanel();
        tabs.addTab("Game", gameTab);
        tabs.addTab("Items", itemsTab);

        this.setContentPane(tabs);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e);
        if (e.getKeyChar() == ' ') {
            System.out.println("Setting default item");
            itemDescPanel.setItemInfo(new ItemType("bfs.png", "BF Sword", "This is a Big Fucking Sword"));
        }
        else if (e.getKeyChar() == 'r') {
            System.out.println("Clearing item");
            itemDescPanel.setItemInfo(null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        if (e.getKeyChar() == ' ') {
            System.out.println("Setting default item");
            itemDescPanel.setItemInfo(new ItemType("bfs.png", "BF Sword", "This is a Big Fucking Sword"));
        }
        else if (e.getKeyChar() == 'r') {
            System.out.println("Clearing item");
            itemDescPanel.setItemInfo(null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e);
    }
}
