package com.company.EnemyBullets;

import city.cs.engine.*;

public class ShadowBullet extends DynamicBody {

    private static final Shape shape = new CircleShape(0.5f);
    private static final BodyImage image = new BodyImage("data/Enemies/shadow-bullet.gif", 2f);

    public ShadowBullet(World world) {
        super(world, shape);
        addImage(image);
        setBullet(true);
        setGravityScale(0);
    }

    public static BodyImage getImage() {
        return image;
    }
}
