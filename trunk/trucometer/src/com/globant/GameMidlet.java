package com.globant;

import com.globant.gj2framework.base.BaseMidlet;
import com.globant.screens.GameCounterCanvas;
import com.globant.screens.Menu;
import com.globant.screens.UIOptions;

/**
 * Demo MIDlet creates, runs and displays GameCanvas.
 *
 * @author  Justo Vargas
 * @version 1.0
 */
public class GameMidlet extends BaseMidlet {

    // game screens
    public static final String SCREEN_MENU = "menu";
    public static final String SCREEN_OPT = "opciones";
    public static final String SCREEN_GAME = "game";

    public GameMidlet() {
        super();
        // set the instance to this
        instance = this;
    }

    public void startApp() {

        if (!screenManager.initialized) {
            // define the screens
            loaderScreen.setProgress(99, 2);

            this.screenManager.registerScreen(GameMidlet.SCREEN_MENU, new Menu());
            this.screenManager.registerScreen(GameMidlet.SCREEN_OPT, new UIOptions());
            this.screenManager.registerScreen(GameMidlet.SCREEN_GAME, new GameCounterCanvas());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println(" sleeping ");
            }
            loaderScreen.setProgress(100, 1);

            screenManager.changeScreen(SCREEN_MENU);
            screenManager.initialized = true;

            killLoader();
        }
        // do nothing come back from the pause state
    }

    public void pauseApp() {
        super.pauseApp();
    }

    public void dispose() {
        super.dispose();
    }


}
