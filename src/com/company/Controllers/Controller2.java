package com.company.Controllers;

import city.cs.engine.AttachedImage;
import city.cs.engine.SoundClip;
import city.cs.engine.Walker;
import com.company.MyView.MyView;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Key handler to control an Walker
public class Controller2 extends KeyAdapter {
    public static float JUMPING_SPEED = 18;
    public static float WALKING_SPEED = 6;
    private static SoundClip jumpSound;

    static {
        try {
            jumpSound = new SoundClip("data/Sounds/FX/jump.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Boolean flags to control jumping animations
    private boolean facingRight;
    private boolean facingLeft;
    private boolean jumping;

    private Walker body;
    private Wasp wasp;
    private MyView myView;

    public Controller2(Walker body) {
        this.body = body;
    }


    /*
     * Handle key press events for walking and jumping.
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Q = quit
        if (code == KeyEvent.VK_Q) {
            System.exit(0);
        }
        // A = walk left
        else if (code == KeyEvent.VK_A) {
            body.startWalking(-WALKING_SPEED);
            //Remove body image and flip it to appropriate side
            body.removeAllImages();
            AttachedImage at = new AttachedImage(body, ((MainWalker) body).getRunImage(), 1, 0, new Vec2(0,0));
            at.flipHorizontal();
            facingLeft = true;
        }
        // D = walk right
        else if (code == KeyEvent.VK_D) {
            body.startWalking(WALKING_SPEED);
            //Remove body image and flip it to appropriate side
            body.removeAllImages();
            new AttachedImage(body, ((MainWalker) body).getRunImage(), 1, 0, new Vec2(0,0));
            facingRight = true;
        }
        // W = jump
        else if (code == KeyEvent.VK_W) {
            jumpSound.play();
            Vec2 v = body.getLinearVelocity();
            //Only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                if (facingRight) {
                    body.jump(JUMPING_SPEED);
                    jumping = true;
                    body.removeAllImages();
                    new AttachedImage(body, ((MainWalker) body).getJumpImage(), 1, 0, new Vec2(0,0));

                } else if (facingLeft) {
                    body.jump(JUMPING_SPEED);
                    jumping = true;
                    body.removeAllImages();
                    AttachedImage at = new AttachedImage(body, ((MainWalker) body).getJumpImage(), 1, 0, new Vec2(0,0));
                    at.flipHorizontal();

                } else {
                    //For the jump to work even when there is no flag set (eg; at spawn)
                    body.jump(JUMPING_SPEED);
                    jumping = true;
                    body.removeAllImages();
                    new AttachedImage(body, ((MainWalker) body).getJumpImage(), 1, 0, new Vec2(0,0));

                }
            }
        }
    }

    /*
     * Handle key release events (stop walking).
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            //Turning the flag off on key release
            facingLeft = false;

            body.stopWalking();
            //Set player image to idle when not walking
            body.removeAllImages();
            AttachedImage at = new AttachedImage(body, ((MainWalker) body).getIdleImage(), 1, 0, new Vec2(0,0));
            at.flipHorizontal();

            //So we don't get idleImage when player jumps and A is released
            if (jumping) {
                //player image
                body.removeAllImages();
                AttachedImage at1 = new AttachedImage(body, ((MainWalker) body).getJumpImage(), 1, 0, new Vec2(0,0));
                at1.flipHorizontal();
            }
        } else if (code == KeyEvent.VK_D) {
            //Turning the flag off on key release
            facingRight = false;

            body.stopWalking();
            //Set player image to idle when not walking
            body.removeAllImages();
            new AttachedImage(body, ((MainWalker) body).getIdleImage(), 1, 0, new Vec2(0,0));

            //So we don't get idleImage when player jumps and D is released
            if (jumping) {
                //player image
                body.removeAllImages();
                new AttachedImage(body, ((MainWalker) body).getJumpImage(), 1, 0, new Vec2(0,0));
            }
        } else if (code == KeyEvent.VK_W) {
            //Turning the flag off to reset the code above
            jumping = false;
        }
    }

    public void setBody(Walker body) {
        this.body = body;
    }
}
