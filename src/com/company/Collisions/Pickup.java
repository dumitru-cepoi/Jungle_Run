package com.company.Collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import com.company.Interfaces.Collideable;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;

public class Pickup implements CollisionListener {
    private MainWalker player;
    private Wasp wasp;

    public Pickup(MainWalker player, Wasp wasp) {
        this.player = player;
        this.wasp = wasp;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Collideable) {
            Collideable c = (Collideable) e.getReportingBody();
            c.collisionResponse(e.getOtherBody());
        }
    }
}