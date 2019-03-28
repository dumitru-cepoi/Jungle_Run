package com.company.Trackers;

import city.cs.engine.AttachedImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.WorldView;
import com.company.Enemies.Yeti;
import com.company.Walkers.MainWalker;
import org.jbox2d.common.Vec2;

public class YetiTracker implements StepListener {

    //The view
    private WorldView view;

    //The was body
    private Yeti yeti;

    private MainWalker player;

    private float min;
    private float max;

    public YetiTracker(WorldView view, MainWalker player, Yeti yeti, float min, float max) {
        this.view = view;
        this.yeti = yeti;
        this.player = player;
        this.min = min;
        this.max = max;
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (yeti.getPosition().x >= max) {
            walkLeft();
        }
        else if (yeti.getPosition().x <= min) {
            walkRight();
        }

        if (player.getPosition().x > min && player.getPosition().x < max) {
            followPlayer();
        }
    }

    public void walkLeft() {
        yeti.removeAllImages();
        AttachedImage at = new AttachedImage(yeti, Yeti.getImage(), 1, 0, new Vec2(0, 0));
        yeti.startWalking(-4);
    }

    public void walkRight() {
        yeti.removeAllImages();
        AttachedImage at = new AttachedImage(yeti, Yeti.getImage(), 1, 0, new Vec2(0, 0));
        at.flipHorizontal();
        yeti.startWalking(4);
    }

    public void followPlayer() {
        if (player.getPosition().x < yeti.getPosition().x) {
            Vec2 a = yeti.getPosition();
            Vec2 m = player.getPosition();
            Vec2 v = m.sub(a);
            v.normalize();
            walkLeft();
        } else if (player.getPosition().x > yeti.getPosition().x) {
            Vec2 a = yeti.getPosition();
            Vec2 m = player.getPosition();
            Vec2 v = m.sub(a);
            v.normalize();
            walkRight();
        }
    }
}
