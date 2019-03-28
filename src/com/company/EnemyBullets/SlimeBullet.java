package com.company.EnemyBullets;

import city.cs.engine.*;

public class SlimeBullet extends DynamicBody {

    private static final Shape shape = new CircleShape(0.5f);
    private static final BodyImage image = new BodyImage("data/Enemies/slime-bullet.png", 1f);

    public SlimeBullet(World world) {
        super(world, shape);
        addImage(image);
        setBullet(true);
        setGravityScale(0);
    }

}
