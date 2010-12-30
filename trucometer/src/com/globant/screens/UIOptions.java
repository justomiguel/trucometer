/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.screens;

import com.globant.gj2framework.screens.ScreenManager;
import com.globant.gj2framework.screens.Screen;
import com.globant.GameMidlet;
import com.globant.settings.Settings;
import com.globant.gj2framework.screens.uielements.SingleButton;
import com.globant.gj2framework.screens.uielements.CanvasString;
import com.globant.gj2framework.utils.ImageUtil;
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author Miguel
 */
public class UIOptions extends Screen implements CommandListener {

    // the background image
    public Image options;
    // if the mobile has a touch screen
    private boolean isTactil;
    // combos for the input of the usr and rival names
    private ComboBox textFieldUSR;
    private ComboBox textFieldRival;
    // strings for the combos inputs
    private CanvasString usrTeam;
    private CanvasString rivalTeam;
    // comandos
    private Command exitCommand;
    private Command playGameCommand;
    // screen size
    private int ancho;
    private int alto;

    public UIOptions() {

        setFullScreenMode(true);
        isTactil = hasPointerEvents();

        textFieldUSR = new ComboBox(this, "usr");
        textFieldRival = new ComboBox(this, "rival");

        // set the initial displays values
        usrTeam = GameMidlet.instance.mobile.opciones_strings[0];
        rivalTeam = GameMidlet.instance.mobile.opciones_strings[1];

        try {
            options = Image.createImage("/opciones.png");
            options = ImageUtil.resizeImage(options, this.getWidth(), this.getHeight(), 2);
        } catch (IOException ex) {
        }

        if (!isTactil) {
            exitCommand = new Command("Exit", Command.EXIT, 0);
            playGameCommand = new Command("Play!", Command.SCREEN, 1);

            this.addCommand(exitCommand);
            this.addCommand(playGameCommand);
            setCommandListener(this);
        }

        //TODO: hay que hacer un metodo init asi puedo llamar al destroy yPercent liberar objetos de memoria

        this.alto = this.getHeight();
        this.ancho = this.getWidth();

        if (isTactil) {
            SingleButton button;
            for (int i = 0; i < GameMidlet.instance.mobile.opciones_botones.length; i++) {
                button = GameMidlet.instance.mobile.opciones_botones[i];
                button.updateSize(alto, ancho);
            }
        }
    }

    protected void paint(Graphics g) {
        g.drawImage(options, 0, 0, Graphics.TOP | Graphics.LEFT);
        if (isTactil) {
            SingleButton object;
            for (int i = 0; i < GameMidlet.instance.mobile.opciones_botones.length; i++) {
                object = GameMidlet.instance.mobile.opciones_botones[i];
                if (object.seleccionado) {
                    g.setColor(object.colorSeleccionado);
                } else {
                    g.setColor(object.color);
                }
                g.setStrokeStyle(1);
                g.drawRect(object.xPercent, object.yPercent, object.anchoPercent, object.largoPercent);
            }
        }
        g.setColor(0x000000);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
        g.drawString(usrTeam.name, usrTeam.xPercent * this.getWidth() / 100, usrTeam.yPercent * this.getHeight() / 100, Graphics.TOP | Graphics.HCENTER);
        g.drawString(rivalTeam.name, rivalTeam.xPercent * this.getWidth() / 100, rivalTeam.yPercent * this.getHeight() / 100, Graphics.TOP | Graphics.HCENTER);

    }

    public void commandAction(Command c, Displayable d) {
        if (c == playGameCommand) {
            //llamo al juego
            GameMidlet.instance.mobile.opciones_botones[3].seleccionado = false;
            Settings.configuration().setEnd_game(15);
            Settings.configuration().setRivalTeam(rivalTeam.name);
            Settings.configuration().setUsrTeam(usrTeam.name);
            GameMidlet.instance.changeScreen(GameMidlet.SCREEN_GAME);

        } else if (c == exitCommand) {
            //lamo al juego
            GameMidlet.instance.mobile.opciones_botones[2].seleccionado = false;
            GameMidlet.instance.changeScreen(GameMidlet.SCREEN_MENU);
        }
    }

    public void init() {
    }

    public void run() {
    }

    public void dispose() {
        if (exitCommand != null) {
            this.removeCommand(exitCommand);
        }
        if (playGameCommand != null) {
            this.removeCommand(playGameCommand);
        }
    }

    /*
     * Pointer methods
     */
    protected void pointerPressed(int x, int y) {
        if (GameMidlet.instance.mobile.opciones_botones[0].hitTestPoint(x, y)) {
            //lamo al juego
            GameMidlet.instance.mobile.opciones_botones[0].seleccionado = true;
        } else if (GameMidlet.instance.mobile.opciones_botones[1].hitTestPoint(x, y)) {
            GameMidlet.instance.mobile.opciones_botones[1].seleccionado = true;
        } else if (GameMidlet.instance.mobile.opciones_botones[2].hitTestPoint(x, y)) {
            //lamo al juego
            GameMidlet.instance.mobile.opciones_botones[2].seleccionado = true;
        } else if (GameMidlet.instance.mobile.opciones_botones[3].hitTestPoint(x, y)) {
            //lamo al juego
            GameMidlet.instance.mobile.opciones_botones[3].seleccionado = true;
        }
        repaint();
    }

    protected void pointerReleased(int x, int y) {
        if (GameMidlet.instance.mobile.opciones_botones[0].hitTestPoint(x, y)) {
            //lamo al juego
            GameMidlet.instance.mobile.opciones_botones[0].seleccionado = false;
            textFieldUSR.setString(usrTeam.name);

            // call directly to the screen
            Display.getDisplay(GameMidlet.instance).setCurrent(textFieldUSR);

        } else if (GameMidlet.instance.mobile.opciones_botones[1].hitTestPoint(x, y)) {
            GameMidlet.instance.mobile.opciones_botones[1].seleccionado = false;
            textFieldRival.setString(rivalTeam.name);

            // call directly to the screen
            Display.getDisplay(GameMidlet.instance).setCurrent(textFieldRival);

        } else if (GameMidlet.instance.mobile.opciones_botones[2].hitTestPoint(x, y)) {
            //lamo al juego
            GameMidlet.instance.mobile.opciones_botones[2].seleccionado = false;
            GameMidlet.instance.changeScreen(GameMidlet.SCREEN_MENU);

        } else if (GameMidlet.instance.mobile.opciones_botones[3].hitTestPoint(x, y)) {
            //llamo al juego
            GameMidlet.instance.mobile.opciones_botones[3].seleccionado = false;
            Settings.configuration().setEnd_game(15);
            Settings.configuration().setRivalTeam(rivalTeam.name);
            Settings.configuration().setUsrTeam(usrTeam.name);
            GameMidlet.instance.changeScreen(GameMidlet.SCREEN_GAME);
        }
        repaint();
    }

    private class ComboBox extends TextBox implements CommandListener {

        private Command backCommand;
        private String name;
        private UIOptions beforeScreen;

        public ComboBox(UIOptions optionUI, String name) {
            super("Ingrese Nombre Equipo", "", 11, TextField.ANY);
            setFullScreenMode(true);
            beforeScreen = optionUI;
            init();
            this.name = name;

        }

        public void commandAction(Command c, Displayable d) {
            if (this.name.equals("usr")) {
                usrTeam.name = this.getString();
            } else {
                rivalTeam.name = this.getString();
            }
            Display.getDisplay(GameMidlet.instance).setCurrent(beforeScreen);
        }

        private void init() {
            this.setCommandListener(this);
            backCommand = new Command("Volver", Command.BACK, 0);
            this.addCommand(backCommand);
        }
    }
}
