/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.parsers.xml;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Miguel
 */
public class XmlNode {

    public static int TEXT_NODE = 0;
    public static int ELEMENT_NODE = 1;
    public int nodeType = 0;
    public String nodeName = null;
    public String nodeValue = null;
    public Vector children = null;
    public Hashtable attributes = null;

    public XmlNode(int nodeType) {
        this.nodeType = nodeType;
        this.children = new Vector();
        this.attributes = new Hashtable();
    }

    public String[] getAttributeNames() {
        String[] names = new String[attributes.size()];

        Enumeration e = attributes.keys();

        int i = 0;

        while (e.hasMoreElements()) {
            names[i] = (String) e.nextElement();

            i++;
        }
        return names;
    }

    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public String getAttribute(String key) {
        return (String) attributes.get(key);
    }

    public void addChild(XmlNode child) {
        this.children.addElement(child);
    }
}
