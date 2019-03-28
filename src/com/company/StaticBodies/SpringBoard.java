package com.company.StaticBodies;

import city.cs.engine.*;
import com.company.Walkers.MainWalker;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpringBoard extends StaticBody implements CollisionListener{

    private MainWalker player;
    private static SoundClip springSound;

    static {
        try {
            springSound = new SoundClip("data/Sounds/FX/spring.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Shape springShape = new BoxShape(1.25f, 0.5f);
    private static final BodyImage springImage = new BodyImage("data/Images/springboard-down.png", 2f);
    private static final BodyImage springUpImage = new BodyImage("data/Images/springboard-up.png", 2f);

    public SpringBoard(World world, MainWalker player, float x, float y, float restitution) {
        super(world, springShape);
        addImage(springUpImage);
        SolidFixture sf = new SolidFixture(this, springShape);
        sf.setRestitution(restitution);
        setPosition(new Vec2(x, y));
        this.player = player;
        this.addCollisionListener(this);
    }

    @Override
    public void collide(final CollisionEvent e){

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (e.getOtherBody() == player) {
                    springSound.play();
                    removeAllImages();
                    addImage(springUpImage);
                }
            }
        };
        Timer timer = new Timer(100, actionListener);
        timer.setRepeats(false);
        timer.start();
        timer.setDelay(100);
        removeAllImages();
        addImage(springImage);

    }

    public static Shape getSpringShape() {
        return springShape;
    }


    public void setPlayer(MainWalker player) {
        this.player = player;
    }
}
