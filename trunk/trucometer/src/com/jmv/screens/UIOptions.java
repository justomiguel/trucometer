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

    UIOptions(Menu aThis, GameMidlet midletInstance) {
        setFullScreenMode(true);
        this.midletInstance = midletInstance;
        backScreen = aThis;
        isTactil = hasPointerEvents();
        textFieldUSR = new ComboBox(this, "usr");
        textFieldRival = new ComboBox(this, "rival");
        usrTeam = new CanvasString(110, 101, "usrTeam");
        rivalTeam = new CanvasString(110, 175, "usrRival");
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
        //TODO: hay que hacer un metodo init asi puedo llamar al destroy y liberar objetos de memoria
    }

    protected void pointerPressed(int x, int y) {
        System.out.println("x: " + x + " y: " + y);
        if (x >= backScreen.mobile.opciones_botones[0].x && x <= backScreen.mobile.opciones_botones[0].ancho) {
            if (y >= backScreen.mobile.opciones_botones[0].y && y <= (backScreen.mobile.opciones_botones[0].y + backScreen.mobile.opciones_botones[0].largo)) {
                //lamo al juego
                backScreen.mobile.opciones_botones[0].seleccionado = true;
                repaint();
            } else if (y >= backScreen.mobile.opciones_botones[1].y && y <= (backScreen.mobile.opciones_botones[1].y + backScreen.mobile.opciones_botones[1].largo)) {
                backScreen.mobile.opciones_botones[1].seleccionado = true;
                repaint();
            }
        } else if (y >= backScreen.mobile.opciones_botones[2].y && y <= (backScreen.mobile.opciones_botones[2].y + backScreen.mobile.opciones_botones[2].largo)) {
            if (x >= backScreen.mobile.opciones_botones[2].x && x <= (backScreen.mobile.opciones_botones[2].x + backScreen.mobile.opciones_botones[2].ancho)) {
                //lamo al juego
                backScreen.mobile.opciones_botones[2].seleccionado = true;
                repaint();
            } else if (x >= backScreen.mobile.opciones_botones[3].x && x <= (backScreen.mobile.opciones_botones[3].x + backScreen.mobile.opciones_botones[3].ancho)) {
                //lamo al juego
                backScreen.mobile.opciones_botones[3].seleccionado = true;
                repaint();
            }
        }
    }

    protected void pointerReleased(int x, int y) {
        if (x >= backScreen.mobile.opciones_botones[0].x && x <= backScreen.mobile.opciones_botones[0].ancho) {
            if (y >= backScreen.mobile.opciones_botones[0].y && y <= (backScreen.mobile.opciones_botones[0].y + backScreen.mobile.opciones_botones[0].largo)) {
                //lamo al juego
                backScreen.mobile.opciones_botones[0].seleccionado = false;
                repaint();
                textFieldUSR.setString(usrTeam.name);
                this.midletInstance.d.setCurrent(textFieldUSR);
            } else if (y >= backScreen.mobile.opciones_botones[1].y && y <= (backScreen.mobile.opciones_botones[1].y + backScreen.mobile.opciones_botones[1].largo)) {
                backScreen.mobile.opciones_botones[1].seleccionado = false;
                repaint();
                textFieldRival.setString(rivalTeam.name);
                this.midletInstance.d.setCurrent(textFieldRival);
            }
        }else if (y >= backScreen.mobile.opciones_botones[2].y && y <= (backScreen.mobile.opciones_botones[2].y + backScreen.mobile.opciones_botones[2].largo)) {
            if (x >= backScreen.mobile.opciones_botones[2].x && x <= (backScreen.mobile.opciones_botones[2].x + backScreen.mobile.opciones_botones[2].ancho)) {
                //lamo al juego
                backScreen.mobile.opciones_botones[2].seleccionado = false;
                repaint();
                backScreen.reset();
                midletInstance.d.setCurrent(backScreen);
            } else if (x >= backScreen.mobile.opciones_botones[3].x && x <= (backScreen.mobile.opciones_botones[3].x + backScreen.mobile.opciones_botones[3].ancho)) {
                //lamo al juego
                backScreen.mobile.opciones_botones[3].seleccionado = false;
                repaint();
                if (gameCanvas != null) {
                    t = null;
                    gameCanvas.destroy();
                    gameCanvas = null;
                }
                Settings.configuration().setEnd_game(15);
                Settings.configuration().setRivalTeam(rivalTeam.name);
                Settings.configuration().setUsrTeam(usrTeam.name);
                this.gameCanvas = new GameCounterCanvas(this);
                this.t = new Thread(gameCanvas);
                t.start();
                midletInstance.d.setCurrent(gameCanvas);
            }
        }
    }

    protected void paint(Graphics g) {
        g.drawImage(options, 0, 0, Graphics.TOP | Graphics.LEFT);
        if (isTactil) {
            UIButton object;
            for (int i = 0; i < backScreen.mobile.opciones_botones.length; i++) {
                object = backScreen.mobile.opciones_botones[i];
                if (object.seleccionado) {
                    g.setColor(object.colorSeleccionado);
                    g.fillRect(object.x, object.y, object.ancho, object.largo);
                }
            }
        }
        g.setColor(0x000000);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
        g.drawString(usrTeam.name, usrTeam.x, usrTeam.y, Graphics.TOP | Graphics.HCENTER);
        g.drawString(rivalTeam.name, rivalTeam.x, rivalTeam.y, Graphics.TOP | Graphics.HCENTER);
        
        
    }

    public void destroy() {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == playGameCommand) {
            if (gameCanvas != null) {
                t = null;
                gameCanvas.destroy();
                gameCanvas = null;
            }
            Settings.configuration().setEnd_game(15);
            Settings.configuration().setRivalTeam(rivalTeam.name);
            Settings.configuration().setUsrTeam(usrTeam.name);
            this.gameCanvas = new GameCounterCanvas(this);
            this.t = new Thread(gameCanvas);
            t.start();
            midletInstance.d.setCurrent(gameCanvas);
        } else if (c == exitCommand) {
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
