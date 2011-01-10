/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.globant.gj2framework.utils;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.media.control.VideoControl;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 *
 * @author dario
 *
 * This class is for Media utilities
 * the idea will be do more method to manipulate several media devices
 */
public class MediaUtil {


  /**
   *
   * Gets an image from the Video camera
   *
   * @return the took image.
   */
  public static Image takePicture()throws IOException,MediaException{

    Player player = Manager.createPlayer("capture://video");

    player.realize();

    VideoControl mVideoControl = (VideoControl)player.getControl("VideoControl");

    byte[] raw = mVideoControl.getSnapshot(null);

    Image image = Image.createImage(raw, 0, raw.length);

    player.close();

    return image;

  }

}
