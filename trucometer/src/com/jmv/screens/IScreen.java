/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.screens;

/**
 *
 * @author Miguel
 */
public interface IScreen {

    public void changeScreen(String newScreen);
    public void initScreen(String backScreen);
    public void dispose();
   
}
