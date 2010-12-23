/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmv.screens;

import com.jmv.GameMidlet;
import com.jmv.models.Phone;
import com.jmv.settings.Settings;
import com.jmv.uicomponents.buttons.UIButton;
import com.jmv.uicomponents.canvas.CanvasString;
import com.jmv.utils.IDestroyable;
import com.jmv.utils.ImageUtil;
import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
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
public class UIOptions extends Canvas implements IDestroyable, CommandListener {

    public Image options;
    private boolean isTactil;
    public GameMidlet midletInstance;
    private ComboBox textFieldUSR;
    private ComboBox textFieldRival;
    private CanvasString usrTeam;
    private CanvasString rivalTeam;
    public Menu backScreen = null;
    private GameCounterCanvas gameCanvas;
    // comandos
    private Command exitCommand;
    private Command playGameCommand;
    private Thread t;
    private int ancho;
    private int alto;

    UIOptions(Menu aThis, GameMidlet midletInstance) {
        setFullScreenMode(true);
        this.midletInstance = midletInstance;
        backScreen = aThis;
        isTactil = hasPointerEvents();

        textFieldUSR = new ComboBox(this, "usr");
        textFieldRival = new ComboBox(this, "rival");

        usrTeam = backScreen.mobile.opciones_strings[0];
        rivalTeam = backScreen.mobile.opciones_strings[1];

        try {
            options = Image.createImage("/opciones.png");
            options = ImageUtil.resizeImage(options, this.getWidth(), this.getHeight(), 2);
        } catch (IOException ex) {
        }

        setCommandListener(this);

        exitCommand = new Command("Exit", Command.EXIT, 0);
        playGameCommand = new Command("Play!", Command.SCREEN, 0);
        this.addCommand(exitCommand);
        this.addCommand(playGameCommand);

        //TODO: hay que hacer un metodo init asi puedo llamar al destroy yPercent liberar objetos de memoria

        this.alto = this.getHeight();
        this.ancho = this.getWidth();

        if (isTactil) {
            UIButton button;
            for (int i = 0; i < backScreen.mobile.opciones_botones.length; i++) {
                button = backScreen.mobile.opciones_botones[i];
                button.xPercent = button.xPercent * ancho / 100;
                button.yPercent = button.yPercent * alto / 100;
                button.anchoPercent = button.anchoPercent * ancho / 100;
                button.largoPercent = button.largoPercent * alto / 100;
            }
        }
    }

    protected void pointerPressed(int x, int y) {
        if (x >= backScreen.mobile.opciones_botones[0].xPercent && x <= backScreen.mobile.opciones_botones[0].anchoPercent) {
            if (y >= backScreen.mobile.opciones_botones[0].yPercent && y <= (backScreen.mobile.opciones_botones[0].yPercent + backScreen.mobile.opciones_botones[0].largoPercent)) {
                //lamo al juego
                backScreen.mobile.opciones_botones[0].seleccionado = true;
            } else if (y >= backScreen.mobile.opciones_botones[1].yPercent && y <= (backScreen.mobile.opciones_botones[1].yPercent + backScreen.mobile.opciones_botones[1].largoPercent)) {
                backScreen.mobile.opciones_botones[1].seleccionado = true;
            }
        } else if (y >= backScreen.mobile.opciones_botones[2].yPercent && y <= (backScreen.mobile.opciones_botones[2].yPercent + backScreen.mobile.opciones_botones[2].largoPercent)) {
            if (x >= backScreen.mobile.opciones_botones[2].xPercent && x <= (backScreen.mobile.opciones_botones[2].xPercent + backScreen.mobile.opciones_botones[2].anchoPercent)) {
                //lamo al juego
                backScreen.mobile.opciones_botones[2].seleccionado = true;
            } else if (x >= backScreen.mobile.opciones_botones[3].xPercent && x <= (backScreen.mobile.opciones_botones[3].xPercent + backScreen.mobile.opciones_botones[3].anchoPercent)) {
                //lamo al juego
                backScreen.mobile.opciones_botones[3].seleccionado = true;
            }
        }
        repaint();
    }

    protected void pointerReleased(int x, int y) {
        if (x >= backScreen.mobile.opciones_botones[0].xPercent && x <= backScreen.mobile.opciones_botones[0].anchoPercent) {
            if (y >= backScreen.mobile.opciones_botones[0].yPercent && y <= (backScreen.mobile.opciones_botones[0].yPercent + backScreen.mobile.opciones_botones[0].largoPercent)) {
                //lamo al juego
                backScreen.mobile.opciones_botones[0].seleccionado = false;
                textFieldUSR.setString(usrTeam.name);
                this.midletInstance.d.setCurrent(textFieldUSR);
            } else if (y >= backScreen.mobile.opciones_botones[1].yPercent && y <= (backScreen.mobile.opciones_botones[1].yPercent + backScreen.mobile.opciones_botones[1].largoPercent)) {
                backScreen.mobile.opciones_botones[1].seleccionado = false;
                textFieldRival.setString(rivalTeam.name);
                this.midletInstance.d.setCurrent(textFieldRival);
            }
        } else if (y >= backScreen.mobile.opciones_botones[2].yPercent && y <= (backScreen.mobile.opciones_botones[2].yPercent + backScreen.mobile.opciones_botones[2].largoPercent)) {
            if (x >= backScreen.mobile.opciones_botones[2].xPercent && x <= (backScreen.mobile.opciones_botones[2].xPercent + backScreen.mobile.opciones_botones[2].anchoPercent)) {

                //lamo al juego
                backScreen.mobile.opciones_botones[2].seleccionado = false;
                backScreen.reset();
                midletInstance.d.setCurrent(backScreen);

            } else if (x >= backScreen.mobile.opciones_botones[3].xPercent && x <= (backScreen.mobile.opciones_botones[3].xPercent + backScreen.mobile.opciones_botones[3].anchoPercent)) {

                //llamo al juego
                backScreen.mobile.opciones_botones[3].seleccionado = false;

                Settings.configuration().setEnd_game(15);
                Settings.configuration().setRivalTeam(rivalTeam.name);
                Settings.configuration().setUsrTeam(usrTeam.name);

                if (gameCanvas == null) {
                    this.gameCanvas = new GameCounterCanvas(this);
                    this.t = new Thread(gameCanvas);
                    this.t.start();
                }

                this.gameCanvas.init(this);

                midletInstance.d.setCurrent(gameCanvas);
            }
        }
        repaint();
    }

    protected void paint(Graphics g) {
        g.drawImage(options, 0, 0, Graphics.TOP | Graphics.LEFT);
        if (isTactil) {
            UIButton object;
            for (int i = 0; i < backScreen.mobile.opciones_botones.length; i++) {
                object = backScreen.mobile.opciones_botones[i];
                if (object.seleccionado) {
                    g.setColor(object.colorSeleccionado);
                    g.fillRect(object.xPercent, object.yPercent, object.anchoPercent, object.largoPercent);
                    g.drawString("pos x: " + object.xPercent + " y:" + object.yPercent, object.xPercent, object.yPercent, Graphics.TOP | Graphics.LEFT);
                }
            }
        }
        g.setColor(0x000000);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
        g.drawString(usrTeam.name, usrTeam.xPercent * this.getWidth() / 100, usrTeam.yPercent * this.getHeight() / 100, Graphics.TOP | Graphics.HCENTER);
        g.drawString(rivalTeam.name, rivalTeam.xPercent * this.getWidth() / 100, rivalTeam.yPercent * this.getHeight() / 100, Graphics.TOP | Graphics.HCENTER);


    }

    public void destroy() {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == playGameCommand) {
            //llamo al juego
            backScreen.mobile.opciones_botones[3].seleccionado = false;
            Settings.configuration().setEnd_game(15);
            Settings.configuration().setRivalTeam(rivalTeam.name);
            Settings.configuration().setUsrTeam(usrTeam.name);
            if (gameCanvas == null) {
                this.gameCanvas = new GameCounterCanvas(this);
               
            }
            this.t = null;
             this.t = new Thread(gameCanvas);
             this.t.start();
            this.gameCanvas.init(this);

            midletInstance.d.setCurrent(gameCanvas);
        } else if (c == exitCommand) {
             //lamo al juego
             backScreen.mobile.opciones_botones[2].seleccionado = false;
             backScreen.reset();
             midletInstance.d.setCurrent(backScreen);
        }
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
            midletInstance.d.setCurrent(beforeScreen);
        }

        private void init() {
            this.setCommandListener(this);
            backCommand = new Command("Volver", Command.BACK, 0);
            this.addCommand(backCommand);
        }
    }
}
