package com.company.EnemyBullets;

import city.cs.engine.*;

public class EyeBullet extends DynamicBody {

    private static final Shape shape = new CircleShape(0.5f);
    private static final BodyImage image = new BodyImage("data/Enemies/eye-bullet.png", 1f);

    public EyeBullet(World world) {
        super(world, shape);
        addImage(image);
        setBullet(true);
        setGravityScale(0);
    }

}
