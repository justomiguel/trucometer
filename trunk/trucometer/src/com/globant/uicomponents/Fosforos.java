/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.uicomponents;

import javax.microedition.lcdui.Image;

/**
 *
 * @author justo.vargas
 */
public class Fosforos {

    private Image img;
    private Image shadow;
    private boolean on;

    public Fosforos(Image img, boolean isOn) {
        this.img = img;
        this.on = isOn;
    }

    public Image getShadow() {
        return shadow;
    }

    public void setShadow(Image shadow) {
        this.shadow = shadow;
    }

    public boolean isOn() {
        return on&&(img!=null);
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public Fosforos() {
        this.img = null;
        this.on = false;
    }

    public int getWidth() {
        return img.getWidth();
    }

    public int getHeight() {
        return img.getHeight();
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }


    public String toString() {
        return "Fosforos{" + "img=" + img + "isOn=" + on + '}';
    }





}
