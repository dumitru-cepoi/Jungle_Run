package com.company.Collectibles;

import city.cs.engine.*;
import com.company.Collisions.Pickup;
import com.company.EnemyBullets.EyeBullet;
import com.company.Interfaces.Collideable;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

public class Coin extends DynamicBody implements Collideable {
    private MainWalker player;
    private Wasp wasp;
    private static SoundClip coinSound;

    static {
        try {
            coinSound = new SoundClip("data/Sounds/FX/coin-pickup.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Shape shape = new CircleShape(0.5f);
    private static final BodyImage coinImage = new BodyImage("data/Collectibles/coin.gif", 1f);

    public Coin(World world, MainWalker player, Wasp wasp, float x, float y) {
        super(world, shape);
        addImage(coinImage);
        setPosition(new Vec2(x, y));
        addCollisionListener(new Pickup(player, wasp));
        this.player = player;
        this.wasp = wasp;
    }

    @Override
    public void collisionResponse(Body b) {
        if (b == player || b == wasp) {
            coinSound.play();
            player.incrementCoinCount();
            destroy();
        } if (b instanceof EyeBullet) {

        }
    }
}
