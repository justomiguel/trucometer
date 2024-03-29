/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.gj2framework.base;

import com.globant.GameMidlet;
import com.globant.gj2framework.screens.LabeledSplashScreen;
import com.globant.gj2framework.screens.ScreenManager;
import com.globant.models.Phone;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 *
 * @author justo.vargas
 */
public class BaseMidlet extends MIDlet {

    // the manager for the mobile screen
    protected ScreenManager screenManager;

    // the current instance of this midlet
    public static BaseMidlet instance;

    // for the images position
    public Phone mobile;

    // the loader
    public LabeledSplashScreen loaderScreen;

    private Thread loaderThread;

    public BaseMidlet() {

        createLoader();
        
        Display.getDisplay(this).setCurrent(loaderScreen);

        this.screenManager = new ScreenManager(Display.getDisplay(this));
    }

    protected void startApp() {
        screenManager.setOnPause(false);
    }

    protected void pauseApp() {
        screenManager.setOnPause(true);
    }

    protected void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }

    // delegate methods
    public void changeScreen(String name) {
        screenManager.changeScreen(name);
    }

    public void dispose() {
        screenManager.dispose();
        destroyApp(true);
    }

    private void createLoader() {
        loaderScreen = new LabeledSplashScreen();
        this.loaderThread = new Thread(loaderScreen);
        this.loaderThread.start();
    }

    protected void killLoader() {
        loaderScreen.dispose();
        loaderScreen = null;
        loaderThread = null;
    }
    


}
