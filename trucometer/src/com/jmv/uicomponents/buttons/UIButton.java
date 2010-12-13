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

    public int x;
    public int y;
    public int largo;
    public int color;
    public int colorSeleccionado;
    public int ancho;
    public boolean  seleccionado = false;
    private String name;

    public UIButton(int x, int y, int ancho, int largo, int color, int colorSeleccionado) {
        this.x = x;
        this.y = y;
        this.largo = largo;
        this.color = color;
        this.colorSeleccionado = colorSeleccionado;
        this.ancho = ancho;
    }

    public UIButton(int x, int y, int ancho, int largo, int color, int colorSeleccionado, String string) {
        this.x = x;
        this.y = y;
        this.largo = largo;
        this.color = color;
        this.colorSeleccionado = colorSeleccionado;
        this.ancho = ancho;
        this.name = string;
    }



}
