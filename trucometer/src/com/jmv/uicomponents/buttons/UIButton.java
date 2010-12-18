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
public class UIButton implements IScreenElement {

    public int xPercent;
    public int yPercent;
    public int largoPercent;
    public int color;
    public int colorSeleccionado;
    public int anchoPercent;
    public boolean  seleccionado = false;
    private String name;

    public UIButton(int x, int y, int ancho, int largo, int color, int colorSeleccionado) {
        this.xPercent = x;
        this.yPercent = y;
        this.largoPercent = largo;
        this.color = color;
        this.colorSeleccionado = colorSeleccionado;
        this.anchoPercent = ancho;
    }

    public UIButton(int x, int y, int ancho, int largo, int color, int colorSeleccionado, String string) {
        this.xPercent = x;
        this.yPercent = y;
        this.largoPercent = largo;
        this.color = color;
        this.colorSeleccionado = colorSeleccionado;
        this.anchoPercent = ancho;
        this.name = string;
    }



}
