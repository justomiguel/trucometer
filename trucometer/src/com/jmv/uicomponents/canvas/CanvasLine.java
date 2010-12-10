/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.uicomponents.canvas;

/**
 *
 * @author Miguel
 */
public class CanvasLine {

    public int x;
    public int y;
    public int largo;
    public int color;

    public CanvasLine(int x, int y, int largo, int color) {
        this.x = x;
        this.y = y;
        this.largo = largo;
        this.color = color;
    }

    public CanvasLine(int x, int y, int largo) {
        this.x = x;
        this.y = y;
        this.largo = largo;
        this.color = 0xFFFFFF;
    }
    

}
