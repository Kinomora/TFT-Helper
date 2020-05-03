package com.kinomora.window;

import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TintedIcon extends ImageIcon {

    private BufferedImage tintedImage;
    private boolean shouldTint = false;

    public TintedIcon(String filename, float scale) {
        super(filename);
        this.setImage(this.getImage().getScaledInstance((int)(scale * this.getIconWidth()), (int)(scale * this.getIconHeight()), Image.SCALE_SMOOTH));
        BufferedImage img = ((ToolkitImage) this.getImage()).getBufferedImage();
        this.tintedImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        for (int i = 0; i < this.tintedImage.getWidth(); i++) {
            for (int j = 0; j < this.tintedImage.getHeight(); j++) {
                this.tintedImage.setRGB(i, j, new Color(img.getRGB(i, j), true).darker().darker().darker().darker().getRGB());
            }
        }
    }

    public void tint(boolean shouldTint) {
        this.shouldTint = shouldTint;
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        if (this.shouldTint) {
            if(this.getImageObserver() == null) {
                g.drawImage(this.tintedImage, x, y, c);
            } else {
                g.drawImage(this.tintedImage, x, y, this.getImageObserver());
            }
        } else {
            super.paintIcon(c, g, x, y);
        }
    }
}
