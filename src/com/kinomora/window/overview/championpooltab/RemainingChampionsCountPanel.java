package com.kinomora.window.overview.championpooltab;

import com.kinomora.window.overview.ChampionPoolTab;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RemainingChampionsCountPanel extends JPanel {

    public final ChampionPoolTab parent;
    public TitledBorder border;

    public RemainingChampionsCountPanel(ChampionPoolTab parent){
        super(new GridLayout(9, 5));
        this.parent = parent;
        this.border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), "Champion Pool");
        this.setBorder(border);
    }


 /*
 On game start, create an immutable table of all units, as well as a pool(hashmap?) of all currently in-use units perhaps in a similar way to items and itemTypes. At the start of the game, 0 units will be in-use
 The in-use pool could be a hashmap with a key for each player, containing then a list of each unit and their quantity.

 When the game starts, get the champions from the carousel. As each person takes a unit, add that unit to the currently in-use pool. This can either be done in real-time, or as the carousel round ends.

 We will need to determine if we update in real-time, or once per round. When this is decided, we will need to check each player's bench and field for units, and their star level.
 Then, we will need to either clear the in-use pool, or update it on a per-player basis, making sure that any units that are sold are added back to the pool, and any units bought are removed.

 These counts can then be subtracted from the immutable table of counts per unit, then the quantity of "remaining units" can be overlaid on top of the icons for each champion.
>>>these numbers will be slightly off if we are unable to check the opponents shops(unlikely) as the units in players shops are also removed from the pool and added back in as the shop is rerolled
  */
}
