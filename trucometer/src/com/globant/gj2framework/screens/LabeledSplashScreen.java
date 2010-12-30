/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.gj2framework.screens;

import com.globant.gj2framework.screens.uielements.CanvasString;
import javax.microedition.lcdui.Font;
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
        super();
        this.img = img;
        this.txtLabel = new CanvasString(this.getWidth() / 2, this.getHeight() / 4, stringLabel);
        this.txtProgress = new CanvasString(this.getWidth() / 2, this.getHeight() / 2, "0");
    }

    public LabeledSplashScreen(String stringLabel) {
        super();
        this.img = null;
        this.txtLabel = new CanvasString(this.getWidth() / 2, this.getHeight() / 4, stringLabel);
        this.txtProgress = new CanvasString(this.getWidth() / 2, this.getHeight() / 2, "0");
    }

    public LabeledSplashScreen() {
        super();
        this.img = null;
        this.txtLabel = new CanvasString(this.getWidth() / 2, this.getHeight() / 4, "Loading");
        this.txtProgress = new CanvasString(this.getWidth() / 2, this.getHeight() / 2, "0");
    }

    protected void paint(Graphics g) {
        super.paint(g);
        if (img == null) {
            g.setColor(0x000000);
            g.setStrokeStyle(1);
            g.drawRect(0, 0, getWidth(), getHeight());
            g.setStrokeStyle(0);
            g.setColor(0xCCFF00);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        g.setColor(0x000000);
        
        // draw label
        g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_UNDERLINED, Font.SIZE_LARGE));
        g.drawString(txtLabel.name, txtLabel.xPercent, txtLabel.yPercent, Graphics.BASELINE | Graphics.HCENTER);

        // draw progress
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        g.drawString(txtProgress.name + " %", txtProgress.xPercent, txtProgress.yPercent, Graphics.BASELINE | Graphics.HCENTER);

        // draw globant logo :)
        // draw progress
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_LARGE));
        g.drawString("Globant", this.getWidth()/2, this.getHeight() - this.getHeight()/8, Graphics.BASELINE | Graphics.HCENTER);
    }

    public void run() {
        while (isLoading) {
            updateTimer();
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void setProgress(int progress, int duration) {
        if (progress > totalLoaded) {
            seconds = 0;
            countToLoad = progress;
            initialLoad = totalLoaded;
            this.duration = duration * 1000;
        } else if (progress == 100) {
            isLoading = false;
        } else {
            countToLoad = 0;
            duration = 0;
        }
    }

    public void dispose() {
        super.dispose();
    }

    private void updateTimer() {
        if (countToLoad > 0 && totalLoaded < 100) {
            seconds+=80;
            totalLoaded = seconds * (initialLoad + countToLoad) / duration;
            txtProgress.name = String.valueOf(totalLoaded > 100? 100 : totalLoaded);
        }
    }
}
