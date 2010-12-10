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
     * resizeImage
     * Gets a source image along with new size for it and resizes it.
     *
     * @param  src    The source image.
     * @param finalWidth The final width to be resized
     * @param finalHeight The final heigth to be resized
     * @param  div    Final image width will be corrected to be divideable by this number
     *
     * @return  The resized image.
     */
    public static Image resizeImage(Image src, int finalWidth, int finalHeight, int div) {

        int srcW = src.getWidth();
        int srcH = src.getHeight();

        // Scale down destination width and height by factor
        int destW = finalWidth;
        int destH = finalHeight;

        // Correction of width and height to be divideable by div-parameter
        if ((div != 0) && (destW % div != 0)) {
            for (int i = 1; i <= div; i++) {
                if ((destW - i) % div == 0) {
                    destW = destW - i;
                    if (destH % (destW / div) != 0) {
                        for (int j = 1; j <= div; j++) {
                            if ((destH - j) % (destW / div) == 0) {
                                destH = destH - j;
                                break;
                            }
                        }
                    }
                }
            }
        }

        // Prepare arrays for line-by-line-processing
        final int[] lineRGB = new int[srcW];
        final int[] srcPos = new int[destW]; // cache for x positions

        /*
         * Pre-calculate x positions with modified bresenham algorithm
         */
        int n = 0;
        int eps = -(srcW >> 1);
        for (int x = 0; x < srcW; x++) {
            eps += destW;
            if ((eps << 1) >= srcW) {
                if (++n == destW) {
                    break;
                }
                srcPos[n] = x;
                eps -= srcW;
            }
        }

        final int[] dest = new int[destW * destH];
        for (int y = 0; y < destH; y++) {
            src.getRGB(lineRGB, 0, srcW, 0, y * srcH / destH, srcW, 1);
            for (int x = 0; x < destW; x++) {
                dest[y * destW + x] = lineRGB[srcPos[x]];
            }
        }

        // Garbage collect
        System.gc();
        // Return a new image created from the destination pixel buffer
        Image destImg = Image.createRGBImage(dest, destW, destH, true);
        return destImg;
    }

    /**
     * resizeImage
     * Gets a source image along with new size for it and resizes it.
     *
     * @param  src    The source image.
     * @param  factor The resizing factor
     * @param  div    Final image width will be corrected to be divideable by this number
     *
     * @return  The resized image.
     */
    public static Image resizeImage(Image src, int factor, int div) {
        int srcW = src.getWidth();
        int srcH = src.getHeight();

        // Scale down destination width and height by factor
        int destW = (srcW * factor);
        int destH = (srcH * factor);

        // Correction of width and height to be divideable by div-parameter
        if ((div != 0) && (destW % div != 0)) {
            for (int i = 1; i <= div; i++) {
                if ((destW - i) % div == 0) {
                    destW = destW - i;
                    if (destH % (destW / div) != 0) {
                        for (int j = 1; j <= div; j++) {
                            if ((destH - j) % (destW / div) == 0) {
                                destH = destH - j;
                                break;
                            }
                        }
                    }
                }
            }
        }

        // Prepare arrays for line-by-line-processing
        final int[] lineRGB = new int[srcW];
        final int[] srcPos = new int[destW]; // cache for x positions

        /*
         * Pre-calculate x positions with modified bresenham algorithm
         */
        int n = 0;
        int eps = -(srcW >> 1);
        for (int x = 0; x < srcW; x++) {
            eps += destW;
            if ((eps << 1) >= srcW) {
                if (++n == destW) {
                    break;
                }
                srcPos[n] = x;
                eps -= srcW;
            }
        }

        final int[] dest = new int[destW * destH];
        for (int y = 0; y < destH; y++) {
            src.getRGB(lineRGB, 0, srcW, 0, y * srcH / destH, srcW, 1);
            for (int x = 0; x < destW; x++) {
                dest[y * destW + x] = lineRGB[srcPos[x]];
            }
        }

        // Garbage collect
        System.gc();
        // Return a new image created from the destination pixel buffer
        Image destImg = Image.createRGBImage(dest, destW, destH, true);
        return destImg;
    }

    // fixed point constants
  private static final int FP_SHIFT = 13;
  private static final int FP_ONE = 1 << FP_SHIFT;
  private static final int FP_HALF = 1 << (FP_SHIFT - 1);

  // resampling modes - valid values for the mode parameter of resizeImage()
  // any other value will default to MODE_BOX_FILTER because of the way the conditionals are set in resizeImage()
  public static final int MODE_POINT_SAMPLE = 0;
  public static final int MODE_BOX_FILTER = 1;

  /**
   * getPixels
   * Wrapper for pixel grabbing techniques.
   * I separated this step into it's own function so that other APIs (Nokia, Motorola, Siemens, etc.) can
   * easily substitute the MIDP 2.0 API (Image.getRGB()).
   * @param src The source image whose pixels we are grabbing.
   * @return An int array containing the pixels in 32 bit ARGB format.
   */
  static int[] getPixels(Image src) {
    int w = src.getWidth();
    int h = src.getHeight();
    int[] pixels = new int[w * h];
    src.getRGB(pixels,0,w,0,0,w,h);
    return pixels;
  }

  /**
   * drawPixels
   * Wrapper for pixel drawing function.
   * I separated this step into it's own function so that other APIs (Nokia, Motorola, Siemens, etc.) can
   * easily substitute the MIDP 2.0 API (Image.createRGBImage()).
   * @param pixels int array containing the pixels in 32 bit ARGB format.
   * @param w The width of the image to be created.
   * @param h The height of the image to be created. This parameter is actually superfluous, because it
   * must equal pixels.length / w.
   * @return The image created from the pixel array.
   */
  static Image drawPixels(int[] pixels, int w, int h) {
    return Image.createRGBImage(pixels,w,h,true);
  }

  public static Image resizeTransparentImage(Image src, int destW,int destH) {
    return resizeImage(src,  destW,destH ,MODE_BOX_FILTER);
  }

  /**
   * resizeImage
   * Gets a source image along with new size for it and resizes it.
   * @param src The source image.
   * @param destW The new width for the destination image.
   * @param destH The new heigth for the destination image.
   * @param mode A flag indicating what type of resizing we want to do. It currently supports two type:
   * MODE_POINT_SAMPLE - point sampled resizing, and MODE_BOX_FILTER - box filtered resizing (default).
   * @return The resized image.
   */
  private static Image resizeTransparentImage(Image src, int destW,int destH, int mode) {
    int srcW = src.getWidth();
    int srcH = src.getHeight();

    // create pixel arrays
    int[] destPixels = new int[destW * destH]; // array to hold destination pixels
    int[] srcPixels = getPixels(src); // array with source's pixels

    if (mode == MODE_POINT_SAMPLE) {
      // simple point smapled resizing
      // loop through the destination pixels, find the matching pixel on the source and use that
      for (int destY = 0; destY < destH; ++destY) {
        for (int destX = 0; destX < destW; ++destX) {
          int srcX = (destX * srcW) / destW;
          int srcY = (destY * srcH) / destH;
          destPixels[destX + destY * destW] = srcPixels[srcX + srcY * srcW];
        }
      }
    }
    else {
      // precalculate src/dest ratios
      int ratioW = (srcW << FP_SHIFT) / destW;
      int ratioH = (srcH << FP_SHIFT) / destH;

      int[] tmpPixels = new int[destW * srcH]; // temporary buffer for the horizontal resampling step

      // variables to perform additive blending
      int argb; // color extracted from source
      int a, r, g, b; // separate channels of the color
      int count; // number of pixels sampled for calculating the average

      // the resampling will be separated into 2 steps for simplicity
      // the first step will keep the same height and just stretch the picture horizontally
      // the second step will take the intermediate result and stretch it vertically

      // horizontal resampling
      for (int y = 0; y < srcH; ++y) {
        for (int destX = 0; destX < destW; ++destX) {
          count = 0; a = 0; r = 0; b = 0; g = 0; // initialize color blending vars
          int srcX = (destX * ratioW) >> FP_SHIFT; // calculate beginning of sample
          int srcX2 = ((destX + 1) * ratioW) >> FP_SHIFT; // calculate end of sample

          // now loop from srcX to srcX2 and add up the values for each channel
          do {
            argb = srcPixels[srcX + y * srcW];
            a += ((argb & 0xff000000) >> 24); // alpha channel
            r += ((argb & 0x00ff0000) >> 16); // red channel
            g += ((argb & 0x0000ff00) >> 8); // green channel
            b += (argb & 0x000000ff); // blue channel
            ++count; // count the pixel
            ++srcX; // move on to the next pixel
          }
          while (srcX <= srcX2 && srcX + y * srcW < srcPixels.length);

          // average out the channel values
          a /= count;
          r /= count;
          g /= count;
          b /= count;

          // recreate color from the averaged channels and place it into the temporary buffer
          tmpPixels[destX + y * destW] = ((a << 24) | (r << 16) | (g << 8) | b);
        }
      }

      // vertical resampling of the temporary buffer (which has been horizontally resampled)
      for (int x = 0; x < destW; ++x) {
        for (int destY = 0; destY < destH; ++destY) {
          count = 0; a = 0; r = 0; b = 0; g = 0; // initialize color blending vars
          int srcY = (destY * ratioH) >> FP_SHIFT; // calculate beginning of sample
          int srcY2 = ((destY + 1) * ratioH) >> FP_SHIFT; // calculate end of sample

          // now loop from srcY to srcY2 and add up the values for each channel
          do {
            argb = tmpPixels[x + srcY * destW];
            a += ((argb & 0xff000000) >> 24); // alpha channel
            r += ((argb & 0x00ff0000) >> 16); // red channel
            g += ((argb & 0x0000ff00) >> 8); // green channel
            b += (argb & 0x000000ff); // blue channel
            ++count; // count the pixel
            ++srcY; // move on to the next pixel
          }
          while (srcY <= srcY2 && x + srcY * destW < tmpPixels.length);

          // average out the channel values
          a /= count; a = (a > 255) ? 255 : a;
          r /= count; r = (r > 255) ? 255 : r;
          g /= count; g = (g > 255) ? 255 : g;
          b /= count; b = (b > 255) ? 255 : b;

          // recreate color from the averaged channels and place it into the destination buffer
          destPixels[x + destY * destW] = ((a << 24) | (r << 16) | (g << 8) | b);
        }
      }
    }

    // return a new image created from the destination pixel buffer
    return drawPixels(destPixels,destW,destH);
  }

  
}
