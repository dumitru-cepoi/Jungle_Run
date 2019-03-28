package com.company.GhostlyBodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class GhostlyImage extends StaticBody {

    public GhostlyImage(World world, float halfWidth, float halfHeight, float x, float y, BodyImage image) {
        super(world);
        Shape shape = new BoxShape(halfWidth, halfHeight);
        GhostlyFixture ghost = new GhostlyFixture(this, shape);
        setPosition(new Vec2(x, y));
        addImage(image);
    }
}
