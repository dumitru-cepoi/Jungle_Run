package com.company.Collectibles;

import city.cs.engine.*;
import com.company.Collisions.Pickup;
import com.company.Controllers.Controller;
import com.company.Controllers.Controller2;
import com.company.Interfaces.Collideable;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerUp extends DynamicBody implements Collideable {

    private MainWalker player;
    private Wasp wasp;
    private static float NEW_WALKING_SPEED = 10f;
    private static float NEW_JUMPING_SPEED = 22f;
    private static float OLD_WALKING_SPEED = 6f;
    private static float OLD_JUMPING_SPEED = 18f;
    private static SoundClip powerUpSound;
    private Timer timer;

    static {
        try {
            powerUpSound = new SoundClip("data/Sounds/FX/powerUp.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isPickedUp = false;

    private static final Shape shape = new BoxShape(0.7f, 0.7f);

    private static final BodyImage powerupImage = new BodyImage("data/Collectibles/powerUp.gif", 2f);

    public PowerUp(World world, MainWalker player, Wasp wasp, float x, float y) {
        super(world, shape);
        addImage(powerupImage);
        setPosition(new Vec2(x, y));
        addCollisionListener(new Pickup(player, wasp));
        this.player = player;
        this.wasp = wasp;
    }

    @Override
    public void collisionResponse(Body b) {

        if (b == player || b == wasp) {
            isPickedUp = true;
            powerUpSound.play();
            player.incrementLife();
            destroy();
            Controller.WALKING_SPEED = NEW_WALKING_SPEED;
            Controller.JUMPING_SPEED = NEW_JUMPING_SPEED;
            Controller2.WALKING_SPEED = NEW_WALKING_SPEED;
            Controller2.JUMPING_SPEED = NEW_JUMPING_SPEED;
        }

        if (isPickedUp) {
            ActionListener actionListener1 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isPickedUp = false;
                    Controller.WALKING_SPEED = OLD_WALKING_SPEED;
                    Controller.JUMPING_SPEED = OLD_JUMPING_SPEED;
                    Controller2.WALKING_SPEED = OLD_WALKING_SPEED;
                    Controller2.JUMPING_SPEED = OLD_JUMPING_SPEED;
                }
            };
            timer = new Timer(15000, actionListener1);
            timer.setRepeats(false);
            timer.start();
        }

    }

    public void stopTimer() {
        if (isPickedUp) {
            timer.stop();
        }
    }

}
