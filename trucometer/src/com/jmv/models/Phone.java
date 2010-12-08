/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.models;

import com.jmv.utils.Item;

/**
 *
 * @author justo.vargas
 */
public abstract class Phone {

    public int alto;
    public int ancho;

    public Item m_titlePosition;

    public Item[] m_linesPosition;
    
    public Item[] m_stringsPosition;

    public Item[] m_botones;


    public Item[] g_linesPosition;
    public Item[] g_stringsPosition;
    public Item[] g_botones;
    public Item g_titlePosition;

    public Item fosforosPositionU;
    public Item fosforosPositionR;

    public String fosforoUP;
    public String fosforoDOWN;
    public String fosforoDIAG;

    public int separate;

    public Item[] g_fosForosPositionUnder;

    public Phone() {
        m_linesPosition = getLinesPosition();
        m_stringsPosition = getStringsPosition();
        m_titlePosition = getMainTitle();
        m_botones = getButtonsPosition();
        g_linesPosition = getGLinesPosition();
        g_stringsPosition = getGStringsPosition();
        g_titlePosition = getGMainTitle();
        g_botones = getGButtonsPosition();
        g_fosForosPositionUnder = getFosforosPositionUnder();

    }

    protected abstract Item[] getLinesPosition();
    protected abstract Item[] getStringsPosition();
    protected abstract Item getMainTitle();
    protected abstract Item[] getButtonsPosition();

    protected Item[] getGLinesPosition() {
        return null;
    }

    protected Item[] getGStringsPosition() {
        return null;
    }

    protected Item getGMainTitle() {
        return null;
    }

    protected Item[] getGButtonsPosition() {
        return null;
    }

    protected Item[] getFosforosPositionUnder() {
        return null;
    }
}
