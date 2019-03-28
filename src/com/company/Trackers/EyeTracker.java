package com.company.Trackers;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.WorldView;
import com.company.Enemies.Eye;

public class EyeTracker implements StepListener {

    //The view
    private WorldView view;

    //The was body
    private Eye eye;

    private float min;
    private float max;
    private float speed;
    private float minusSpeed;

    public EyeTracker(WorldView view, Eye eye, float speed, float minusSpeed, float min, float max) {
        this.view = view;
        this.eye = eye;
        this.min = min;
        this.max = max;
        this.speed = speed;
        this.minusSpeed = minusSpeed;
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (eye.getPosition().x >= max) {
            eye.startWalking(minusSpeed);
        }
        if (eye.getPosition().x <= min) {
            eye.startWalking(speed);
        }
    }

}
