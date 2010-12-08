/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.models.screenstyles.touch;

import com.jmv.models.Phone;
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

     protected Item[] getLinesPosition() {
       Item[] result = { new Item(20,107,40),new Item(67,107,232), new Item(307,107,40),
                         new Item(20,111,40) ,new Item(67,111,232), new Item(307,111,40),
                         new Item(20,340,40),new Item(67,340,232), new Item(307,340,40),
                         new Item(20,344,40),new Item(67,344,232), new Item(307,344,40),
                         new Item(20,520,40),new Item(67,520,232), new Item(307,520,40),
                         new Item(20,524,40),new Item(67,524,232), new Item(307,524,40)};
        return result;
    }

    protected Item getMainTitle() {
        return new Item(39, 154, "/mainTitle360.png");
    }

    protected Item[] getGButtonsPosition() {
        Item[] result = {new Item(10,500,75,65,0x009900,0xCCFFCC),new Item(96,500,75,65,0x009900,0xCCFFCC), new Item(188,500,75,65,0x009900,0xCCFFCC),new Item(274,500,75,65,0x009900,0xCCFFCC)};
        return result;
    }

    protected Item[] getGLinesPosition() {
        Item[] result = {new Item(180,105,533),new Item(5,85,349),new Item(5,105,349), new Item(5,495,349), new Item(5,135,349)};
        return result;
    }

    protected Item getGMainTitle() {
       return new Item(17, 7, "/titulo.png");
    }

    protected Item[] getGStringsPosition() {
        Item[] result = {new Item(2,85, "Beta version"),new Item(11,107, "USR"), new Item(185,107, "Rival")};;;
        return result;
    }





    protected Item[] getStringsPosition() {
        Item[] result = {new Item(80,373, "Play"),new Item(80,420, "Help"), new Item(80,468, "Exit")};;;
        return result;
    }

    protected Item[] getButtonsPosition() {
        Item[] result = {new Item(63,362,228,44,0x585858,0xD8D8D8),new Item(63,413,228,44,0x585858,0xD8D8D8), new Item(63,463,228,44,0x585858,0xD8D8D8)};
        return result;
    }

    protected Item[] getFosforosPositionUnder() {
        Item[] result = {new Item(45,45)};
        return result;
    }


}
