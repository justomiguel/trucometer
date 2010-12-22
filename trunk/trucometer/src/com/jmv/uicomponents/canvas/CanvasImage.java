/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.uicomponents.canvas;

/**
 *
 * @author Miguel
 */
public class CanvasImage {

    public int x;
    public int y;

    public int preferedHeight;
    public int preferedWidth;

    public String name;

    public CanvasImage(int x, int y, String name, int height, int width) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.preferedHeight = height;
        this.preferedWidth = width;
    }

    public CanvasImage(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.preferedHeight = -1;
        this.preferedWidth = -1;
    }

}
