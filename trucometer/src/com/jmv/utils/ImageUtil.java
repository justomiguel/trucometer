/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmv.utils;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Miguel
 */
public class ImageUtil {

    /**
     * This methog resizes an image by resampling its pixels
     * @param src The image to be resized
     * @param finalWidth The final width to be resized
     * @param finalHeight The final heigth to be resized
     * @return The resized image
     */
    public static Image resizeImage(Image src, int finalWidth, int finalHeight) {
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        Image tmp = Image.createImage(finalWidth, srcHeight);
        Graphics g = tmp.getGraphics();
        int ratio = (srcWidth << 16) / finalWidth;
        int pos = ratio / 2;

        //Horizontal Resize

        for (int x = 0; x < finalWidth; x++) {
            g.setClip(x, 0, 1, srcHeight);
            g.drawImage(src, x - (pos >> 16), 0, Graphics.LEFT | Graphics.TOP);
            pos += ratio;
        }

        Image resizedImage = Image.createImage(finalHeight, finalHeight);
        g = resizedImage.getGraphics();
        ratio = (srcHeight << 16) / finalHeight;
        pos = ratio / 2;

        //Vertical resize

        for (int y = 0; y < finalHeight; y++) {
            g.setClip(0, y, finalHeight, 1);
            g.drawImage(tmp, 0, y - (pos >> 16), Graphics.LEFT | Graphics.TOP);
            pos += ratio;
        }
        return resizedImage;

    }//resize image
}
