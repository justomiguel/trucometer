/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.models.screenstyles.generic;

import com.jmv.models.Phone;
import com.jmv.utils.Item;

/**
 *
 * @author justo.vargas
 */
public class N320x240 extends Phone {

    public N320x240() {
        super();
        fosforosPositionU = new Item(15, 90);
        fosforosPositionR = new Item(135, 90);
        fosforoDIAG = "/diag.png";
        fosforoDOWN = "/down.png";
        fosforoUP = "/up.png";
        separate = 10;
    }



    protected Item[] getLinesPosition() {
       Item[] result = { new Item(11,22,27),new Item(42,22,159), new Item(207,22,27),
                                      new Item(11,26,27),new Item(42,26,159), new Item(207,26,27),
                                      new Item(11,176,27),new Item(42,176,159), new Item(207,176,27),
                                      new Item(11,180,27),new Item(42,180,159), new Item(207,180,27),
                                      new Item(11,295,27),new Item(42,295,159), new Item(207,295,27),
                                      new Item(11,299,27),new Item(42,299,159), new Item(207,299,27)};
        return result;
    }

    protected Item getMainTitle() {
        return new Item(22, 53, "/mainTitle240.png");
    }

    protected Item[] getStringsPosition() {
        Item[] result = {new Item(87,189, "Play"),new Item(87,231, "Help"), new Item(87,270, "Exit")};
        return result;
    }

    protected Item[] getButtonsPosition() {
        Item[] result = {new Item(40,187,159,32,0x585858,0xA4A4A4),new Item(40,223,159,32,0x585858,0xA4A4A4), new Item(40,259,159,32,0x585858,0xA4A4A4)};
        return result;
    }


    protected Item[] getGButtonsPosition() {
        Item[] result = {new Item(1,236,59,38,0x009900,0xCCFFCC),new Item(59,236,62,38,0x009900,0xCCFFCC), new Item(121,236,59,38,0x009900,0xCCFFCC),new Item(178,236,62,38,0x009900,0xCCFFCC)};
        return result;
    }

    protected Item[] getGLinesPosition() {
        Item[] result = {new Item(120,59,266),new Item(0,42,240),new Item(0,59,240),new Item(0,79,240), new Item(0,232,240), new Item(0,236,240),new Item(0,275,240),new Item(0,278,240)};
        return result;
    }

    protected Item getGMainTitle() {
       return new Item(22, -2, "/titulo240.png");
    }

    protected Item[] getGStringsPosition() {
        Item[] result = {new Item(3,42, "Beta version"),new Item(7,60, "USR"), new Item(125,60, "Rival")};;;
        return result;
    }

    protected Item[] getFosforosPositionUnder() {
        Item[] result = {new Item(29,34)};
        return result;
    }

}
