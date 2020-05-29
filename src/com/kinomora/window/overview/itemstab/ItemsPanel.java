package com.kinomora.window.overview.itemstab;

import com.kinomora.CraftingManager;
import com.kinomora.Inventory;
import com.kinomora.ItemType;
import com.kinomora.Recipe;
import com.kinomora.window.utility.ItemButton;
import com.kinomora.window.overview.ItemsOverviewTab;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;
import java.util.*;

public class ItemsPanel extends JPanel {

    public final ItemsOverviewTab parent;
    private final Map<ItemType, Pair<ItemButton, ItemButton>> BUTTONS;

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
        List<ItemType> basicTypes = ItemType.BASIC_TYPES;
        for (int y = 0; y < basicTypes.size(); y++) {
            ItemType left = basicTypes.get(y);
            for (int x = 0; x < basicTypes.size(); x++) {
                ItemType top = basicTypes.get(x);

                ItemButton button = new ItemButton(0, false, this.parent);

                if (x == 0) {               // is first row (basic type)
                    button.setType(left);
                } else if (y == 0) {        // if first column (basic type)
                    button.setType(top);
                } else {
                    button.setType(CraftingManager.getRecipe(top, left).output);
                }

                System.out.println("Adding item " + button.type + " to slot (" + x + ", " + y + ")");

                this.storeItemButton(button);
                this.add(button);
            }
        }
    }

    public void storeItemButton(ItemButton button) {
        Pair<ItemButton, ItemButton> pair = BUTTONS.get(button.type);

        if (pair == null) {
            pair = new Pair<>(button, null);
            this.BUTTONS.put(button.type, pair);
            return;
        }

        this.BUTTONS.put(button.type, new Pair<>(pair.getKey(), button));
    }

    public void updateItems() {
        Inventory inventory = parent.inventoryPanel.inventory;

        Set<ItemType> craftableItems = new HashSet<>();
        for (Recipe recipe : CraftingManager.getAllCraftables(inventory)) {
            craftableItems.add(recipe.output);
        }

        for (Map.Entry<ItemType, Pair<ItemButton, ItemButton>> entry : BUTTONS.entrySet()) {
            ItemType type = entry.getKey();
            Pair<ItemButton, ItemButton> button = entry.getValue();

            if (type.isBasic()) {
                continue;
            }

            button.getKey().setCraftable(craftableItems.contains(type));
            if (button.getValue() != null) {
                button.getValue().setCraftable(craftableItems.contains(type));
            }
        }

        this.updateUI();
    }
}
