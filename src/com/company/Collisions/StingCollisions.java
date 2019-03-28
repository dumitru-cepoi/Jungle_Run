package com.company.Collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import com.company.Collectibles.Coin;
import com.company.Sting.Sting;
import com.company.Walkers.MainWalker;

public class StingCollisions implements CollisionListener {

    private Sting bullet;
    private MainWalker player;

    public StingCollisions(Sting bullet, MainWalker player) {
        this.bullet = bullet;
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {
//        e.getReportingBody().destroy();

        if (e.getOtherBody() instanceof Coin) {
            player.incrementCoinCount();
            e.getOtherBody().destroy();
            e.getReportingBody().destroy();
        }
    }
}
