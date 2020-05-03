package com.kinomora.window.overview.itemstab;

import com.kinomora.*;
import com.kinomora.window.ItemButton;
import com.kinomora.window.overview.ItemsOverviewTab;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.util.*;

public class ItemsPanel extends JPanel {

    public final ItemsOverviewTab parent;
    private final Map<ItemType, ItemButton> BUTTONS;

    public ItemsPanel(ItemsOverviewTab parent) {
        this.parent = parent;
        this.BUTTONS = new HashMap<>();

        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "Items",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                Font.getFont(Font.MONOSPACED)
        ));

        this.populateItems();
        this.updateItems();
    }

    /**
     * Adds all the item icons inside this panel
     */
    private void populateItems() {
        //Adds all items from the /resources/icons folder to the bottomPanel
        for (int i = 0; i < 2; i++)
        for (File icon : Objects.requireNonNull(new File(FileManager.resources, "icons").listFiles())) {
            ItemButton item = new ItemButton(0, false, this.parent);
            ItemType type = ItemType.getItemTypeFromName(icon.getName().substring(0, icon.getName().indexOf('.')));
            item.setType(type);
            item.setMargin(new Insets(0, 0, 0, 0));
            BUTTONS.put(type, item);
            this.add(item);
        }
    }

    public void updateItems() {
        Inventory inventory = parent.inventoryPanel.inventory;

        Set<ItemType> craftableItems = new HashSet<>();
        for (Recipe recipe : CraftingManager.getAllCraftables(inventory)) {
            craftableItems.add(recipe.output);
        }

        for (Map.Entry<ItemType, ItemButton> entry : BUTTONS.entrySet()) {
            ItemType type = entry.getKey();
            ItemButton button = entry.getValue();
            if (type.isBasic()) {
                continue;
            }

            button.setCraftable(craftableItems.contains(type));
        }

        this.updateUI();
    }
}
