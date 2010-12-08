package com.jmv;

import com.jmv.screens.GameCounterCanvas;
import com.jmv.screens.Menu;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * Demo MIDlet creates, runs and displays GameCanvas.
 *
 * @author  Justo Vargas
 * @version 1.0
 */
public class GameMidlet extends MIDlet {

    private Menu menu;
    private Thread t;
    public Display d;



    public void startApp() {
        this.menu = new Menu(this);
        t = new Thread(menu);
        t.start();
        d = Display.getDisplay(this);
        d.setCurrent(menu);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        //this.gameCanvas.stop();
        this.notifyDestroyed();
    }
}
