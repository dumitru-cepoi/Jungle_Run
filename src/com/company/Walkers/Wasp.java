package com.company.Walkers;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Wasp extends Walker {
    private static final Shape shape = new PolygonShape(
            0.92f,1.09f, -0.1f,1.42f, -0.97f,-0.13f, -0.67f,-1.01f, 1.06f,-0.17f);

    private static final BodyImage waspIdle = new BodyImage("data/Player/Wasp/wasp-idle.gif", 3f);

    private static final BodyImage waspMove = new BodyImage("data/Player/Wasp/wasp-move.gif", 3f);

    private static final BodyImage waspJump = new BodyImage("data/Player/Wasp/wasp-jump.gif", 3f);

    private static final BodyImage waspAttack = new BodyImage("data/Player/Wasp/wasp-attack.gif", 3f);

    public Wasp(World world) {
        super(world, shape);
        AttachedImage at = new AttachedImage(this, waspIdle, 1, 0, new Vec2(0,0));
        at.flipHorizontal();
        this.setGravityScale(0f);
    }

    public BodyImage getWaspIdle() {
        return waspIdle;
    }

    public BodyImage getWaspMove() {
        return waspMove;
    }

    public BodyImage getWaspJump() {
        return waspJump;
    }

    public BodyImage getWaspAttack() {
        return waspAttack;
    }
}
