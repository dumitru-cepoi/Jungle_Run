package com.company.Trackers;

import city.cs.engine.Body;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.WorldView;
import org.jbox2d.common.Vec2;

public class WaspTracker implements StepListener {

    //The view
    private WorldView view;

    //The wasp body
    private Body waspBody;

    //The player body
    private Body playerBody;

    public WaspTracker(WorldView view, Body waspBody, Body playerBody) {
        this.view = view;
        this.waspBody = waspBody;
        this.playerBody = playerBody;
    }

    @Override
    public void preStep(StepEvent e) {
    }

    @Override
    public void postStep(StepEvent e) {

        //Set the wasp to follow the player's position
        waspBody.setPosition(new Vec2(this.playerBody.getPosition().add(new Vec2(-1.5f,4f))));
    }
}
