package com.jmv;

import com.jmv.models.Phone;
import com.jmv.parsers.xml.XmlNode;
import com.jmv.screens.GameCounterCanvas;
import com.jmv.screens.Menu;
import com.jmv.screens.ScreenManager;
import com.jmv.screens.UIOptions;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * Demo MIDlet creates, runs and displays GameCanvas.
 *
 * @author  Justo Vargas
 * @version 1.0
 */
public class GameMidlet extends MIDlet {

    // the manager for the mobile screen
    private ScreenManager screenManager;

    // the current instance of this midlet
    public static GameMidlet instance;

    // for the images position
    public Phone mobile;

    public GameMidlet() {
        
    }

    public void startApp() {

        instance = this;

        Display.getDisplay(this).vibrate(2000);

        this.screenManager = new ScreenManager(Display.getDisplay(this));
        this.screenManager.registerScreen(ScreenManager.SCREEN_MENU, new Menu());
        this.screenManager.registerScreen(ScreenManager.SCREEN_OPT, new UIOptions());
        this.screenManager.registerScreen(ScreenManager.SCREEN_GAME, new GameCounterCanvas());
        
        this.screenManager.changeScreen(ScreenManager.SCREEN_MENU);

       /* InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/PhoneConfigurations.xml"));
        KXmlParser parser = new KXmlParser();
        try {
            parser.setInput(reader);
        } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        }
        GenericXmlParser gParser = new GenericXmlParser();
        XmlNode xml = null;
        try {
            xml = gParser.parseXML(parser, true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.print("value: " + "");
        XmlNode xml2 = getNode(xml, "d320x240");
        XmlNode xml3 = getNode(xml2, "options");
        XmlNode xml4 = getNode(xml3, "buttons");*/
        


    }

    public void changeScreen(String name) {
        screenManager.changeScreen(name);
    }

    

    XmlNode getNode(XmlNode node, String search) {
        if (node == null) return null
                ;
        if (node.nodeName.equals(search)) {
            return node;
        }
        for (int i = 0; i < node.children.size(); i++) {
            getNode((XmlNode) node.children.elementAt(i), search);
        }
        return null;
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        //this.gameCanvas.stop();
        this.notifyDestroyed();
    }
}
