package com.jmv;

import com.jmv.parsers.xml.GenericXmlParser;
import com.jmv.parsers.xml.XmlNode;
import com.jmv.screens.GameCounterCanvas;
import com.jmv.screens.Menu;
import java.io.InputStreamReader;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Demo MIDlet creates, runs and displays GameCanvas.
 *
 * @author  Justo Vargas
 * @version 1.0
 */
public class GameMidlet extends MIDlet {

    private Menu menu;
    private Thread t;
    public Display d;

    public void startApp() {

         this.menu = new Menu(this);
        t = new Thread(menu);
        t.start();
        d = Display.getDisplay(this);
        d.setCurrent(menu);
        InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/PhoneConfigurations.xml"));
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
        XmlNode xml4 = getNode(xml3, "buttons");
        


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
