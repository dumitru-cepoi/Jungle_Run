package com.company.Enemies;

import city.cs.engine.*;
import com.company.Walkers.MainWalker;
import org.jbox2d.common.Vec2;

public class Shadow extends Walker implements CollisionListener {
    private MainWalker player;
    private int healthPoints;
    private static SoundClip damageSound;

    static {
        try {
            damageSound = new SoundClip("data/Sounds/FX/hurt-02.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Shape shape = new PolygonShape(
            0.76f,1.9f, -1.1f,1.9f, -1.49f,-2.43f, 1.53f,-2.45f);

    private static final BodyImage image = new BodyImage("data/Enemies/shadow.gif", 5f);

    public Shadow(World world, MainWalker player, float x, float y) {
        super(world, shape);
        addImage(image);
        setGravityScale(5f);
        setPosition(new Vec2(x, y));
        this.player = player;
        this.addCollisionListener(this);
        healthPoints = 6;
    }

    public static BodyImage getImage() {
        return image;
    }

    public void reduceLife() {
        healthPoints--;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof MainWalker) {
            player.reduceLife();
            damageSound.play();
            damageSound.setVolume(2.0);
            player.dead();
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void dead() {
        if (healthPoints == 0) {
            player.incrementLife();
            player.doubleCoinIncrement();
            destroy();
        }
    }
}