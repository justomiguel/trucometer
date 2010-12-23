/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmv.screens;

import com.jmv.models.Phone;
import com.jmv.settings.Settings;
import com.jmv.uicomponents.canvas.CanvasLine;
import com.jmv.uicomponents.Fosforos;
import com.jmv.uicomponents.Marquesina;
import com.jmv.uicomponents.IScreenElement;
import com.jmv.uicomponents.buttons.UIButton;
import com.jmv.utils.IDestroyable;
import com.jmv.utils.ImageUtil;
import com.jmv.utils.Item;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author justo.vargas
 */
public class GameCounterCanvas extends Canvas implements Runnable, CommandListener, IDestroyable {

    // referencias a los fosforos
    private Fosforos[] tantos = null;
    private Fosforos tantoTactil;
    // imagenes de tantos
    private Image IMAGE_UP;
    private Image IMAGE_DIAG;
    private Image IMAGE_SUP;
    private Image IMAGE_SDIAG;
    // background images
    private Image mainBackGround;
    private Image mainTitle;
    // para manejar los tantos
    private int tantoUSR;
    private int tantoRival;
    // image coordinates
    private int posXTantoTactil = 0;
    private int posYTantoTactil = 0;
    // screen size
    private int ancho;
    private int alto;
    // team players
    private String nombreTeamUsr;
    private String nombreTeamRival;
    // comandos
    private Command exitCommand;
    // back screen
    private UIOptions preferencesInstance = null;
    // fonts management
    private Font font = null;
    // configuration
    private Phone mobile;
    //manager for the animations
    private Timer timer;
    // my ticker
    private Marquesina ticker;
    // boolean vars
    private boolean interrupted = false;
    private boolean isTactil;
    private boolean givePoints;
    
    private int usrMatchesWin;
    private int rivalMatchesWin;
    
    private Image winnerImage;
    private boolean thereIsAWinner = false;
    private String winner;
    private boolean enabled;
    private String winnerLegend;

    public GameCounterCanvas(UIOptions inst) {

        setFullScreenMode(true);
        enabled = true;
        ancho = this.getWidth();
        alto = this.getHeight();
        // get config
        mobile = inst.backScreen.mobile;
        isTactil = hasPointerEvents();
        // create images
        createImages();
        // set config according the screen size
        setScreenConfig();
        

    }

    public void log(String o) {
        System.out.println(o);
    }

    private void log(int indice) {
        System.out.println("log " + indice);
    }

    protected void pointerPressed(int x, int y) {
        if (enabled) {
            if ((x > mobile.game_botones[0].xPercent) && x < (mobile.game_botones[0].xPercent + mobile.game_botones[0].anchoPercent)) {
                if (y > (mobile.game_botones[0].yPercent) && (y < (mobile.game_botones[0].yPercent + mobile.game_botones[0].largoPercent))) {
                    mobile.game_botones[0].seleccionado = true;
                }
            } else if (x > mobile.fosforosUnderX && x < this.ancho) {
                if (y > mobile.fosforosUnderY) {
                    givePoints = true;
                }
            }
        }
        repaint();
    }

    protected void pointerReleased(int x, int y) {
        if (enabled) {
            if (((x > mobile.game_botones[0].xPercent) && x < (mobile.game_botones[0].xPercent + mobile.game_botones[0].anchoPercent)) && (y > (mobile.game_botones[0].yPercent) && (y < (mobile.game_botones[0].yPercent + mobile.game_botones[0].largoPercent)))) {
                interrupted = true;
                preferencesInstance.midletInstance.d.setCurrent(preferencesInstance);
                mobile.game_botones[0].seleccionado = false;
            } else {
                if (givePoints) {
                    if ((x > mobile.game_botones[1].xPercent) && (x < (mobile.game_botones[1].xPercent + mobile.game_botones[1].anchoPercent))) {
                        if (y > (mobile.game_botones[1].yPercent) && (y < (mobile.game_botones[1].yPercent + mobile.game_botones[1].largoPercent))) {
                            if (tantoUSR < 15) {
                                tantoUSR++;
                            }
                        }
                    } else if ((x > mobile.game_botones[2].xPercent) && x < (mobile.game_botones[2].xPercent + mobile.game_botones[2].anchoPercent)) {
                        if (y > (mobile.game_botones[2].yPercent) && (y < (mobile.game_botones[2].yPercent + mobile.game_botones[2].largoPercent))) {
                            if (tantoRival < 15) {
                                tantoRival++;
                            }
                        }
                    }
                    givePoints = false;
                    posXTantoTactil = -tantoTactil.getWidth();
                    posYTantoTactil = -tantoTactil.getHeight();
                }

            }
        } else {
                thereIsAWinner = false;
                enabled = true;
        }
        // me tengo que fijar donde deja el loco
        repaint();
    }

    protected void pointerDragged(int x, int y) {
        if (givePoints) {
            posXTantoTactil = x;
            posYTantoTactil = y;
        }
    }

    protected void paint(Graphics g) {
        g.drawImage(mainBackGround, 0, 0, Graphics.TOP | Graphics.LEFT);
        g.setColor(0xFFFFFF);
        g.drawRect(0, 0, this.ancho - 1, this.alto - 1);
        //g.drawImage(background, 0, 0, Graphics.TOP | Graphics.LEFT);
        // dibujo recuadros interiores
        if (isTactil) {
            UIButton object;
            for (int i = 0; i < mobile.game_botones.length; i++) {
                object = mobile.game_botones[i];
                if (object.seleccionado) {
                    g.setColor(object.colorSeleccionado);
                    g.fillRect(object.xPercent, object.yPercent, object.anchoPercent, object.largoPercent);
                }
                g.setColor(0xFFFFFF);
                g.drawRect(object.xPercent, object.yPercent, object.anchoPercent, object.largoPercent);
            }
        }
        // guardar en constantes
        int percent;
        g.setColor(0xFFFFFF);

        // dibujo verticales
        for (int i = 0; i < mobile.game_verticalLinesPosition.length; i++) {
            percent = mobile.game_verticalLinesPosition[i];
            g.drawLine(this.ancho / 2, percent * this.alto / 100, this.ancho / 2, this.alto);
        }

        // dibujolineas horizontales
        for (int i = 0; i < mobile.game_HorizontalLinesPosition.length; i++) {
            percent = mobile.game_HorizontalLinesPosition[i];
            g.drawLine(0, percent * this.alto / 100, this.ancho, percent * this.alto / 100);
        }

        // dibujo la imagen del titulo
        g.drawImage(mainTitle, mobile.game_titlePosition.x * this.ancho / 100, mobile.game_titlePosition.y * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_SMALL));
        g.drawString(ticker.getContent(), ticker.getX(), ticker.getY(), Graphics.TOP | Graphics.LEFT);

        // dibujo los fosforos
        drawFosforos(g);

        g.setColor(0xFFFFFF);
        g.setFont(font);
        g.drawString(nombreTeamUsr, mobile.game_stringsPosition[1].xPercent * this.ancho / 100, mobile.game_stringsPosition[1].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.drawString(nombreTeamRival, mobile.game_stringsPosition[2].xPercent * this.ancho / 100, mobile.game_stringsPosition[2].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.drawString(mobile.game_stringsPosition[3].name, mobile.game_stringsPosition[3].xPercent * this.ancho / 100, mobile.game_stringsPosition[3].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.drawString(nombreTeamUsr + "  "+usrMatchesWin, mobile.game_stringsPosition[4].xPercent * this.ancho / 100, mobile.game_stringsPosition[4].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.drawString(nombreTeamRival +"  "+ rivalMatchesWin, mobile.game_stringsPosition[5].xPercent * this.ancho / 100, mobile.game_stringsPosition[5].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.drawString(mobile.game_stringsPosition[6].name, mobile.game_stringsPosition[6].xPercent * this.ancho / 100, mobile.game_stringsPosition[6].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        if (givePoints) {
            g.drawImage(tantoTactil.getShadow(), posXTantoTactil - 2, posYTantoTactil + 3, Graphics.TOP | Graphics.LEFT);
            g.drawImage(tantoTactil.getImg(), posXTantoTactil, posYTantoTactil, Graphics.TOP | Graphics.LEFT);
        }
        // dibuja varios fosforos
        for (int i = 0; i < 12; i++) {
            Image img = tantoTactil.getImg();
            g.drawImage(img, mobile.fosforosUnderX + i * img.getWidth(), mobile.fosforosUnderY, Graphics.TOP | Graphics.LEFT);

        }
        for (int i = 0; i < 10; i++) {
            Image img = tantoTactil.getImg();
            g.drawImage(img, mobile.fosforosUnderX + 8 + i * img.getWidth(), mobile.fosforosUnderY + 14, Graphics.TOP | Graphics.LEFT);
        }

        if (thereIsAWinner) {
            g.drawImage(winnerImage, 0, 0, Graphics.TOP | Graphics.LEFT);
            g.setColor(0x000000);
            g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_SMALL));
            g.drawString(winner, 32, 199, Graphics.TOP | Graphics.LEFT);
            g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_SMALL));
            g.drawString("vs", 85, 218, Graphics.TOP | Graphics.LEFT);
            g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_SMALL));
            if (winner.equals(nombreTeamUsr)){
                g.drawString(nombreTeamRival, 115, 229, Graphics.TOP | Graphics.LEFT);
            } else {
                g.drawString(nombreTeamUsr, 115, 229, Graphics.TOP | Graphics.LEFT);
            }
            g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_SMALL));
            g.drawString(winnerLegend,70, 251, Graphics.TOP | Graphics.LEFT);
            enabled = false;
        }
    }

    public void run() {
        while (!interrupted) {
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
            }
        }
    }

    protected void keyReleased(int keyCode) {
        int gameAction = getGameAction(keyCode);

        if (enabled) {
            switch (gameAction) {
                case 9:
                    if (tantoUSR > 0) {
                        setTantoUSR(tantoUSR - 1);
                    }
                    break;
                case 11:
                    if (tantoUSR < 15) {
                        setTantoUSR(tantoUSR + 1);
                    }
                    break;
                case 10:
                    if (tantoRival > 0) {
                        setTantoRival(tantoRival - 1);
                    }
                    break;
                case 12:
                    if (tantoRival < 15) {
                        setTantoRival(tantoRival + 1);
                    }
                    break;
            }
        } else {
            if (gameAction == FIRE) {
                thereIsAWinner = false;
                enabled = true;
            }
        }
        repaint();
    }

    public void setTantoRival(int tantoRival) {
        this.tantoRival = tantoRival;
        if (tantoRival == 15) {
            setWinner(nombreTeamRival);
            rivalMatchesWin++;
        }
    }

    public void setTantoUSR(int tantoUSR) {
        this.tantoUSR = tantoUSR;
        if (tantoUSR == 15) {
            setWinner(nombreTeamUsr);
            usrMatchesWin++;
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (enabled) {
            if (c == exitCommand) {
                interrupted = true;
                preferencesInstance.midletInstance.d.setCurrent(preferencesInstance);
            }
        }
    }

    private void createImages() {
        try {

            //imagen de fondo
            mainTitle = Image.createImage(mobile.game_titlePosition.name);
            mainTitle = ImageUtil.resizeTransparentImage(mainTitle, mobile.game_titlePosition.preferedWidth * this.ancho / 100, mobile.game_titlePosition.preferedHeight * this.alto / 100);

            winnerImage = Image.createImage("/final.png");
            winnerImage = ImageUtil.resizeImage(winnerImage, this.getWidth(), this.getHeight(), 2);
            //
            IMAGE_UP = Image.createImage(mobile.fosforoUP);
            IMAGE_DIAG = Image.createImage(mobile.fosforoDIAG);

            IMAGE_SUP = Image.createImage("/shadow.png");
            IMAGE_SDIAG = Image.createImage("/shadowDiag.png");

            // si no tiene seteado por defecto una imagen ocupa la img por defecto hasta que esta cubra su pantalla
            if (mobile.myBackGround.length() == 0) {
                mainBackGround = Image.createImage(mobile.defaultBackGround);
                mainBackGround = Image.createImage(mainBackGround, 0, 0, this.ancho, this.alto, Sprite.TRANS_NONE);
            } else {
                mainBackGround = Image.createImage(mobile.myBackGround);
            }

            // fosforos
            Random random = new Random();
            tantos = new Fosforos[15];
            // shadows
            Image genericImage = null;
            Image shadowGeneric = null;
            int num = 0;
            for (int i = 0; i
                    < tantos.length; i++) {
                num = i + 1;
                tantos[i] = new Fosforos();
                if (num % 5 == 0) {
                    genericImage = IMAGE_DIAG;
                    genericImage = ImageUtil.resizeTransparentImage(genericImage, mobile.diagFosforoWidth * ancho / 100, mobile.diagFosforoHeight * alto / 100);
                    shadowGeneric = IMAGE_SDIAG;
                    shadowGeneric = ImageUtil.resizeTransparentImage(shadowGeneric, mobile.diagFosforoWidth * ancho / 100, mobile.diagFosforoHeight * alto / 100);
                } else if (num % 2 == 0) {
                    genericImage = IMAGE_UP;
                    // la doy vuelta
                    genericImage = Image.createImage(genericImage, 0, 0, genericImage.getWidth(), genericImage.getHeight(), Sprite.TRANS_ROT90);
                    genericImage = ImageUtil.resizeTransparentImage(genericImage, mobile.downFosforoWidth * ancho / 100, mobile.downFosforoHeight * alto / 100);
                    // sombra
                    shadowGeneric = IMAGE_SUP;
                    shadowGeneric = Image.createImage(shadowGeneric, 0, 0, shadowGeneric.getWidth(), shadowGeneric.getHeight(), Sprite.TRANS_ROT90);
                    shadowGeneric = ImageUtil.resizeTransparentImage(shadowGeneric, mobile.downFosforoWidth * ancho / 100, mobile.downFosforoHeight * alto / 100);
                } else {
                    genericImage = IMAGE_UP;
                    genericImage = ImageUtil.resizeTransparentImage(genericImage, mobile.upFosforoWidth * ancho / 100, mobile.upFosforoHeight * alto / 100);
                    shadowGeneric = IMAGE_SUP;
                    shadowGeneric = ImageUtil.resizeTransparentImage(shadowGeneric, mobile.upFosforoWidth * ancho / 100, mobile.upFosforoHeight * alto / 100);
                }
                if ((random.nextInt() % 2 == 0) || (num % 5 == 0)) {
                    genericImage = Image.createImage(genericImage, 0, 0, genericImage.getWidth(), genericImage.getHeight(), Sprite.TRANS_MIRROR_ROT180);
                    shadowGeneric = Image.createImage(shadowGeneric, 0, 0, shadowGeneric.getWidth(), shadowGeneric.getHeight(), Sprite.TRANS_MIRROR_ROT180);
                }
                tantos[i].setImg(genericImage);
                tantos[i].setOn(true);
                // sombras
                tantos[i].setShadow(shadowGeneric);
            }
            tantoTactil = new Fosforos();
            genericImage = IMAGE_UP;
            genericImage = ImageUtil.resizeTransparentImage(genericImage, mobile.upFosforoWidth * ancho / 100, mobile.upFosforoHeight * alto / 100);

            shadowGeneric = IMAGE_SUP;
            shadowGeneric = ImageUtil.resizeTransparentImage(shadowGeneric, mobile.upFosforoWidth * ancho / 100, mobile.upFosforoHeight * alto / 100);
            tantoTactil.setImg(genericImage);
            tantoTactil.setOn(true);
            // sombras
            tantoTactil.setShadow(shadowGeneric);
        } catch (IOException ex) {
            System.out.println("No carga imagen");
        }
    }

    public void destroy() {
        preferencesInstance = null;
        //okCommand = null;
        exitCommand = null;
        timer = null;
        ticker = null;

    }

    public void init(UIOptions inst) {
        // screen to go...
        preferencesInstance = inst;

        // the names of the teams
        nombreTeamRival = Settings.configuration().getRivalTeam();
        nombreTeamUsr = Settings.configuration().getUsrTeam();

        // default font
        font = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

        // usr & rival count
        tantoRival = tantoUSR = 0;

        // add commands
        exitCommand = new Command("Go Back", Command.EXIT, 0);
        this.addCommand(exitCommand);
        this.setCommandListener(this);

        interrupted = false;

         // generar Timer
        timer = new Timer();

        // generar ticker
        ticker = new Marquesina(mobile.game_stringsPosition[0].name, 0, mobile.game_stringsPosition[0].yPercent * this.alto / 100);
        timer.scheduleAtFixedRate(ticker, 0, 40);

    }

    public void stop() {
        timer.cancel();
        interrupted = true;
        this.removeCommand(exitCommand);
        destroy();
    }

    private void drawFosforos(Graphics g) {

        int initialX = mobile.fosforosPositionU.x * ancho / 100;
        int initialY = mobile.fosforosPositionU.y * alto / 100;

        int num = 0;
        int i = 0;

        int DOWNcounter = 0;
        int UPcounter = 0;

        // ni se te ocurra tocar esto
        for (int j = 0; j < tantoUSR; j++) {
            Image img = tantos[j].getImg();
            Image shadow = tantos[j].getShadow();
            if ((tantos[j].isOn())) {
                num = j + 1;
                if (num % 5 == 0) {
                    // digbuja diagonales
                    g.drawImage(shadow, initialX + 5, initialY + 4, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + 6, initialY + 5, Graphics.TOP | Graphics.LEFT);
                } else if (num % 2 == 0) {
                    // dibuja
                    g.drawImage(shadow, initialX + 3, initialY + DOWNcounter * (mobile.g_fosForosPositionUnder[0].x * ancho / 100) - 2, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + 4, initialY + DOWNcounter * (mobile.g_fosForosPositionUnder[0].x * ancho / 100), Graphics.TOP | Graphics.LEFT);
                    DOWNcounter++;
                    if (DOWNcounter == 2) {
                        DOWNcounter = 0;
                    }
                } else {
                    // hago la gilada esta por que empiezo al reves por eso ... :S
                    int k = ((i % 2) != 0) ? i - 1 : i;
                    g.drawImage(shadow, initialX + UPcounter * (mobile.g_fosForosPositionUnder[0].y * alto / 100) - 2, initialY + 3, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + UPcounter * (mobile.g_fosForosPositionUnder[0].y * alto / 100), initialY + 4, Graphics.TOP | Graphics.LEFT);
                    UPcounter++;
                    if (UPcounter == 2) {
                        UPcounter = 0;
                    }
                }
            }
            i++;
            if (num % 5 == 0) {
                initialY = initialY + (i - 1) * (mobile.separate);
                i = 0;
            }
        }
        initialX = mobile.fosforosPositionR.x * ancho / 100;
        initialY = mobile.fosforosPositionR.y * alto / 100;
        num = 0;
        i = 0;
        DOWNcounter = 0;
        UPcounter = 0;
        // ni se te ocurra tocar esto
        for (int j = 0; j
                < tantoRival; j++) {
            Image img = tantos[j].getImg();
            Image shadow = tantos[j].getShadow();
            if ((tantos[j].isOn())) {
                num = j + 1;
                if (num % 5 == 0) {
                    // digbuja diagonales
                    g.drawImage(shadow, initialX + 5, initialY + 4, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + 6, initialY + 5, Graphics.TOP | Graphics.LEFT);
                } else if (num % 2 == 0) {
                    // dibuja
                    g.drawImage(shadow, initialX + 3, initialY + DOWNcounter * (mobile.g_fosForosPositionUnder[0].x * ancho / 100) - 2, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + 4, initialY + DOWNcounter * (mobile.g_fosForosPositionUnder[0].x * ancho / 100), Graphics.TOP | Graphics.LEFT);
                    DOWNcounter++;
                    if (DOWNcounter == 2) {
                        DOWNcounter = 0;
                    }
                } else {
                    // hago la gilada esta por que empiezo al reves por eso ... :S
                    int k = ((i % 2) != 0) ? i - 1 : i;
                    g.drawImage(shadow, initialX + UPcounter * (mobile.g_fosForosPositionUnder[0].y * alto / 100) - 2, initialY + 3, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + UPcounter * (mobile.g_fosForosPositionUnder[0].y * alto / 100), initialY + 4, Graphics.TOP | Graphics.LEFT);
                    UPcounter++;
                    if (UPcounter == 2) {
                        UPcounter = 0;
                    }
                }
            }
            i++;
            if (num % 5 == 0) {
                initialY = initialY + (i - 1) * (mobile.separate);
                i = 0;
            }
        }
    }

    private void log(boolean b) {
        System.out.println(b);
    }

    private void setScreenConfig() {
        // set the buttons according to this screen;
        if (isTactil) {
            UIButton button;
            for (int i = 0; i < mobile.game_botones.length; i++) {
                button = mobile.game_botones[i];
                button.xPercent = button.xPercent * ancho / 100;
                button.yPercent = button.yPercent * alto / 100;
                button.anchoPercent = button.anchoPercent * ancho / 100;
                button.largoPercent = button.largoPercent * alto / 100;
            }
        }
        mobile.separate = mobile.separate * alto / 100;
    }

    private void setWinner(String string) {
        winner = string;
        if (winner.equals(nombreTeamUsr)){;
            winnerLegend = "Por "+tantoUSR+" a "+tantoRival;
        } else {
            winnerLegend = "Por "+tantoRival+" a "+tantoUSR;
        }
        thereIsAWinner = true;
        tantoUSR = 0;
        tantoRival = 0;
    }
}
