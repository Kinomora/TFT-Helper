package com.kinomora.window.overview.championpooltab;

import com.kinomora.window.overview.ChampionPoolTab;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ChampPoolProbabilityPanel extends JPanel {


    public final ChampionPoolTab parent;
    public TitledBorder border;

    public ChampPoolProbabilityPanel(ChampionPoolTab parent) {
        super(new BorderLayout());
        this.parent = parent;
        this.border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), "Probabilities");
        this.setBorder(border);


        /*for (int i = 0; i < 5; i++) {
            //TODO: change this so that you import the recommended champions from a file such as the items file or something
            JLabel championIcon = new JLabel(ItemButton.EMPTY_ICON);
            JLabel[] championsArray = new JLabel[0];
            championsArray[i] = championIcon;

            //TODO: Change the border to the actual champion name instead of hard-coding it
            championIcon.setBorder(new TitledBorder("Champion " + (i + 1)));

            //Add the button to the inventory panel
            this.add(championIcon, i);
        }*/
    }












    /*public final ItemsOverviewTab parent;
    public ItemType type;
    public TitledBorder border;

    public ItemDescPanel(ItemsOverviewTab parent) {
        super(new BorderLayout());
        this.parent = parent;
        this.border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), "Item");
        this.setBorder(border);
    }

    public void setItemInfo(ItemType type) {
        if (type == null) {
            if (this.type != null) {
                this.removeItemInfo();
                this.type = null;
            }
            return;
        }

        if (this.type == null) {
            this.type = type;
            this.setItemName();
            this.setItemDesc();
            this.updateUI();
        }
    }

    private void setItemName() {
        // Change the code here to add the recipe for the item where result = item + item
        // get the recipe from recipe checker
        // add a panel and add all items here to it

        //Get the recipe for the item we are display
        Recipe recipe = CraftingManager.getRecipe(type);

        JPanel recipeDisplay = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //Add the output item
        recipeDisplay.add(new JLabel(this.type.getIcon(ItemButton.INV_ICON_SCALE), SwingConstants.LEADING));

        //Check if there is no recipe
        if(recipe != null){
            recipeDisplay.add(new JLabel(" = "));
            recipeDisplay.add(new JLabel(recipe.input1.getIcon(ItemButton.INV_ICON_SCALE), SwingConstants.LEADING));
            recipeDisplay.add(new JLabel(" + "));
            recipeDisplay.add(new JLabel(recipe.input2.getIcon(ItemButton.INV_ICON_SCALE), SwingConstants.LEADING));
        }
        this.add(recipeDisplay, BorderLayout.PAGE_START);
        this.border.setTitle(this.type.name);
    }

    private void setItemDesc() {
        JTextArea description = new JTextArea();
        description.setLayout(new BorderLayout());
        description.setEnabled(false);
        description.setText(this.type.description);
        description.setBorder(new TitledBorder("Description"));
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setLineWrap(true);
        description.setOpaque(false);
        this.add(description);

        this.add(new ChampionPanel(), BorderLayout.SOUTH);
    }

    private void removeItemInfo() {
        this.removeAll();
        this.updateUI();
        this.border.setTitle("Item");
    }*/
}
