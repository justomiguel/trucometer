/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmv.screens;

import com.jmv.GameMidlet;
import com.jmv.models.screenstyles.touch.N640x360;
import com.jmv.models.Phone;
import com.jmv.models.screenstyles.generic.N320x240;
import com.jmv.uicomponents.Marquesina;
import com.jmv.utils.IDestroyable;
import com.jmv.utils.Item;
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
public class Menu extends Canvas implements Runnable, IDestroyable {

    // main image
    private Image mainTitle = null;
    // an ok image to move around the menu
    private Image select = null;
    // the midlet instance
    public GameMidlet midletInstance;
    // an int to know the current selection
    private int selectedOption = 1;
    // the game to lunch
    private GameCounterCanvas gameCanvas;
    // preferences
    private Preferences preferences;
    // the x,y initial coordinates for the ok image
    private int selectImgX = 40;
    private int selectImgY = 145;
    // if the phone is a touch one this var is going to be true
    private boolean isTactil;
    //manager for the animations
    private Timer timer ;
    // my ticker
    private Marquesina letrero;
    // for the thread
    private boolean interrupted;
    // for the images position
    public Phone mobile;

    public Menu(GameMidlet inst) {
        setFullScreenMode(true);
        midletInstance = inst;
        isTactil = hasPointerEvents();
        interrupted = false;
        setAnimations();
        defineModel();
        getImages();
    }

    protected void pointerPressed(int x, int y) {
        if (x>=mobile.m_botones[0].x && x<=mobile.m_botones[0].ancho){
            if (y>=mobile.m_botones[0].y && y<= (mobile.m_botones[0].y+mobile.m_botones[0].largo)){
                //lamo al juego
                mobile.m_botones[0].seleccionado = true;
                repaint();
            } else if (y>=mobile.m_botones[2].y && y<= (mobile.m_botones[2].y+mobile.m_botones[2].largo)){
                mobile.m_botones[2].seleccionado = true;
                repaint();
            }
        }
    }



    protected void pointerReleased(int x, int y) {
        if (x>=mobile.m_botones[0].x && x<=mobile.m_botones[0].ancho){
            if (y>=mobile.m_botones[0].y && y<= (mobile.m_botones[0].y+mobile.m_botones[0].largo)){
                //lamo al juego
                preferences = new Preferences(this, midletInstance);
                midletInstance.d.setCurrent(preferences);
                mobile.m_botones[0].seleccionado = false;
            } else if (y>=mobile.m_botones[2].y && y<= (mobile.m_botones[2].y+mobile.m_botones[2].largo)){
                destroy();
                midletInstance.destroyApp(true);
            }
        }
    }

    protected void paint(Graphics g) {
       g.setColor(0x000000);
       g.drawRect(0, 0, this.getWidth(), this.getHeight());
       g.fillRect(0, 0, this.getWidth(), this.getHeight());
       g.setColor(0xFFFFFF);
       // dibujo rectas
        for (int i = 0; i < mobile.m_linesPosition.length; i++) {
            Item object = mobile.m_linesPosition[i];
            g.setColor(0xFFFFFF);
            g.drawLine(object.x, object.y, object.x+object.largo, object.y);
        }
       // dibujo recuadros interiores
       for (int i = 0; i < mobile.m_botones.length; i++) {
            Item object = mobile.m_botones[i];
            if (object.seleccionado){
                g.setColor(object.colorSeleccionado);
            } else {
                g.setColor(object.color);
            }
            g.fillRect(object.x, object.y, object.ancho, object.largo);
        }
       // dibujo la imagen del titulo
       g.drawImage(mainTitle, mobile.m_titlePosition.x, mobile.m_titlePosition.y, Graphics.TOP | Graphics.LEFT);
       // dibujo seleccionador
       if (!isTactil) g.drawImage(select, mobile.m_stringsPosition[selectedOption-1].x - select.getWidth(), mobile.m_stringsPosition[selectedOption-1].y, Graphics.TOP | Graphics.LEFT);
       // seteo fuente
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_LARGE));
        // dibujo Strings
        g.setColor(0xFFFFFF);
        for (int i = 0; i < mobile.m_stringsPosition.length; i++) {
            Item object = mobile.m_stringsPosition[i];
            g.drawString(object.name, object.x, object.y, Graphics.TOP | Graphics.LEFT);
        }
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_SMALL));
        g.drawString(letrero.getContent(), letrero.getX(), letrero.getY(), Graphics.TOP | Graphics.LEFT);
    }

    public void reset(){
         for (int i = 0; i < mobile.m_botones.length; i++) {
            Item object = mobile.m_botones[i];
            object.seleccionado = false;
        }
    }

    private void getImages() {
        try {
            mainTitle = Image.createImage(mobile.m_titlePosition.name);
            select = Image.createImage("/select.png");
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
                     for (int i = 0; i < mobile.m_botones.length; i++) {
                        Item object = mobile.m_botones[i];
                        if (i == selectedOption-1){
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
                    for (int i = 0; i < mobile.m_botones.length; i++) {
                        Item object = mobile.m_botones[i];
                        if (i == selectedOption-1){
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
                preferences = new Preferences(this, midletInstance);
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
        interrupted = true;
    }

    public void run() {
        while(!interrupted){
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {

            }
        }
    }

    private void setAnimations() {
        letrero = new Marquesina("TrucoMeter para mobiles -- Beta Version", 1, 0);
        timer = new Timer();
        timer.scheduleAtFixedRate(letrero, 0, 40);
    }

    private void defineModel() {
        int ancho = this.getWidth();
        int alto = this.getHeight();
        if ((alto <= 322)&&(ancho <= 245)){
            mobile = new N320x240();
           // mobile = new Nokia5800();
        } else if ((alto <= 647)&&(ancho <= 365)){
            mobile = new N640x360();
        }
    }
}
