/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.globant.gj2framework.screens;

import javax.microedition.lcdui.Canvas;

/**
 *
 * @author justo.vargas
 */
public abstract class Screen extends Canvas implements Runnable {

    protected boolean isRunning;
    private String name;

    public Screen() {
        isRunning = false;
    }

    public void stop(){
        isRunning = false;
    }

    public void reset(){
        isRunning = true;
    }

    public String getName() {
        return name;
    }

    
    public abstract void init();
    public abstract void dispose();

}
