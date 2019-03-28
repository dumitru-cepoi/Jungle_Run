package com.company.Trackers;

import city.cs.engine.AttachedImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.WorldView;
import com.company.Enemies.Slime;
import com.company.Walkers.MainWalker;
import org.jbox2d.common.Vec2;

public class SlimeTracker implements StepListener {

    //The view
    private WorldView view;

    //The was body
    private Slime slime;

    private MainWalker player;

    private float min;
    private float max;

    public SlimeTracker(WorldView view, MainWalker player, Slime slime, float min, float max) {
        this.view = view;
        this.slime = slime;
        this.player = player;
        this.min = min;
        this.max = max;
    }
    
    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {

        if (slime.getPosition().x >= max) {
            walkLeft();
        }
        else if (slime.getPosition().x <= min) {
            walkRight();
        }

        if (player.getPosition().x > min && player.getPosition().x < max) {
           followPlayer();
        }
    }

    public void walkLeft() {
        slime.removeAllImages();
        AttachedImage at = new AttachedImage(slime, Slime.getImage(), 1, 0, new Vec2(0, 0));
        at.flipHorizontal();
        slime.startWalking(-3);
    }

    public void walkRight() {
        slime.removeAllImages();
        AttachedImage at = new AttachedImage(slime, Slime.getImage(), 1, 0, new Vec2(0, 0));
        slime.startWalking(3);
    }

    public void followPlayer() {
        if (player.getPosition().x < slime.getPosition().x) {
            Vec2 a = slime.getPosition();
            Vec2 m = player.getPosition();
            Vec2 v = m.sub(a);
            v.normalize();
            walkLeft();
        } else if (player.getPosition().x > slime.getPosition().x) {
            Vec2 a = slime.getPosition();
            Vec2 m = player.getPosition();
            Vec2 v = m.sub(a);
            v.normalize();
            walkRight();
        }
    }
}
