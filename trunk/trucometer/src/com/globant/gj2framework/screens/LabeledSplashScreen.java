/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.gj2framework.screens;

import com.globant.gj2framework.screens.uielements.CanvasString;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author justo.vargas
 */
public class LabeledSplashScreen extends SplashScreen {

    // string for the splashscreen title
    private CanvasString txtLabel;
    // current progress
    private CanvasString txtProgress;

    public LabeledSplashScreen(Image img, String stringLabel) {
        this.img = img;
        this.txtLabel = new CanvasString(this.getWidth() / 4, this.getHeight() / 4, stringLabel);
        this.txtProgress = new CanvasString(this.getWidth() / 4, this.getHeight() / 2, "0%");
    }

    public LabeledSplashScreen(String stringLabel) {
        this.img = null;
        this.txtLabel = new CanvasString(this.getWidth() / 4, this.getHeight() / 4, stringLabel);
        this.txtProgress = new CanvasString(this.getWidth() / 4, this.getHeight() / 2, "0 %");
    }

    public LabeledSplashScreen() {
        this.img = null;
        this.txtLabel = new CanvasString(this.getWidth() / 4, this.getHeight() / 4, "Loading");
        this.txtProgress = new CanvasString(this.getWidth() / 4, this.getHeight() / 2, "0 %");
    }

    protected void paint(Graphics g) {
        super.paint(g);
        if (img == null) {
            g.setColor(0x000000);
            g.setStrokeStyle(2);
            g.drawRect(0, 0, getWidth(), getHeight());
            g.setStrokeStyle(0);
            g.setColor(0xCCFF00);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        g.setColor(0x000000);
        // draw label
        g.drawString(txtLabel.name, txtLabel.xPercent, txtLabel.yPercent, Graphics.TOP | Graphics.LEFT);
        // draw progress
        g.drawString(txtProgress.name, txtProgress.xPercent, txtProgress.yPercent, Graphics.TOP | Graphics.LEFT);
    }

    public void run() {
        while (isLoading) {
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void setProgress(int progress){
        this.txtProgress.name = progress+" %";
    }

    public void dispose(){

    }


}
