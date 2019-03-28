package com.company.Trackers;

import city.cs.engine.Body;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.WorldView;
import org.jbox2d.common.Vec2;

public class Tracker implements StepListener {

    //The view
    private WorldView view;

    //The body
    private Body body;

    private float gap;
    private float end;

    public Tracker(WorldView view, Body body, float gap, float end) {
        this.view = view;
        this.body = body;
        this.gap = gap;
        this.end = end;
    }

    @Override
    public void preStep(StepEvent e) {}

    @Override
    public void postStep(StepEvent e) {

        //TRACK VIEW

        if (body.getPosition().x < gap) {
            view.setCentre(new Vec2(0,0));
        } else if (body.getPosition().x > end) {
            view.setCentre(new Vec2(end - gap, 0));
        } else if (body.getPosition().x > gap) {
            view.setCentre(new Vec2(body.getPosition().x - gap, 0));
        }

    }

}
