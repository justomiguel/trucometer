/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmv.screens;

import com.jmv.GameMidlet;
import com.jmv.models.screenstyles.touch.N640x360;
import com.jmv.models.Phone;
import com.jmv.models.screenstyles.generic.NG320x240;
import com.jmv.models.screenstyles.touch.NT320x240;
import com.jmv.uicomponents.canvas.CanvasLine;
import com.jmv.uicomponents.Marquesina;
import com.jmv.uicomponents.buttons.UIButton;
import com.jmv.uicomponents.canvas.CanvasString;
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
        defineModel();
        setAnimations();
        getImages();
    }

    protected void pointerPressed(int x, int y) {
        if (x>=mobile.menu_botones[0].xPercent && x<=mobile.menu_botones[0].anchoPercent){
            if (y>=mobile.menu_botones[0].yPercent && y<= (mobile.menu_botones[0].yPercent+mobile.menu_botones[0].largoPercent)){
                //lamo al juego
                mobile.menu_botones[0].seleccionado = true;
                repaint();
            } else if (y>=mobile.menu_botones[2].yPercent && y<= (mobile.menu_botones[2].yPercent+mobile.menu_botones[2].largoPercent)){
                mobile.menu_botones[2].seleccionado = true;
                repaint();
            }
        }
    }



    protected void pointerReleased(int x, int y) {
        if (x>=mobile.menu_botones[0].xPercent && x<=mobile.menu_botones[0].anchoPercent){
            if (y>=mobile.menu_botones[0].yPercent && y<= (mobile.menu_botones[0].yPercent+mobile.menu_botones[0].largoPercent)){
                //lamo al juego
                //preferences = new Preferences(this, midletInstance);
                preferences = new UIOptions(this, midletInstance);
                midletInstance.d.setCurrent(preferences);
                mobile.menu_botones[0].seleccionado = false;
            } else if (y>=mobile.menu_botones[2].yPercent && y<= (mobile.menu_botones[2].yPercent+mobile.menu_botones[2].largoPercent)){
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
        for (int i = 0; i < mobile.menu_linesPosition.length; i++) {
            CanvasLine object = mobile.menu_linesPosition[i];
            g.setColor(0xFFFFFF);
            g.drawLine(object.x, object.y, object.x+object.largo, object.y);
        }
       // dibujo recuadros interiores
       for (int i = 0; i < mobile.menu_botones.length; i++) {
            UIButton object = mobile.menu_botones[i];
            if (object.seleccionado){
                g.setColor(object.colorSeleccionado);
            } else {
                g.setColor(object.color);
            }
            g.fillRect(object.xPercent, object.yPercent, object.anchoPercent, object.largoPercent);
        }
       // dibujo la imagen del titulo
       g.drawImage(mainTitle, mobile.menu_titlePosition.x, mobile.menu_titlePosition.y, Graphics.TOP | Graphics.LEFT);
       // seteo fuente
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_LARGE));
        // dibujo Strings
        g.setColor(0xFFFFFF);
        for (int i = 0; i < mobile.menu_stringsPosition.length; i++) {
            CanvasString object = mobile.menu_stringsPosition[i];
            g.drawString(object.name, object.xPercent, object.yPercent, Graphics.TOP | Graphics.LEFT);
        }
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_SMALL));
        g.drawString(letrero.getContent(), letrero.getX(), letrero.getY(), Graphics.TOP | Graphics.LEFT);
    }

    public void reset(){
         for (int i = 0; i < mobile.menu_botones.length; i++) {
            UIButton object = mobile.menu_botones[i];
            object.seleccionado = false;
        }
    }

    private void getImages() {
        try {
            mainTitle = Image.createImage(mobile.menu_titlePosition.name);
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
                    for (int i = 0; i < mobile.menu_botones.length; i++) {
                        UIButton object = mobile.menu_botones[i];
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
        // for default select the first option
        UIButton object = mobile.menu_botones[0];
        object.seleccionado = true;
    }

    private void defineModel() {
        
  
        
                mobile = new NG320x240();
    }
            
}
