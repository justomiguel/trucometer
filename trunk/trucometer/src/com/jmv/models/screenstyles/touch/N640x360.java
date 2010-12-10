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
        fosforosPositionU = new Item(30, 160);
        fosforosPositionR = new Item(205, 160);
        fosforoDIAG = "/diag360.png";
        fosforoDOWN = "/down360.png";
        fosforoUP = "/up360.png";
        separate = 15; 

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
        return new CanvasImage(39, 154, "/mainTitle360.png");
    }

    protected UIButton[] getGameButtonsPosition() {
        UIButton[] result = {new UIButton(10,500,75,65,0x009900,0xCCFFCC),new UIButton(96,500,75,65,0x009900,0xCCFFCC), new UIButton(188,500,75,65,0x009900,0xCCFFCC),new UIButton(274,500,75,65,0x009900,0xCCFFCC)};
        return result;
    }

    protected CanvasLine[] getVerticalGamesLinesPosition() {
        CanvasLine[] result = {new CanvasLine(180,105,533),new CanvasLine(5,85,349),new CanvasLine(5,105,349), new CanvasLine(5,495,349), new CanvasLine(5,135,349)};
        return result;
    }

    protected CanvasImage getGameMainTitle() {
       return new CanvasImage(17, 7, "/titulo.png");
    }

    protected CanvasString[] getGamesStringsPosition() {
        CanvasString[] result = {new CanvasString(2,85, "Beta version"),new CanvasString(11,107, "USR"), new CanvasString(185,107, "Rival")};;;
        return result;
    }

    protected CanvasString[] getStringsPosition() {
        CanvasString[] result = {new CanvasString(80,373, "Play"),new CanvasString(80,420, "Help"), new CanvasString(80,468, "Exit")};;;
        return result;
    }

    protected UIButton[] getButtonsPosition() {
        UIButton[] result = {new UIButton(63,362,228,44,0x585858,0xD8D8D8),new UIButton(63,413,228,44,0x585858,0xD8D8D8), new UIButton(63,463,228,44,0x585858,0xD8D8D8)};
        return result;
    }

    protected Item[] getSeparacionEntreFosforos() {
        Item[] result = {new Item(45,45)};
        return result;
    }

    protected CanvasLine[] getHorizontalGamesLinesPosition() {
        return null;
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
