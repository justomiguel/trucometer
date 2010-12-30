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

    //
    protected int totalLoaded;
    //
    protected int initialLoad;
    //
    protected int duration;
    //
    protected int countToLoad;
    //
    protected int seconds;

    public SplashScreen(Image img) {
        setFullScreenMode(true);
        this.img = img;
        isLoading = true;
        totalLoaded = 0;
        duration = 0;
        seconds = 0;
        initialLoad = 0;
    }

    public SplashScreen() {
        setFullScreenMode(true);
        isLoading = true;
        totalLoaded = 0;
        duration = 0;
        seconds = 0;
        initialLoad = 0;
    }

    protected void paint(Graphics g) {
        
    }

    public void run() {

    }

    public void dispose(){
        isLoading = false;
    }

    public boolean isIsLoading() {
        return isLoading;
    }

    
}
