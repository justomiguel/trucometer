/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmv.models.screenstyles.generic;

import com.jmv.models.Phone;
import com.jmv.uicomponents.canvas.CanvasLine;
import com.jmv.uicomponents.buttons.UIButton;
import com.jmv.uicomponents.canvas.CanvasImage;
import com.jmv.uicomponents.canvas.CanvasString;
import com.jmv.utils.Item;

/**
 *
 * @author justo.vargas
 */
public class NG320x240 extends Phone {

    public NG320x240() {
        super();
        fosforosPositionU = new Item(15, 86);
        fosforosPositionR = new Item(135, 86);

        // revisar esto se deberia de hacer mejor
        upFosforoHeight = 40;
        upFosforoWidth = 4;
        downFosforoHeight = 4;
        downFosforoWidth = 40;
        diagFosforoHeight = 39;
        diagFosforoWidth = 37;

        fosforoDIAG = "/diag.png";
        fosforoUP = "/up.png";
        separate = 13;
        myBackGround = "/gameBackground320.png";

        mainTitleHeight = 39;
        mainTitleWidth = 199;

    }

    protected CanvasLine[] getLinesPosition() {
        CanvasLine[] result = {new CanvasLine(11, 22, 27), new CanvasLine(42, 22, 159), new CanvasLine(207, 22, 27),
            new CanvasLine(11, 26, 27), new CanvasLine(42, 26, 159), new CanvasLine(207, 26, 27),
            new CanvasLine(11, 176, 27), new CanvasLine(42, 176, 159), new CanvasLine(207, 176, 27),
            new CanvasLine(11, 180, 27), new CanvasLine(42, 180, 159), new CanvasLine(207, 180, 27),
            new CanvasLine(11, 295, 27), new CanvasLine(42, 295, 159), new CanvasLine(207, 295, 27),
            new CanvasLine(11, 299, 27), new CanvasLine(42, 299, 159), new CanvasLine(207, 299, 27)};
        return result;
    }

    protected CanvasImage getMainTitle() {
        return new CanvasImage(22, 53, "/mainTitle240.png");
    }

    protected CanvasString[] getStringsPosition() {
        CanvasString[] result = {new CanvasString(87, 189, "Play"), new CanvasString(87, 231, "Help"), new CanvasString(87, 270, "Exit")};
        return result;
    }

    protected UIButton[] getButtonsPosition() {
        UIButton[] result = {new UIButton(40, 187, 159, 32, 0x585858, 0xA4A4A4), new UIButton(40, 223, 159, 32, 0x585858, 0xA4A4A4), new UIButton(40, 259, 159, 32, 0x585858, 0xA4A4A4)};
        return result;
    }

    protected UIButton[] getGameButtonsPosition() {
       UIButton[] result = {new UIButton(0,90,50,11,0x009900,0xFFFF99),
       // separaion de canchas
       new UIButton(0,26,50,50,0x009900,0xFFFF99),
       new UIButton(50,26,50,50,0x009900,0xFFFF99)};
        return result;
    }

    protected int[] getVerticalGamesLinesPosition() {
        int[] result = {19};
        return result;
    }

    protected int[] getHorizontalGamesLinesPosition() {
        int[] result = {13,19,26,75,76,89,90};
        return result;
    }

    protected CanvasImage getGameMainTitle() {
        return new CanvasImage(22, -2, "/titulo240.png");
    }

    protected CanvasString[] getGamesStringsPosition() {
        CanvasString[] result = {
            new CanvasString(0, 14, "Beta version"),
            new CanvasString(4, 20, "USR"),
            new CanvasString(53, 20, "Rival"),
            //string de abajo
            new CanvasString(6, 80, "Partidos"),
            // contador de partido
            new CanvasString(55, 77, "usr"),
            new CanvasString(55, 83, "rival"),
            new CanvasString(17, 93, "Atras")};
        return result;
    }

    protected Item[] getSeparacionEntreFosforos() {
        // the distance between the fosforos
        Item[] result = {new Item(42, 42)};
        return result;
    }

    protected UIButton[] getOptionsButtons() {
        // the textfields on the screen
        UIButton[] result = {
            new UIButton(21, 32, 71, 8, 0xFFFFFF, 0xFFFFFF),
            new UIButton(21, 53, 71, 8, 0xFFFFFF, 0xFFFFFF),
            new UIButton(0, 92, 50, 8, 0xFFFFFF, 0x733213),
            new UIButton(50, 92, 50, 8, 0xFFFFFF, 0x733213)
        };
        return result;
    }

    protected CanvasImage getOptionsImg() {
        return new CanvasImage(0, 0, "/opciones.png");
    }

    protected CanvasString[] getOptionsStrings() {
        CanvasString[] result = {
            new CanvasString(44, 33, "teamUsr"),
            new CanvasString(44, 55, "teamRival"),
        };
        return result;
    }
}
