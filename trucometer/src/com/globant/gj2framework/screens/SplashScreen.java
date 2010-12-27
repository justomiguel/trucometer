/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.gj2framework.screens;

import com.globant.gj2framework.screens.uielements.CanvasString;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author justo.vargas
 */
public class SplashScreen extends Canvas implements Runnable {

    // image for the background
    protected Image img;

    //
    protected boolean isLoading;

    public SplashScreen(Image img) {
        this.img = img;
    }

    public SplashScreen() {
    }

    protected void paint(Graphics g) {
        
    }

    public void run() {

    }
}
