/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.globant.utils;

/**
 *
 * @author justo.vargas
 */
public class Item {

    public int x;
    public int y;
    public String name;
    public int largo;
    public int color;
    public int colorSeleccionado;
    public int ancho;
    public boolean  seleccionado = false;

    public Item(int x, int y, int ancho, int largo, int color, int coloSeleccionado) {
        this.x = x;
        this.y = y;
        this.largo = largo;
        this.ancho = ancho;
        this.color = color;
        this.colorSeleccionado = coloSeleccionado;
    }

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Item(int x, int y, int largo) {
        this.x = x;
        this.y = y;
        this.largo = largo;
    }

    public Item(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }


}
