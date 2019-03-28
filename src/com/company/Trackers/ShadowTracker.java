package com.company.Trackers;

import city.cs.engine.AttachedImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.WorldView;
import com.company.Enemies.Shadow;
import com.company.Walkers.MainWalker;
import org.jbox2d.common.Vec2;

public class ShadowTracker implements StepListener {

    //The view
    private WorldView view;

    //The was body
    private Shadow shadow;

    private MainWalker player;

    private float min;
    private float max;

    public ShadowTracker(WorldView view, MainWalker player, Shadow shadow, float min, float max) {
        this.view = view;
        this.shadow = shadow;
        this.player = player;
        this.min = min;
        this.max = max;
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (shadow.getPosition().x >= max) {
            walkLeft();
        }
        else if (shadow.getPosition().x <= min) {
            walkRight();
        }

        if (player.getPosition().x > min && player.getPosition().x < max) {
            followPlayer();
        }
    }

    public void walkLeft() {
        shadow.removeAllImages();
        AttachedImage at = new AttachedImage(shadow, Shadow.getImage(), 1, 0, new Vec2(0, 0));
        shadow.startWalking(-4);
    }

    public void walkRight() {
        shadow.removeAllImages();
        AttachedImage at = new AttachedImage(shadow, Shadow.getImage(), 1, 0, new Vec2(0, 0));
        at.flipHorizontal();
        shadow.startWalking(4);
    }

    public void followPlayer() {
        if (player.getPosition().x < shadow.getPosition().x) {
            Vec2 a = shadow.getPosition();
            Vec2 m = player.getPosition();
            Vec2 v = m.sub(a);
            v.normalize();
            walkLeft();
        } else if (player.getPosition().x > shadow.getPosition().x) {
            Vec2 a = shadow.getPosition();
            Vec2 m = player.getPosition();
            Vec2 v = m.sub(a);
            v.normalize();
            walkRight();
        }
    }
}
