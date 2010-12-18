/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.models.screenstyles.touch;

import com.jmv.models.screenstyles.generic.*;
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
public class NT320x240 extends Phone {

    public NT320x240() {
        super();
        fosforosPositionU = new Item(15, 94);
        fosforosPositionR = new Item(135, 94);

        // revisar esto se deberia de hacer mejor
        upFosforoHeight = 38;
        upFosforoWidth = 3;
        downFosforoHeight = 3;
        downFosforoWidth = 38;
        diagFosforoHeight = 34;
        diagFosforoWidth = 33;

        fosforoDIAG = "/diag.png";
        fosforoDOWN = "/down.png";
        fosforoUP = "/up.png";
        separate = 12;
        myBackGround = "/gameBackground320.png";

        mainTitleHeight = 39;
        mainTitleWidth = 199;

    }



    protected CanvasLine[] getLinesPosition() {
       CanvasLine[] result = { new CanvasLine(11,22,27),new CanvasLine(42,22,159), new CanvasLine(207,22,27),
                                      new CanvasLine(11,26,27),new CanvasLine(42,26,159), new CanvasLine(207,26,27),
                                      new CanvasLine(11,176,27),new CanvasLine(42,176,159), new CanvasLine(207,176,27),
                                      new CanvasLine(11,180,27),new CanvasLine(42,180,159), new CanvasLine(207,180,27),
                                      new CanvasLine(11,295,27),new CanvasLine(42,295,159), new CanvasLine(207,295,27),
                                      new CanvasLine(11,299,27),new CanvasLine(42,299,159), new CanvasLine(207,299,27)};
        return result;
    }

    protected CanvasImage getMainTitle() {
        return new CanvasImage(22, 53, "/mainTitle240.png");
    }

    protected CanvasString[] getStringsPosition() {
        CanvasString[] result = {new CanvasString(87,189, "Play"),new CanvasString(87,231, "Help"), new CanvasString(87,270, "Exit")};
        return result;
    }

    protected UIButton[] getButtonsPosition() {
        UIButton[] result = {new UIButton(40,187,159,32,0x585858,0xA4A4A4),new UIButton(40,223,159,32,0x585858,0xA4A4A4), new UIButton(40,259,159,32,0x585858,0xA4A4A4)};
        return result;
    }


    protected UIButton[] getGameButtonsPosition() {
        UIButton[] result = {new UIButton(1,244,59,38,0x009900,0xFFFF99),new UIButton(59,244,62,38,0x009900,0xFFFF99), new UIButton(121,244,59,38,0x009900,0xFFFF99),new UIButton(178,244,62,38,0x009900,0xFFFF99)};
        return result;
    }

    protected int[] getVerticalGamesLinesPosition() {
        int[] result = {13,19,
                        // ABAJO
                        26,75,76,89,90};
        return result;
    }

    protected CanvasImage getGameMainTitle() {
       return new CanvasImage(22, -2, "/titulo240.png");
    }

    protected CanvasString[] getGamesStringsPosition() {
        CanvasString[] result = {new CanvasString(3,42, "Beta version"),new CanvasString(7,60, "USR"), new CanvasString(125,60, "Rival")};;;
        return result;
    }

    protected Item[] getSeparacionEntreFosforos() {
        // the distance between the fosforos
        Item[] result = {new Item(42,42)};
        return result;
    }

    protected int[] getHorizontalGamesLinesPosition() {
         int[] result = {13,19,
                        // ABAJO
                        26,75,76,89,90};
        return result;
    }

    protected UIButton[] getOptionsButtons() {
        return null;
    }

    protected CanvasImage getOptionsImg() {
        return null;
    }

    protected CanvasString[] getOptionsStrings() {
        return null;
    }

}
