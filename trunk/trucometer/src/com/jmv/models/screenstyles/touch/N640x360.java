/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.models.screenstyles.touch;

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
public class N640x360 extends Phone {

    public N640x360() {
        super();
        fosforosPositionU = new Item(15, 110);
        fosforosPositionR = new Item(180, 110);

        // revisar esto se deberia de hacer mejor
        upFosforoHeight = 90;
        upFosforoWidth = 15;
        downFosforoHeight = 12;
        downFosforoWidth = 90;
        diagFosforoHeight = 88;
        diagFosforoWidth = 86;

        fosforoDIAG = "/diag.png";
        fosforoDOWN = "/down.png";
        fosforoUP = "/up.png";
        separate = 35;

        mainTitleHeight = 39;
        mainTitleWidth = 199;

        fosforosUnderX = 250;
        fosforosUnderY = 600;

    }

    protected CanvasLine[] getLinesPosition() {
       CanvasLine[] result = { new CanvasLine(20,107,40),new CanvasLine(67,107,232), new CanvasLine(307,107,40),
                         new CanvasLine(20,111,40) ,new CanvasLine(67,111,232), new CanvasLine(307,111,40),
                         new CanvasLine(20,340,40),new CanvasLine(67,340,232), new CanvasLine(307,340,40),
                         new CanvasLine(20,344,40),new CanvasLine(67,344,232), new CanvasLine(307,344,40),
                         new CanvasLine(20,520,40),new CanvasLine(67,520,232), new CanvasLine(307,520,40),
                         new CanvasLine(20,524,40),new CanvasLine(67,524,232), new CanvasLine(307,524,40)};
        return result;
    }

    protected CanvasImage getMainTitle() {
        return new CanvasImage(39, 154, "/mainTitle240.png");
    }

    protected CanvasString[] getStringsPosition() {
         CanvasString[] result = {new CanvasString(80,373, "Play"),new CanvasString(80,420, "Help"), new CanvasString(80,468, "Exit")};;;
        return result;
    }

    protected UIButton[] getButtonsPosition() {
        UIButton[] result = {new UIButton(63,362,228,44,0x585858,0xD8D8D8),new UIButton(63,413,228,44,0x585858,0xD8D8D8), new UIButton(63,463,228,44,0x585858,0xD8D8D8)};
        return result;
    }

    protected UIButton[] getGameButtonsPosition() {
       UIButton[] result = {new UIButton(10,600,129,35,0x009900,0xFFFF99),
       // separaion de canchas
       new UIButton(0,95,165,430,0x009900,0xFFFF99),
       new UIButton(165,95,165,430,0x009900,0xFFFF99)};
        return result;
    }

    protected int[] getVerticalGamesLinesPosition() {
        int[] result = {13,19,
                        // ABAJO
                        26,75,76,89,90};
        return result;
    }

    protected int[] getHorizontalGamesLinesPosition() {
        int[] result = {13,19,
                        // ABAJO
                        26,75,76,89,90};
        return result;
    }

    protected CanvasImage getGameMainTitle() {
        return new CanvasImage(55, -2, "/titulo240.png");
    }

    protected CanvasString[] getGamesStringsPosition() {
        CanvasString[] result = {
            new CanvasString(3, 50, "Beta version"),
            new CanvasString(7, 76, "USR"),
            new CanvasString(175, 76, "Rival"),
            //string de abajo
            new CanvasString(30, 545, "Partidos"),
            // contador de partido
            new CanvasString(195, 534, "usr"),
            new CanvasString(195, 565, "rival")};
        return result;
    }

    protected Item[] getSeparacionEntreFosforos() {
        // the distance between the fosforos
        Item[] result = {new Item(90,90)};
        return result;
    }

    protected UIButton[] getOptionsButtons() {
        // the textfields on the screen
        UIButton[] result = {
            new UIButton(75, 199, 240, 48, 0xFFFFFF, 0xFFFFFF),
            new UIButton(75, 340, 240, 48, 0xFFFFFF, 0xFFFFFF),
            new UIButton(0, 590, 120, 50, 0xFFFFFF, 0x733213),
            new UIButton(230, 590, 120, 50, 0xFFFFFF, 0x733213)
        };
        return result;
    }

    protected CanvasImage getOptionsImg() {
        return new CanvasImage(0, 0, "/opciones.png");
    }

    protected CanvasString[] getOptionsStrings() {
        CanvasString[] result = {
            new CanvasString(3, 42, "teamUsr"),
            new CanvasString(7, 60, "teamRival"),
        };
        return result;
    }


}
