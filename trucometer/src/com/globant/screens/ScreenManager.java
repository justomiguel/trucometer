/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.screens;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

/**
 *
 * @author Miguel
 */
public class ScreenManager {

    public static final String SCREEN_MENU = "menu";
    public static final String SCREEN_OPT = "opciones";
    public static final String SCREEN_GAME = "game";

    private Hashtable screens;
    private Screen currentScreen;
    private Thread currentThreadScreen;
    private Display display;

    public ScreenManager(Display display) {
        this.screens = new Hashtable();
        this.display = display;
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
