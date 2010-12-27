/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.globant.uicomponents;

import java.util.TimerTask;

/**
 *
 * @author justo.vargas
 */
public class Marquesina extends TimerTask {

    private String content;
    private int x;
    private int y;

    public Marquesina(String content, int x, int y) {
        this.content = content;
        this.x = x;
        this.y = y;
    }

    public Marquesina(String content) {
        this.content = content;
        this.x = this.y = 0;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void run() {
        x++;
        if (x>240){
            x = - (content.length()*4);
        }
    }



}
