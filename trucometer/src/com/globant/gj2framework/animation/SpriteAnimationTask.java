/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.globant.gj2framework.animation;

import java.util.TimerTask;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author justo.vargas
 */
public class SpriteAnimationTask extends TimerTask {

        private boolean moving = false;
        private boolean forward = true;
        private Sprite sprite;

        public SpriteAnimationTask(Sprite sprite, boolean forward) {
            this.sprite = sprite;
            this.forward = forward;
        }

        public void run() {
            if (!this.moving) {
                return;
            }
            if (this.forward) {
                this.sprite.nextFrame();
            } else {
                this.sprite.prevFrame();
            }
        }

        public void forward() {
            this.forward = true;
            this.moving = true;
        }

        public void backward() {
            this.forward = false;
            this.moving = true;
        }

        public void setMoving(boolean isMoving) {
            this.moving = isMoving;
        }
}