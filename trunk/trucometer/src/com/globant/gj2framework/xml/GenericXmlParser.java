/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.gj2framework.animation.xml;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;

/**
 *
 * @author Miguel
 */
public class GenericXmlParser {

    public XmlNode parseXML(KXmlParser parser, boolean ignoreWhitespaces) throws Exception {
        parser.next();
        return _parse(parser, ignoreWhitespaces);
    }

    XmlNode _parse(KXmlParser parser, boolean ignoreWhitespaces) throws Exception {

        XmlNode node = new XmlNode(XmlNode.ELEMENT_NODE);

        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new Exception("Illegal XML state: "
                    + parser.getName() + ", " + parser.getEventType());
        } else {
            node.nodeName = parser.getName();

            for (int i = 0; i < parser.getAttributeCount(); i++) {
                node.setAttribute(parser.getAttributeName(i), parser.getAttributeValue(i));
            }

            parser.next();

            while (parser.getEventType() != XmlPullParser.END_TAG) {
                if (parser.getEventType() == XmlPullParser.START_TAG) {
                    node.addChild(_parse(parser, ignoreWhitespaces));
                } else if (parser.getEventType() == XmlPullParser.TEXT) {
                    if (!ignoreWhitespaces || !parser.isWhitespace()) {
                        XmlNode child = new XmlNode(XmlNode.TEXT_NODE);

                        child.nodeValue = parser.getText();

                        node.addChild(child);
                    }
                }
                parser.next();
            }
        }
        return node;
    }
}
