/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.screens;

import java.util.Hashtable;
import javax.microedition.lcdui.Displayable;

/**
 *
 * @author Miguel
 */
public class ScreenManager {

    public Hashtable screens;

    public ScreenManager() {
        this.screens = new Hashtable();
    }

    public void registerScreen(String name, Displayable screen){
        screens.put(name, screen);
    }

    public Displayable getScreen(String name){
        return (Displayable) screens.get(name);
    }





}
