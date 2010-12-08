/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmv.screens;

import com.jmv.models.Phone;
import com.jmv.settings.Settings;
import com.jmv.uicomponents.Fosforos;
import com.jmv.uicomponents.Marquesina;
import com.jmv.utils.IDestroyable;
import com.jmv.utils.ImageUtil;
import com.jmv.utils.Item;
import java.io.IOException;
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
    private Fosforos[] fosforosUSR = null;
    private Fosforos[] fosforosRival = null;
    // para manejar los tantos
    private int tantoUSR;
    private int tantoRival;
    private String nombreTeamUsr;
    private String nombreTeamRival;
    // comandos
    private Command exitCommand;
    //private Command okCommand;
    private Preferences preferencesInstance = null;
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

    public GameCounterCanvas(Preferences inst) {
        setFullScreenMode(true);
        setTitle("Truco Meter");
        mobile = inst.backScreen.mobile;
        isTactil = hasPointerEvents();
        init(inst);
        try {
            mainTitle = Image.createImage(mobile.g_titlePosition.name);
            mainBackGround = Image.createImage(mobile.backGround);
            mainBackGround = Image.createImage(mainBackGround,0,0, this.getWidth(), this.getHeight(),Sprite.TRANS_NONE);
        } catch (IOException ex) {
        }

        letrero = new Marquesina(mobile.g_stringsPosition[0].name, mobile.g_stringsPosition[0].x, mobile.g_stringsPosition[0].y);
        timer = new Timer();
        timer.scheduleAtFixedRate(letrero, 0, 40);
    }

    public void log(String o) {
        System.out.println(o);
    }

    private void log(int indice) {
        System.out.println("log " + indice);
    }

    protected void pointerPressed(int x, int y) {
        System.out.println("Pos de x:" + x + " pos de y:" + y);
        if ((y >= mobile.g_botones[0].y) && y < (mobile.g_botones[0].y + mobile.g_botones[0].largo)) {
            // control del usr
            if (x > mobile.g_botones[0].x && x < (mobile.g_botones[0].x + mobile.g_botones[0].ancho)) {
                // quiere sumar
                mobile.g_botones[0].seleccionado = true;
            } else if (x > mobile.g_botones[1].x && x < (mobile.g_botones[1].x + mobile.g_botones[1].ancho)) {
                // quiere restar
                mobile.g_botones[1].seleccionado = true;
            } else if (x > mobile.g_botones[2].x && x < (mobile.g_botones[2].x + mobile.g_botones[2].ancho)) {
                // quiere sumar
                mobile.g_botones[2].seleccionado = true;
            } else if (x > mobile.g_botones[3].x && x < (mobile.g_botones[3].x + mobile.g_botones[3].ancho)) {
                // quiere restar
                mobile.g_botones[3].seleccionado = true;
            }
        }

        repaint();
    }

    protected void pointerReleased(int x, int y) {
        System.out.println("Pos de x:" + x + " pos de y:" + y);
        if ((y >= mobile.g_botones[0].y) && y < (mobile.g_botones[0].y + mobile.g_botones[0].largo)) {
            // control del usr
            if (x > mobile.g_botones[0].x && x < (mobile.g_botones[0].x + mobile.g_botones[0].ancho)) {
                // quiere sumar
                if (tantoUSR < 15) {
                    tantoUSR++;
                }
                mobile.g_botones[0].seleccionado = false;
            } else if (x > mobile.g_botones[1].x && x < (mobile.g_botones[1].x + mobile.g_botones[1].ancho)) {
                // quiere restar
                if (tantoUSR > 0) {
                    tantoUSR--;
                }
                mobile.g_botones[1].seleccionado = false;
            } else if (x > mobile.g_botones[2].x && x < (mobile.g_botones[2].x + mobile.g_botones[2].ancho)) {
                // quiere sumar
                if (tantoRival < 15) {
                    tantoRival++;
                }
                mobile.g_botones[2].seleccionado = false;
            } else if (x > mobile.g_botones[3].x && x < (mobile.g_botones[3].x + mobile.g_botones[3].ancho)) {
                // quiere restar
                if (tantoRival > 0) {
                    tantoRival--;
                }
                mobile.g_botones[3].seleccionado = false;
            }
        } else if (x > 0 && x < (mobile.g_botones[0].x + mobile.g_botones[0].ancho)) {
            if (y > (mobile.g_botones[0].y + mobile.g_botones[0].largo)) {
                preferencesInstance.midlet.d.setCurrent(preferencesInstance);
            }
        }

        repaint();
    }

    protected void paint(Graphics g) {
        g.setColor(0x08610D);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(mainBackGround, 0, 0, Graphics.TOP | Graphics.LEFT);
        g.setColor(0x000000);
        Item object;
        //g.drawImage(background, 0, 0, Graphics.TOP | Graphics.LEFT);
        // dibujo recuadros interiores
        if (isTactil) {
            for (int i = 0; i < mobile.g_botones.length; i++) {
                object = mobile.g_botones[i];
                if (object.seleccionado) {
                    g.setColor(object.colorSeleccionado);
                    g.fillRect(object.x, object.y, object.ancho, object.largo);
                }

                
                g.setColor(0xFFFFFF);
                g.drawRect(object.x, object.y, object.ancho, object.largo);
                if (i % 2 == 0) {
                    g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
                    g.drawString("+", object.x + (object.ancho) / 2, object.y + (object.largo) / 4, Graphics.TOP | Graphics.HCENTER);
                } else {
                    g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
                    g.drawString("-", object.x + (object.ancho) / 2, object.y + (object.largo) / 4, Graphics.TOP | Graphics.HCENTER);
                }
            }
        }
        // dibujo rectas
        object = mobile.g_linesPosition[0];
        g.setColor(0xFFFFFF);
        g.drawLine(object.x, object.y, object.x, object.y + object.largo);
        for (int i = 1; i < mobile.g_linesPosition.length; i++) {
            object = mobile.g_linesPosition[i];
            g.setColor(0xFFFFFF);
            g.drawLine(object.x, object.y, object.x + object.largo, object.y);
        }

        // dibujo la imagen del titulo
        g.drawImage(mainTitle, mobile.g_titlePosition.x, mobile.g_titlePosition.y, Graphics.TOP | Graphics.LEFT);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_SMALL));
        g.drawString(letrero.getContent(), letrero.getX(), letrero.getY(), Graphics.TOP | Graphics.LEFT);
        drawFosforos(g);
        g.setColor(0xFFFFFF);
        g.setFont(font);
        g.drawString(nombreTeamUsr, mobile.g_stringsPosition[1].x, mobile.g_stringsPosition[1].y, Graphics.TOP | Graphics.LEFT);
        g.drawString(nombreTeamRival, mobile.g_stringsPosition[2].x, mobile.g_stringsPosition[2].y, Graphics.TOP | Graphics.LEFT);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        g.drawString("Atras", 3, this.getHeight() - 20, Graphics.TOP | Graphics.LEFT);

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
            preferencesInstance.midlet.d.setCurrent(preferencesInstance);
        }
    }

    private void createImages() {
        try {

            fosforosUSR = new Fosforos[15];
            fosforosRival = new Fosforos[15];
            int num = 0;
            for (int i = 0; i < fosforosUSR.length; i++) {
                num = i + 1;
                fosforosUSR[i] = new Fosforos();
                if (num % 5 == 0) {

                    fosforosUSR[i].setImg(Image.createImage(mobile.fosforoDIAG));
                } else if (num % 2 == 0) {

                    fosforosUSR[i].setImg(Image.createImage(mobile.fosforoDOWN));
                } else {

                    fosforosUSR[i].setImg(Image.createImage(mobile.fosforoUP));
                }
                fosforosUSR[i].setOn(true);
            }

            num = 0;
            for (int i = 0; i < fosforosRival.length; i++) {
                num = i + 1;
                fosforosRival[i] = new Fosforos();
                if (num % 5 == 0) {

                    fosforosRival[i].setImg(Image.createImage(mobile.fosforoDIAG));
                } else if (num % 2 == 0) {

                    fosforosRival[i].setImg(Image.createImage(mobile.fosforoDOWN));
                } else {

                    fosforosRival[i].setImg(Image.createImage(mobile.fosforoUP));
                }
                fosforosRival[i].setOn(true);
            }

        } catch (IOException ex) {
            System.out.println("No carga imagen");
        }
    }

    public void destroy() {
        preferencesInstance = null;
        //okCommand = null;
        exitCommand = null;
        fosforosUSR = null;
        fosforosRival = null;

    }

    private void init(Preferences inst) {
        preferencesInstance = inst;
        nombreTeamRival = Settings.configuration().getRivalTeam();
        nombreTeamUsr = Settings.configuration().getUsrTeam();
        font = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        tantoRival = tantoUSR = 0;
        // add commands
        exitCommand = new Command("Go Back", Command.EXIT, 0);
        //okCommand = new Command("Options", Command.OK, 0);
        this.addCommand(exitCommand);
        //this.addCommand(okCommand);
        this.setCommandListener(this);
        // create images
        createImages();
    }

    private void drawFosforosLine(Graphics g) {
    }

    private void drawFosforos(Graphics g) {
        int initialX = mobile.fosforosPositionU.x;
        int initialY = mobile.fosforosPositionU.y;
        int num = 0;
        int i = 0;
        int DOWNcounter = 0;
        int UPcounter = 0;

        // ni se te ocurra tocar esto
        for (int j = 0; j < tantoUSR; j++) {
            Image img = fosforosUSR[j].getImg();
            if ((fosforosUSR[j].isOn())) {
                num = j + 1;
                if (num % 5 == 0) {
                    // digbuja diagonales
                    g.drawImage(img, initialX + 6, initialY + 5, Graphics.TOP | Graphics.LEFT);
                } else if (num % 2 == 0) {
                    // dibuja
                    g.drawImage(img, initialX + 4, initialY + DOWNcounter * (mobile.g_fosForosPositionUnder[0].x), Graphics.TOP | Graphics.LEFT);
                    DOWNcounter++;
                    if (DOWNcounter == 2) {
                        DOWNcounter = 0;
                    }
                } else {
                    // hago la gilada esta por que empiezo al reves por eso ... :S
                    int k = ((i % 2) != 0) ? i - 1 : i;
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
        for (int j = 0; j < tantoRival; j++) {
            Image img = fosforosRival[j].getImg();
            if ((fosforosRival[j].isOn())) {
                     num = j + 1;
                if (num % 5 == 0) {
                    // digbuja diagonales
                    g.drawImage(img, initialX + 6, initialY + 5, Graphics.TOP | Graphics.LEFT);
                } else if (num % 2 == 0) {
                    // dibuja
                    g.drawImage(img, initialX + 4, initialY + DOWNcounter * (mobile.g_fosForosPositionUnder[0].x), Graphics.TOP | Graphics.LEFT);
                    DOWNcounter++;
                    if (DOWNcounter == 2) {
                        DOWNcounter = 0;
                    }
                } else {
                    // hago la gilada esta por que empiezo al reves por eso ... :S
                    int k = ((i % 2) != 0) ? i - 1 : i;
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
}
