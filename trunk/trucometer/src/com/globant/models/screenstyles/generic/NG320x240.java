/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.models.screenstyles.generic;

import com.globant.models.Phone;
import com.globant.uicomponents.canvas.CanvasLine;
import com.globant.uicomponents.buttons.SingleButton;
import com.globant.uicomponents.canvas.CanvasImage;
import com.globant.uicomponents.canvas.CanvasString;
import com.globant.utils.Item;

/**
 *
 * @author justo.vargas
 */
public class NG320x240 extends Phone {

    public NG320x240() {
        super();
        fosforosPositionU = new Item(13, 28);
        fosforosPositionR = new Item(65, 28);

        // revisar esto se deberia de hacer mejor
        upFosforoHeight = 13;
        upFosforoWidth = 2;
        downFosforoHeight = 2;
        downFosforoWidth = 17;
        diagFosforoHeight = 13;
        diagFosforoWidth = 15;

        fosforoDIAG = "/diag.png";
        fosforoUP = "/up.png";
        separate = 4;
        myBackGround = "/gameBackground320.png";

        mainTitleHeight = 39;
        mainTitleWidth = 199;

    }

    protected SingleButton[] getButtonsPosition() {
        SingleButton[] result = {
            new SingleButton(30, 68, 40, 8, 0x585858, 0xA4A4A4),
            new SingleButton(30, 77, 40, 8, 0x585858, 0xA4A4A4),
            new SingleButton(30, 86, 40, 8, 0x585858, 0xA4A4A4)
        };
        return result;
    }

    protected SingleButton[] getGameButtonsPosition() {
       SingleButton[] result = {new SingleButton(0,90,50,11,0x009900,0xFFFF99),
       // separaion de canchas
       new SingleButton(0,26,50,50,0x009900,0xFFFF99),
       new SingleButton(50,26,50,50,0x009900,0xFFFF99)};
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
        return new CanvasImage(9, 3, "/titulo240.png",9,79);
    }

    protected CanvasString[] getGamesStringsPosition() {
        CanvasString[] result = {
            new CanvasString(0, 14, "Beta version"),
            new CanvasString(4, 20, "USR"),
            new CanvasString(53, 20, "Rival"),
            //string de abajo
            new CanvasString(6, 80, "Partidos"),
            // contador de partido
            new CanvasString(54, 77, "usr"),
            new CanvasString(54, 83, "rival"),
            new CanvasString(17, 93, "Atras")};
        return result;
    }

    protected Item[] getSeparacionEntreFosforos() {
        // the distance between the fosforos
        Item[] result = {new Item(17, 14)};
        return result;
    }

    protected SingleButton[] getOptionsButtons() {
        // the textfields on the screen
        SingleButton[] result = {
            new SingleButton(21, 32, 71, 8, 0xFFFFFF, 0xFFFFFF),
            new SingleButton(21, 53, 71, 8, 0xFFFFFF, 0xFFFFFF),
            new SingleButton(0, 92, 50, 8, 0xFFFFFF, 0x733213),
            new SingleButton(50, 92, 50, 8, 0xFFFFFF, 0x733213)
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

    protected CanvasImage getInitialBackGround() {
         return new CanvasImage(0, 0, "/intro.png");
    }
}
