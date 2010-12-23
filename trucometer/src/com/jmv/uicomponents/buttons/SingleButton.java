/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmv.uicomponents.buttons;

import com.jmv.uicomponents.IScreenElement;

/**
 *
 * @author Miguel
 */
public class SingleButton implements IScreenElement {

    public int xPercent;
    public int yPercent;
    public int largoPercent;
    public int color;
    public int colorSeleccionado;
    public int anchoPercent;
    public boolean seleccionado = false;
    private String name;

    public SingleButton(int x, int y, int ancho, int largo, int color, int colorSeleccionado) {
        this.xPercent = x;
        this.yPercent = y;
        this.largoPercent = largo;
        this.color = color;
        this.colorSeleccionado = colorSeleccionado;
        this.anchoPercent = ancho;
    }

    public SingleButton(int x, int y, int ancho, int largo, int color, int colorSeleccionado, String string) {
        this.xPercent = x;
        this.yPercent = y;
        this.largoPercent = largo;
        this.color = color;
        this.colorSeleccionado = colorSeleccionado;
        this.anchoPercent = ancho;
        this.name = string;
    }

    public boolean hitTestPoint(int x, int y) {
        boolean hitOnX = ((x > this.xPercent) && (x < (this.xPercent + this.anchoPercent)));
        boolean hitOnY = ((y > this.yPercent) && (y < (this.yPercent + this.largoPercent)));
        return hitOnX && hitOnY;
    }

    public void updateSize(int screenHeight, int screenWidth) {
        this.xPercent = this.xPercent * screenWidth / 100;
        this.yPercent = this.yPercent * screenHeight / 100;
        this.anchoPercent = this.anchoPercent * screenWidth / 100;
        this.largoPercent = this.largoPercent * screenHeight / 100;
    }
}
