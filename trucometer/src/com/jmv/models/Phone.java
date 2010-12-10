/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.models;

import com.jmv.uicomponents.canvas.CanvasLine;
import com.jmv.uicomponents.buttons.UIButton;
import com.jmv.uicomponents.canvas.CanvasImage;
import com.jmv.uicomponents.canvas.CanvasString;
import com.jmv.utils.Item;

/**
 *
 * @author justo.vargas
 */
public abstract class Phone {

    public int alto;
    public int ancho;

    public CanvasImage menu_titlePosition;
    public CanvasLine[] menu_linesPosition;
    public CanvasString[] menu_stringsPosition;
    public UIButton[] menu_botones;

    public CanvasImage opciones_imgPosition;
    public CanvasString[] opciones_strings;
    public UIButton[] opciones_botones;

    public CanvasLine[] game_verticalLinesPosition;
    public CanvasLine[] game_HorizontalLinesPosition;
    public CanvasString[] game_stringsPosition;
    public UIButton[] game_botones;
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

    public Phone() {

        menu_linesPosition = getLinesPosition();
        menu_stringsPosition = getStringsPosition();
        menu_titlePosition = getMainTitle();
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

    }

    
    protected abstract CanvasLine[] getLinesPosition();
    protected abstract CanvasString[] getStringsPosition();
    protected abstract CanvasImage getMainTitle();
    protected abstract UIButton[] getButtonsPosition();

    protected abstract CanvasLine[] getVerticalGamesLinesPosition();
    protected abstract CanvasLine[] getHorizontalGamesLinesPosition();
    protected abstract CanvasString[] getGamesStringsPosition();
    protected abstract CanvasImage getGameMainTitle();
    protected abstract UIButton[] getGameButtonsPosition();

    protected abstract Item[] getSeparacionEntreFosforos();

    protected abstract UIButton[] getOptionsButtons();
    protected abstract CanvasImage getOptionsImg();
    protected abstract CanvasString[] getOptionsStrings();

}
