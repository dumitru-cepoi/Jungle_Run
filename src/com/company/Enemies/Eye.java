package com.company.Enemies;

import city.cs.engine.*;
import com.company.Walkers.MainWalker;
import org.jbox2d.common.Vec2;

public class Eye extends Walker implements CollisionListener {

    private int healthPoints;
    private MainWalker player;

    private static final Shape shape = new PolygonShape(
            1.4f,0.95f, -1.33f,0.95f, -1.5f,0.2f, -1.18f,-0.86f, 0.04f,-1.24f, 1.12f,-0.93f, 1.49f,-0.05f
    );

    private static final BodyImage eyeImage = new BodyImage("data/Enemies/eye.gif", 3f);

    public Eye(World world, MainWalker player, float x) {
        super(world, shape);
        addImage(eyeImage);
        setGravityScale(0);
        setPosition(new Vec2(x, 10.5f));
        healthPoints = 1;
        this.player = player;
        this.addCollisionListener(this);
    }

    public void reduceLife() {
        healthPoints--;
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

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof MainWalker) {

        }
    }
}
