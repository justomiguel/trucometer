/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.screens;

import com.globant.GameMidlet;
import com.globant.models.Phone;
import com.globant.models.screenstyles.generic.NG320x240;
import com.globant.uicomponents.buttons.SingleButton;
import com.globant.utils.ImageUtil;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author justo.vargas
 */
public class Menu extends Screen {

    // main image
    private Image mainTitle = null;

    // an int to know the current selection
    private int selectedOption = 1;

    // the game to lunch
    private GameCounterCanvas gameCanvas;

    // preferences
    private UIOptions preferences;

    // if the phone is a touch one this var is going to be true
    private boolean isTactil;

    // configuration file
    private Phone mobile;

    public Menu() {
        setFullScreenMode(true);
        isTactil = hasPointerEvents();
        defineModel();
        setAnimations();
        getImages();
    }

    public void init() {
    }

    protected void paint(Graphics g) {
        // dibujo la imagen del titulo
        g.drawImage(mainTitle, mobile.menu_backGround.x, mobile.menu_backGround.y, Graphics.TOP | Graphics.LEFT);

        g.setColor(0xFFFFFF);
        // dibujo recuadros interiores
        for (int i = 0; i < mobile.menu_botones.length; i++) {
            SingleButton object = mobile.menu_botones[i];
            if (object.seleccionado) {
                g.setColor(object.colorSeleccionado);
            } else {
                g.setColor(object.color);
            }
            g.setStrokeStyle(1);
            g.drawRect(object.xPercent, object.yPercent, object.anchoPercent, object.largoPercent);
        }

    }

    public void reset() {
        for (int i = 0; i < mobile.menu_botones.length; i++) {
            SingleButton object = mobile.menu_botones[i];
            if (i != 0) {
                object.seleccionado = false;
            } else {
                object.seleccionado = true;
            }
        }
    }

    private void getImages() {
        try {
            mainTitle = Image.createImage(mobile.menu_backGround.name);
            mainTitle = ImageUtil.resizeImage(mainTitle, this.getWidth(), this.getHeight(), 2);
        } catch (IOException ex) {
            System.out.println("No carga imagen");
        }
    }

    protected void keyReleased(int keyCode) {
        int gameAction = getGameAction(keyCode);

        switch (gameAction) {
            case UP:
                if (selectedOption > 1) {
                    selectedOption--;
                    for (int i = 0; i < mobile.menu_botones.length; i++) {
                        SingleButton object = mobile.menu_botones[i];
                        if (i == selectedOption - 1) {
                            object.seleccionado = true;
                        } else {
                            object.seleccionado = false;
                        }
                    }
                }
                break;
            case DOWN:
                if (selectedOption < 3) {
                    selectedOption++;
                    for (int i = 0; i < mobile.menu_botones.length; i++) {
                        SingleButton object = mobile.menu_botones[i];
                        if (i == selectedOption - 1) {
                            object.seleccionado = true;
                        } else {
                            object.seleccionado = false;
                        }
                    }
                }
                break;
            case FIRE:
                goToScreen();
                break;
        }
        repaint();
    }

    private void goToScreen() {
        switch (selectedOption) {
            case 1:
                //preferences = new Preferences(this, midletInstance);
                GameMidlet.instance.changeScreen(ScreenManager.SCREEN_OPT);
                break;
            case 2:
                break;
            case 3:
                 GameMidlet.instance.destroyApp(true);
                break;
        }
    }

    private void setAnimations() {
        // for default select the first option
        SingleButton object = mobile.menu_botones[0];
        object.seleccionado = true;
    }

    private void defineModel() {
        
        mobile = new NG320x240();
        // update the configuration var
        GameMidlet.instance.mobile = mobile;

        // set the buttons according to this screen;
        int ancho = this.getWidth();
        int alto = this.getHeight();

        if (isTactil) {
            SingleButton button;
            for (int i = 0; i < mobile.menu_botones.length; i++) {
                button = mobile.menu_botones[i];
                button.updateSize(alto, ancho);
            }
        }
    }

    public void run() {
    }


    // pointer events
    protected void pointerPressed(int x, int y) {
        if (mobile.menu_botones[0].hitTestPoint(x, y)) {
            //lamo al juego
            mobile.menu_botones[0].seleccionado = true;
        } else if (mobile.menu_botones[2].hitTestPoint(x, y)) {
            mobile.menu_botones[2].seleccionado = true;
        }
        repaint();
    }

    protected void pointerReleased(int x, int y) {
        if (mobile.menu_botones[0].hitTestPoint(x, y)) {
            mobile.menu_botones[0].seleccionado = false;
            //lamo al juego
            GameMidlet.instance.changeScreen(ScreenManager.SCREEN_OPT);
        } else if (mobile.menu_botones[2].hitTestPoint(x, y)) {
             GameMidlet.instance.destroyApp(true);
        }
        repaint();

    }

    // kill this class
        public void dispose() {
        gameCanvas = null;
        preferences = null;
        mobile = null;
    }
}
