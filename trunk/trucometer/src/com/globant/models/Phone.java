/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.globant.models;

import com.globant.gj2framework.screens.uielements.SingleButton;
import com.globant.gj2framework.screens.uielements.CanvasImage;
import com.globant.gj2framework.screens.uielements.CanvasString;
import com.globant.utils.Item;

/**
 *
 * @author justo.vargas
 */
public abstract class Phone {

    public int alto;
    public int ancho;

    public CanvasImage menu_backGround;
    public SingleButton[] menu_botones;

    public CanvasImage opciones_imgPosition;
    public CanvasString[] opciones_strings;
    public SingleButton[] opciones_botones;

    public int[] game_verticalLinesPosition;
    public int[] game_HorizontalLinesPosition;
    public CanvasString[] game_stringsPosition;
    public SingleButton[] game_botones;
    public CanvasImage game_titlePosition;

    public Item fosforosPositionU;
    public Item fosforosPositionR;

    public String fosforoUP;
    public String fosforoDOWN;
    public String fosforoDIAG;

    public int separate;

    public int upFosforoWidth;
    public int upFosforoHeight;

    public int downFosforoWidth;
    public int downFosforoHeight;

    public int diagFosforoWidth;
    public int diagFosforoHeight;

    public int mainTitleWidth;
    public int mainTitleHeight;

    public String defaultBackGround;
    public String myBackGround;
    
    public Item[] g_fosForosPositionUnder;

    public  int fosforosUnderX;
    public  int fosforosUnderY;

    public Phone() {

        menu_backGround = getInitialBackGround();
        menu_botones = getButtonsPosition();

        opciones_botones = getOptionsButtons();
        opciones_imgPosition = getOptionsImg();
        opciones_strings = getOptionsStrings();

        game_verticalLinesPosition = getVerticalGamesLinesPosition();
        game_HorizontalLinesPosition = getHorizontalGamesLinesPosition();
        game_stringsPosition = getGamesStringsPosition();
        game_titlePosition = getGameMainTitle();
        game_botones = getGameButtonsPosition();

        g_fosForosPositionUnder = getSeparacionEntreFosforos();

        defaultBackGround = "/gameBackground.png";
        myBackGround = "";
        fosforosUnderX = 180;
        fosforosUnderY = 300;

    }

    
    protected abstract CanvasImage getInitialBackGround();
    protected abstract SingleButton[] getButtonsPosition();

    protected abstract int[] getVerticalGamesLinesPosition();
    protected abstract int[] getHorizontalGamesLinesPosition();
    protected abstract CanvasString[] getGamesStringsPosition();
    protected abstract CanvasImage getGameMainTitle();
    protected abstract SingleButton[] getGameButtonsPosition();

    protected abstract Item[] getSeparacionEntreFosforos();

    protected abstract SingleButton[] getOptionsButtons();
    protected abstract CanvasImage getOptionsImg();
    protected abstract CanvasString[] getOptionsStrings();

}
