package com.company.StaticBodies;

import city.cs.engine.*;
import com.company.Main.Game;
import com.company.Walkers.MainWalker;
import org.jbox2d.common.Vec2;

public class DeathPlatform extends StaticBody implements CollisionListener {

    private MainWalker player;

    public DeathPlatform(World world, Game game, MainWalker player, float halfWidth, float halfHeight, float x, float y) {
        super(world);
        Shape shape = new BoxShape(halfWidth, halfHeight);
        SolidFixture sf = new SolidFixture(this, shape);
        setPosition(new Vec2(x, y));
        this.player = player;
        this.addCollisionListener(this);
        game.save();
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof MainWalker) {
            player.setHealthPoints(0);
            player.dead();
        }
    }
}
