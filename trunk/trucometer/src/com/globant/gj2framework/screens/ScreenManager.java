/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.gj2framework.screens;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

/**
 *
 * @author Miguel
 */
public class ScreenManager {

    // a collection to save references to the game screens
    private Hashtable screens;

    // the current screen
    private Screen currentScreen;

    // the current thread that manage the screen
    private Thread currentThreadScreen;

    // the display
    private Display display;

    // pause state
    protected boolean onPause;

    //
    public boolean initialized;

    public ScreenManager(Display display) {
        this.screens = new Hashtable();
        this.display = display;
        initialized = onPause = false;
    }

    public void registerScreen(String name, Displayable screen) {
        screens.put(name, screen);
    }

    public Screen getScreen(String name) {
        return (Screen) screens.get(name);
    }

    public void initScreens() {
        // recorrer un Hashtable
        for (Enumeration e = screens.keys(); e.hasMoreElements();) {
            String clave = (String) e.nextElement();
            Screen screen = (Screen) screens.get(clave);
            screen.init();
        }
    }

    public void changeScreen(String name) {
        Screen newScreen = getScreen(name);
        if (newScreen != currentScreen) {
            newScreen.reset();
            if (currentScreen != null) {
                currentScreen.stop();
            }
            this.currentThreadScreen = null;
            display.setCurrent(newScreen);

            currentScreen = newScreen;

            this.currentThreadScreen = new Thread(currentScreen);
            this.currentThreadScreen.start();

            newScreen.init();
        }
    }

    public boolean isOnPause() {
        return onPause;
    }

    public void setOnPause(boolean onPause) {
        this.onPause = onPause;
        if (onPause){
            currentScreen.stop();
            this.currentThreadScreen = null;
        } else {
            currentScreen.reset();
            display.setCurrent(currentScreen);
            this.currentThreadScreen = new Thread(currentScreen);
            this.currentThreadScreen.start();
        }
    }

    public String getCurrentScreenName() {
        return currentScreen.getName();
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public void dispose() {
        // recorrer un Hashtable
        for (Enumeration e = screens.keys(); e.hasMoreElements();) {
            String clave = (String) e.nextElement();
            Screen screen = (Screen) screens.get(clave);
            screen.dispose();
        }
    }
}
