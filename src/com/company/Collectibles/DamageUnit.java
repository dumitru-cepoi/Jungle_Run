package com.company.Collectibles;

import city.cs.engine.*;
import com.company.Collisions.Pickup;
import com.company.Controllers.Controller;
import com.company.Controllers.Controller2;
import com.company.Interfaces.Collideable;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

public class DamageUnit extends DynamicBody implements Collideable {
    private MainWalker player;
    private Wasp wasp;
    private static float NEW_WALKING_SPEED = 6f;
    private static float NEW_JUMPING_SPEED = 18f;
    private static SoundClip damageSound;
    private World world;

    static {
        try {
            damageSound = new SoundClip("data/Sounds/FX/hurt-01.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Shape shape = new CircleShape(0.5f);

    private static final BodyImage image = new BodyImage("data/Collectibles/damageUnit.gif", 1f);

    public DamageUnit(World world, MainWalker player, Wasp wasp, float x, float y) {
        super(world, shape);
        addImage(image);
        setPosition(new Vec2(x, y));
        addCollisionListener(new Pickup(player, wasp));
        this.player = player;
        this.wasp = wasp;
    }

    @Override
    public void collisionResponse(Body b) {
        if (b == player || b == wasp) {
            damageSound.play();
            player.reduceLife();
            player.decrementCoinCount();
            player.dead();
            Controller.WALKING_SPEED = NEW_WALKING_SPEED;
            Controller.JUMPING_SPEED = NEW_JUMPING_SPEED;
            Controller2.WALKING_SPEED = NEW_WALKING_SPEED;
            Controller2.JUMPING_SPEED = NEW_JUMPING_SPEED;
            destroy();
        }
    }
}
