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

        // define the screens
        this.screenManager.registerScreen(GameMidlet.SCREEN_MENU, new Menu());
        this.screenManager.registerScreen(GameMidlet.SCREEN_OPT, new UIOptions());
        this.screenManager.registerScreen(GameMidlet.SCREEN_GAME, new GameCounterCanvas());
        
        screenManager.changeScreen(SCREEN_MENU);
    }

    public void startApp() {
        super.startApp();
    }

    public void pauseApp() {
        super.pauseApp();
    }

    public void dispose() {
        super.dispose();
    }

}
