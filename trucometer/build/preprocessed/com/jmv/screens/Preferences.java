/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.screens;

import com.jmv.GameMidlet;
import com.jmv.settings.Settings;
import com.jmv.utils.IDestroyable;
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.Spacer;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;

/**
 *
 * @author justo.vargas
 */
public class Preferences extends Form implements CommandListener, IDestroyable {
    private StringItem stringItem;
    private Image image1;
    private Spacer spacer;
    private TextField textFieldUSR;
    private StringItem stringItem1;
    private TextField textFieldRIVAL;
    private Font font;
    private ImageItem imageItem;
    private Spacer spacer1;
    private Ticker ticker;
    private Command exitCommand;
    private StringItem wellcomeText;
    private StringItem setTipodePartido;
    private Command playGameCommand;
    public Menu backScreen = null;
    private GameCounterCanvas gameCanvas;
    public GameMidlet midlet = null;
    private Thread t;


    public Preferences(String title, Item[] items) {
        super(title, items);
    }

    public Preferences() {
        super("Inicio");
        init();
    }

    Preferences(Menu aThis, GameMidlet midlet) {
        super("Inicio");
        this.midlet = midlet;
        backScreen = aThis;
        init();
    }

    private void init() {
        
        this.setCommandListener(this);

        //obtengo imagen
         try {
            image1 = Image.createImage("/portada.png");
        } catch (IOException ex) {
            
        }
        
        //font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        
        wellcomeText = new StringItem("Hola usuario setea tus preferencias antes de ingresar a la app", "Podras cambiarlas luego en opciones...");
        setTipodePartido = new StringItem("Tipo de partida", "a 15 (por ahora)..");
        
        textFieldUSR = new TextField("Nombre Equipo 1", "teamUsr", 32, TextField.ANY);
        textFieldUSR.setMaxSize(11);
        textFieldRIVAL = new TextField("Nombre Equipo 2", "teamRival", 32, TextField.ANY);
        textFieldRIVAL.setMaxSize(11);
        
        spacer = new Spacer(16, 1);
        spacer1 = new Spacer(16, 1);

        imageItem = new ImageItem("", image1, ImageItem.LAYOUT_CENTER | Item.LAYOUT_TOP | Item.LAYOUT_BOTTOM | Item.LAYOUT_VCENTER | Item.LAYOUT_2, "<Missing Image>", Item.PLAIN);

        ticker = new Ticker("Truco Meter 1.0");
        this.setTicker(ticker);

        exitCommand = new Command("Exit", Command.EXIT, 0);
        playGameCommand = new Command("Play!", Command.SCREEN, 0);
        this.addCommand(exitCommand);
        this.addCommand(playGameCommand);

        this.append(imageItem);
        this.append(wellcomeText);
        this.append(spacer);
        this.append(textFieldUSR);
        this.append(spacer1);
        this.append(textFieldRIVAL);
        this.append(setTipodePartido);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == playGameCommand) {
            if (gameCanvas != null){
                t = null;
                gameCanvas.destroy();
                gameCanvas = null;
            }
            Settings.configuration().setEnd_game(15);
            Settings.configuration().setRivalTeam(textFieldRIVAL.getString());
            Settings.configuration().setUsrTeam(textFieldUSR.getString());
            this.gameCanvas = new GameCounterCanvas(this);
            this.t = new Thread(gameCanvas);
           t.start();
            midlet.d.setCurrent(gameCanvas);
        } else if (c == exitCommand) {
            backScreen.reset();
            midlet.d.setCurrent(backScreen);
        }
    }

    public void destroy() {
    }

    
}
