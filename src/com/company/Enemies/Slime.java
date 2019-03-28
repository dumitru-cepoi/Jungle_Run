package com.company.Enemies;

import city.cs.engine.*;
import com.company.Controllers.Controller;
import com.company.Controllers.Controller2;
import com.company.Walkers.MainWalker;
import org.jbox2d.common.Vec2;

public class Slime extends Walker implements CollisionListener {

    private MainWalker player;
    private int healthPoints;
    private static SoundClip damageSound;
    private static float NEW_WALKING_SPEED = 5f;

    static {
        try {
            damageSound = new SoundClip("data/Sounds/FX/hurt-02.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Shape shape = new PolygonShape(
            0.98f,0.86f, -0.67f,0.86f, -1.4f,-1.51f, 1.51f,-1.51f);

    private static final BodyImage image = new BodyImage("data/Enemies/slime.gif", 3f);

    public Slime(World world, MainWalker player, float x, float y) {
        super(world, shape);
        addImage(image);
        setGravityScale(3f);
        setPosition(new Vec2(x, y));
        this.player = player;
        this.addCollisionListener(this);
        healthPoints = 5;
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
            Controller.WALKING_SPEED = NEW_WALKING_SPEED;
            Controller2.WALKING_SPEED = NEW_WALKING_SPEED;
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public static float getNewWalkingSpeed() {
        return NEW_WALKING_SPEED;
    }

    public void dead() {
        if (healthPoints == 0) {
            player.incrementLife();
            player.doubleCoinIncrement();
            destroy();
        }
    }
}
