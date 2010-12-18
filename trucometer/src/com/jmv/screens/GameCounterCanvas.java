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
    private Fosforos[] fosforos = null;
    // para manejar los tantos
    private int tantoUSR;
    private int tantoRival;
    private String nombreTeamUsr;
    private String nombreTeamRival;
    // comandos
    private Command exitCommand;
    private UIOptions preferencesInstance = null;
    private Font font = null;
    private Phone mobile;
    private Image mainTitle;
    //manager for the animations
    private Timer timer;
    // my ticker
    private Marquesina letrero;
    private boolean interrupted = false;
    private boolean isTactil;
    private Image mainBackGround;
    private Fosforos fosforoTactil;
    private int posX = 0;
    private int posY = 0;
    private boolean canMoveFosoforo;
    private int ancho;
    private int alto;

    public GameCounterCanvas(UIOptions inst) {
        setFullScreenMode(true);

        ancho = this.getWidth();
        alto = this.getHeight();
        
        // get config
        mobile = inst.backScreen.mobile;
        isTactil = hasPointerEvents();

        // create images
        createImages();

        // set config according the screen size
        setScreenConfig();
        
        // generar ticker
        letrero = new Marquesina(mobile.game_stringsPosition[0].name, 0, mobile.game_stringsPosition[0].yPercent * this.alto / 100);
        
    }

    public void log(String o) {
        System.out.println(o);
    }

    private void log(int indice) {
        System.out.println("log " + indice);
    }

    protected void pointerPressed(int x, int y) {
        if ((x > mobile.game_botones[0].xPercent) && x < (mobile.game_botones[0].xPercent + mobile.game_botones[0].anchoPercent)) {
            if (y > (mobile.game_botones[0].yPercent) && (y < (mobile.game_botones[0].yPercent + mobile.game_botones[0].largoPercent))) {
                mobile.game_botones[0].seleccionado = true;
            }
        } else if (x > mobile.fosforosUnderX && x < this.ancho) {
            if (y > mobile.fosforosUnderY) {
                canMoveFosoforo = true;
            }
        }
        repaint();
    }

    protected void pointerReleased(int x, int y) {
        if (((x > mobile.game_botones[0].xPercent) && x < (mobile.game_botones[0].xPercent + mobile.game_botones[0].anchoPercent)) && (y > (mobile.game_botones[0].yPercent) && (y < (mobile.game_botones[0].yPercent + mobile.game_botones[0].largoPercent)))) {
            interrupted = true;
            preferencesInstance.midletInstance.d.setCurrent(preferencesInstance);
            mobile.game_botones[0].seleccionado = false;
        } else {
            if (canMoveFosoforo) {
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
                canMoveFosoforo = false;
                posX = -fosforoTactil.getWidth();
                posY = -fosforoTactil.getHeight();
            }

        }
        // me tengo que fijar donde deja el loco
        repaint();
    }

    protected void pointerDragged(int x, int y) {
        if (canMoveFosoforo) {
            posX = x;
            posY = y;
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
        g.drawImage(mainTitle, mobile.game_titlePosition.x, mobile.game_titlePosition.y, Graphics.TOP | Graphics.LEFT);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_SMALL));
        g.drawString(letrero.getContent(), letrero.getX(), letrero.getY(), Graphics.TOP | Graphics.LEFT);
        // dibujo los fosforos
        drawFosforos(g);
        g.setColor(0xFFFFFF);
        g.setFont(font);
        g.drawString(nombreTeamUsr, mobile.game_stringsPosition[1].xPercent * this.ancho / 100, mobile.game_stringsPosition[1].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.drawString(nombreTeamRival, mobile.game_stringsPosition[2].xPercent * this.ancho / 100, mobile.game_stringsPosition[2].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.drawString(mobile.game_stringsPosition[3].name, mobile.game_stringsPosition[3].xPercent * this.ancho / 100, mobile.game_stringsPosition[3].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.drawString(nombreTeamUsr + "  0", mobile.game_stringsPosition[4].xPercent * this.ancho / 100, mobile.game_stringsPosition[4].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.drawString(nombreTeamRival + "  0", mobile.game_stringsPosition[5].xPercent * this.ancho / 100, mobile.game_stringsPosition[5].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        g.drawString(mobile.game_stringsPosition[6].name,mobile.game_stringsPosition[6].xPercent * this.ancho / 100, mobile.game_stringsPosition[6].yPercent * this.alto / 100, Graphics.TOP | Graphics.LEFT);
        if (canMoveFosoforo) {
            g.drawImage(fosforoTactil.getShadow(), posX - 2, posY + 3, Graphics.TOP | Graphics.LEFT);
            g.drawImage(fosforoTactil.getImg(), posX, posY, Graphics.TOP | Graphics.LEFT);
        }
        // dibuja varios fosforos
        for (int i = 0;i< 12; i++) {
            Image img = fosforoTactil.getImg();
            g.drawImage(img, mobile.fosforosUnderX + i * img.getWidth(), mobile.fosforosUnderY, Graphics.TOP | Graphics.LEFT);

        }
        for (int i = 0;i< 10; i++) {
            Image img = fosforoTactil.getImg();
            g.drawImage(img, mobile.fosforosUnderX + 8 + i * img.getWidth(), mobile.fosforosUnderY + 14, Graphics.TOP | Graphics.LEFT);
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
        switch (gameAction) {
            case 9:
                if (tantoUSR > 0) {
                    tantoUSR--;
                }
                break;
            case 11:
                if (tantoUSR < 15) {
                    tantoUSR++;
                }
                break;
            case 10:
                if (tantoRival > 0) {
                    tantoRival--;
                }
                break;
            case 12:
                if (tantoRival < 15) {
                    tantoRival++;
                }
                break;
        }
        repaint();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == exitCommand) {
            interrupted = true;
            preferencesInstance.midletInstance.d.setCurrent(preferencesInstance);
        }
    }

    private void createImages() {
        try {
            //imagen de fondo
            mainTitle = Image.createImage(mobile.game_titlePosition.name);
            mainTitle = ImageUtil.resizeTransparentImage(mainTitle, mobile.mainTitleWidth, mobile.mainTitleHeight);
            // si no tiene seteado por defecto una imagen ocupa la img por defecto hasta que esta cubra su pantalla
            if (mobile.myBackGround.length() == 0) {
                mainBackGround = Image.createImage(mobile.defaultBackGround);
                mainBackGround = Image.createImage(mainBackGround, 0, 0, this.ancho, this.alto, Sprite.TRANS_NONE);
            } else {
                mainBackGround = Image.createImage(mobile.myBackGround);
            }

            // fosforos
            Random random = new Random();
            fosforos = new Fosforos[15];
            // shadows
            Image genericImage = null;
            Image shadowGeneric = null;
            int num = 0;
            for (int i = 0; i
                    < fosforos.length; i++) {
                num = i + 1;
                fosforos[i] = new Fosforos();
                if (num % 5 == 0) {
                    genericImage = Image.createImage(mobile.fosforoDIAG);
                    genericImage = ImageUtil.resizeTransparentImage(genericImage, mobile.diagFosforoWidth, mobile.diagFosforoHeight);
                    shadowGeneric = Image.createImage("/shadowDiag.png");
                    shadowGeneric = ImageUtil.resizeTransparentImage(shadowGeneric, mobile.diagFosforoWidth, mobile.diagFosforoHeight);
                } else if (num % 2 == 0) {
                    genericImage = Image.createImage(mobile.fosforoUP);
                    // la doy vuelta
                    genericImage = Image.createImage(genericImage, 0, 0, genericImage.getWidth(), genericImage.getHeight(), Sprite.TRANS_ROT90);
                    genericImage = ImageUtil.resizeTransparentImage(genericImage, mobile.downFosforoWidth, mobile.downFosforoHeight);
                    // sombra
                    shadowGeneric = Image.createImage("/shadow.png");
                    shadowGeneric = Image.createImage(shadowGeneric, 0, 0, shadowGeneric.getWidth(), shadowGeneric.getHeight(), Sprite.TRANS_ROT90);
                    shadowGeneric = ImageUtil.resizeTransparentImage(shadowGeneric, mobile.downFosforoWidth, mobile.downFosforoHeight);
                } else {
                    genericImage = Image.createImage(mobile.fosforoUP);
                    genericImage = ImageUtil.resizeTransparentImage(genericImage, mobile.upFosforoWidth, mobile.upFosforoHeight);
                    shadowGeneric = Image.createImage("/shadow.png");
                    shadowGeneric = ImageUtil.resizeTransparentImage(shadowGeneric, mobile.upFosforoWidth, mobile.upFosforoHeight);
                }
                if ((random.nextInt() % 2 == 0) || (num % 5 == 0)) {
                    genericImage = Image.createImage(genericImage, 0, 0, genericImage.getWidth(), genericImage.getHeight(), Sprite.TRANS_MIRROR_ROT180);
                    shadowGeneric = Image.createImage(shadowGeneric, 0, 0, shadowGeneric.getWidth(), shadowGeneric.getHeight(), Sprite.TRANS_MIRROR_ROT180);
                }
                fosforos[i].setImg(genericImage);
                fosforos[i].setOn(true);
                // sombras
                fosforos[i].setShadow(shadowGeneric);
            }
            fosforoTactil = new Fosforos();
            genericImage = Image.createImage(mobile.fosforoUP);
            genericImage = ImageUtil.resizeTransparentImage(genericImage, mobile.upFosforoWidth, mobile.upFosforoHeight);

            shadowGeneric = Image.createImage("/shadow.png");
            shadowGeneric = ImageUtil.resizeTransparentImage(shadowGeneric, mobile.upFosforoWidth, mobile.upFosforoHeight);
            fosforoTactil.setImg(genericImage);
            fosforoTactil.setOn(true);
            // sombras
            fosforoTactil.setShadow(shadowGeneric);
        } catch (IOException ex) {
            System.out.println("No carga imagen");
        }
    }

    public void destroy() {
        preferencesInstance = null;
        //okCommand = null;
        exitCommand = null;
        fosforos = null;
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

        // generar Timer
        timer = new Timer();
        timer.scheduleAtFixedRate(letrero, 0, 40);

    }

    public void stop(){
        timer.cancel();
        interrupted = true;
        this.removeCommand(exitCommand);
    }

    private void drawFosforos(Graphics g) {
        int initialX = mobile.fosforosPositionU.x;
        int initialY = mobile.fosforosPositionU.y;
        int num = 0;
        int i = 0;
        int DOWNcounter = 0;
        int UPcounter = 0;
        // ni se te ocurra tocar esto
        for (int j = 0; j
                < tantoUSR; j++) {
            Image img = fosforos[j].getImg();
            Image shadow = fosforos[j].getShadow();
            if ((fosforos[j].isOn())) {
                num = j + 1;
                if (num % 5 == 0) {
                    // digbuja diagonales
                    g.drawImage(shadow, initialX + 5, initialY + 4, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + 6, initialY + 5, Graphics.TOP | Graphics.LEFT);
                } else if (num % 2 == 0) {
                    // dibuja
                    g.drawImage(shadow, initialX + 3, initialY + DOWNcounter * (mobile.g_fosForosPositionUnder[0].x) - 2, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + 4, initialY + DOWNcounter * (mobile.g_fosForosPositionUnder[0].x), Graphics.TOP | Graphics.LEFT);
                    DOWNcounter++;
                    if (DOWNcounter == 2) {
                        DOWNcounter = 0;                    }
                } else {
                    // hago la gilada esta por que empiezo al reves por eso ... :S
                    int k = ((i % 2) != 0) ? i - 1 : i;
                    g.drawImage(shadow, initialX + UPcounter * (mobile.g_fosForosPositionUnder[0].y) - 2, initialY + 3, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + UPcounter * (mobile.g_fosForosPositionUnder[0].y), initialY + 4, Graphics.TOP | Graphics.LEFT);
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
        initialX = mobile.fosforosPositionR.x;
        initialY = mobile.fosforosPositionR.y;
        num = 0;
        i = 0;
        DOWNcounter = 0;
        UPcounter = 0;
        // ni se te ocurra tocar esto
        for (int j = 0; j
                < tantoRival; j++) {
            Image img = fosforos[j].getImg();
            Image shadow = fosforos[j].getShadow();
            if ((fosforos[j].isOn())) {
                num = j + 1;
                if (num % 5 == 0) {
                    // digbuja diagonales
                    g.drawImage(shadow, initialX + 5, initialY + 4, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + 6, initialY + 5, Graphics.TOP | Graphics.LEFT);
                } else if (num % 2 == 0) {
                    // dibuja
                    g.drawImage(shadow, initialX + 3, initialY + DOWNcounter * (mobile.g_fosForosPositionUnder[0].x) - 2, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + 4, initialY + DOWNcounter * (mobile.g_fosForosPositionUnder[0].x), Graphics.TOP | Graphics.LEFT);
                    DOWNcounter++;
                    if (DOWNcounter == 2) {
                        DOWNcounter = 0;
                    }
                } else {
                    // hago la gilada esta por que empiezo al reves por eso ... :S
                    int k = ((i % 2) != 0) ? i - 1 : i;
                    g.drawImage(shadow, initialX + UPcounter * (mobile.g_fosForosPositionUnder[0].y) - 2, initialY + 3, Graphics.TOP | Graphics.LEFT);
                    g.drawImage(img, initialX + UPcounter * (mobile.g_fosForosPositionUnder[0].y), initialY + 4, Graphics.TOP | Graphics.LEFT);
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
                button.xPercent = button.xPercent*ancho/100;
                button.yPercent = button.yPercent*alto/100;
                button.anchoPercent = button.anchoPercent*ancho/100;
                button.largoPercent = button.largoPercent*alto/100;
            }
        }
    }

    
}
