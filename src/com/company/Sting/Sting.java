package com.company.Sting;

import city.cs.engine.*;

public class Sting extends DynamicBody {

    private static final Shape stingShape = new CircleShape(0.5f);
    private static final BodyImage stingImage = new BodyImage("data/Player/Wasp/sting.gif", 1.4f);

    public Sting(World world) {
        super(world, stingShape);
        addImage(stingImage);
        setBullet(true);
        setGravityScale(0);
    }

    public static BodyImage getStingImage() {
        return stingImage;
    }
}
