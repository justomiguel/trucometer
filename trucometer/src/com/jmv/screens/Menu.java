/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmv.screens;

import com.jmv.GameMidlet;
import com.jmv.models.Phone;
import com.jmv.models.screenstyles.generic.NG320x240;
import com.jmv.uicomponents.canvas.CanvasLine;
import com.jmv.uicomponents.Marquesina;
import com.jmv.uicomponents.buttons.UIButton;
import com.jmv.uicomponents.canvas.CanvasString;
import com.jmv.utils.IDestroyable;
import com.jmv.utils.ImageUtil;
import java.io.IOException;
import java.util.Timer;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author justo.vargas
 */
public class Menu extends Canvas implements IDestroyable {

    // main image
    private Image mainTitle = null;
    // the midlet instance
    public GameMidlet midletInstance;
    // an int to know the current selection
    private int selectedOption = 1;
    // the game to lunch
    private GameCounterCanvas gameCanvas;
    // preferences
    //private Preferences preferences;
    private UIOptions preferences;
    // if the phone is a touch one this var is going to be true
    private boolean isTactil;
    // for the images position
    public Phone mobile;

    public Menu(GameMidlet inst) {
        setFullScreenMode(true);
        midletInstance = inst;
        isTactil = hasPointerEvents();
        defineModel();
        setAnimations();
        getImages();
    }

    protected void pointerPressed(int x, int y) {
        if (x >= mobile.menu_botones[0].xPercent && x <= mobile.menu_botones[0].anchoPercent) {
            if (y >= mobile.menu_botones[0].yPercent && y <= (mobile.menu_botones[0].yPercent + mobile.menu_botones[0].largoPercent)) {
                //lamo al juego
                mobile.menu_botones[0].seleccionado = true;
                repaint();
            } else if (y >= mobile.menu_botones[2].yPercent && y <= (mobile.menu_botones[2].yPercent + mobile.menu_botones[2].largoPercent)) {
                mobile.menu_botones[2].seleccionado = true;
                repaint();
            }
        }
    }

    protected void pointerReleased(int x, int y) {
        if (x >= mobile.menu_botones[0].xPercent && x <= mobile.menu_botones[0].anchoPercent) {
            if (y >= mobile.menu_botones[0].yPercent && y <= (mobile.menu_botones[0].yPercent + mobile.menu_botones[0].largoPercent)) {
                //lamo al juego
                //preferences = new Preferences(this, midletInstance);
                preferences = new UIOptions(this, midletInstance);
                midletInstance.d.setCurrent(preferences);
                mobile.menu_botones[0].seleccionado = false;
            } else if (y >= mobile.menu_botones[2].yPercent && y <= (mobile.menu_botones[2].yPercent + mobile.menu_botones[2].largoPercent)) {
                destroy();
                midletInstance.destroyApp(true);
            }
        }
    }

    protected void paint(Graphics g) {
        // dibujo la imagen del titulo
        g.drawImage(mainTitle, mobile.menu_backGround.x, mobile.menu_backGround.y, Graphics.TOP | Graphics.LEFT);

        g.setColor(0xFFFFFF);
        // dibujo recuadros interiores
        for (int i = 0; i < mobile.menu_botones.length; i++) {
            UIButton object = mobile.menu_botones[i];
            if (object.seleccionado) {
                g.setColor(object.colorSeleccionado);
                g.setStrokeStyle(1);
                g.drawRect(object.xPercent, object.yPercent, object.anchoPercent, object.largoPercent);
            }
        }

    }

    public void reset() {
        for (int i = 0; i < mobile.menu_botones.length; i++) {
            UIButton object = mobile.menu_botones[i];
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
                        UIButton object = mobile.menu_botones[i];
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
                        UIButton object = mobile.menu_botones[i];
                        if (i == selectedOption - 1) {
                            object.seleccionado = true;
                        } else {
                            object.seleccionado = false;
                        }
                    }
                }
                break;
            case FIRE:
                changeScreen();
                break;
        }
        repaint();
    }

    private void changeScreen() {
        switch (selectedOption) {
            case 1:
                //preferences = new Preferences(this, midletInstance);
                preferences = new UIOptions(this, midletInstance);
                midletInstance.d.setCurrent(preferences);
                break;
            case 2:
                break;
            case 3:
                destroy();
                midletInstance.destroyApp(true);
                break;
        }
    }

    public void destroy() {
        gameCanvas = null;
        preferences = null;
    }

    private void setAnimations() {
        // for default select the first option
        UIButton object = mobile.menu_botones[0];
        object.seleccionado = true;
    }

    private void defineModel() {
        mobile = new NG320x240();
        // set the buttons according to this screen;
        int ancho = this.getWidth();
        int alto = this.getHeight();

        if (isTactil) {
            UIButton button;
            for (int i = 0; i < mobile.menu_botones.length; i++) {
                button = mobile.menu_botones[i];
                button.xPercent = button.xPercent * ancho / 100;
                button.yPercent = button.yPercent * alto / 100;
                button.anchoPercent = button.anchoPercent * ancho / 100;
                button.largoPercent = button.largoPercent * alto / 100;
            }
        }
    }
}
