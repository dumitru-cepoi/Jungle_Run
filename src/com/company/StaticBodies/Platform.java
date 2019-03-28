package com.company.StaticBodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {

    public Platform(World world, BodyImage bodyImage, float halfWidth, float halfHeight, float x, float y) {
        super(world);
        Shape shape = new BoxShape(halfWidth, halfHeight);
        SolidFixture sf = new SolidFixture(this, shape);
        sf.setFriction(2);
        setPosition(new Vec2(x, y));
        addImage(bodyImage);
    }

}
