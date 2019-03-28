package com.company.EnemyAttacks;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.WorldView;
import com.company.Collectibles.Coin;
import com.company.Collectibles.DamageUnit;
import com.company.Collectibles.DoubleCoin;
import com.company.Enemies.Eye;
import com.company.EnemyBullets.EyeBullet;
import com.company.Sting.Sting;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EyeAttack implements CollisionListener {

    private WorldView view;
    private MainWalker player;
    private Eye eye;
    private double distance;
    private EyeBullet eyeBullet;
    private Timer timer;
    private int max;
    private int min;
    private int delay;
    private static SoundClip eyeAttackSound;

    static {
        try {
            eyeAttackSound = new SoundClip("data/Sounds/FX/hurt-01.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EyeAttack(WorldView view, Eye eye, MainWalker player, int min, int max, int delay) {
        this.view = view;
        this.eye = eye;
        this.player = player;
        this.max = max;
        this.min = min;
        this.delay = delay;


        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (player.getPosition().x >= min && player.getPosition().x <= max) {
                    eyeBullet = new EyeBullet(view.getWorld());
                    eyeBullet.setPosition(new Vec2(eye.getPosition().x, eye.getPosition().y - 2));

                    Vec2 a = eye.getPosition();
                    Vec2 m = player.getPosition();
                    Vec2 v = m.sub(a);
                    v.normalize();
                    eyeBullet.setLinearVelocity(v.mul(10.0f));

                    eyeBullet.addCollisionListener(EyeAttack.this::collide);

                }
            }
        };
        timer = new Timer(delay, actionListener);
        timer.setRepeats(true);
        timer.start();


    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof MainWalker || e.getOtherBody() instanceof Wasp) {
            player.reduceLife();
            player.dead();
            eyeAttackSound.play();
            e.getReportingBody().destroy();
        }
        if (e.getOtherBody() instanceof Coin) {
            e.getOtherBody().destroy();
            e.getReportingBody().destroy();
        }
        if (e.getOtherBody() instanceof DamageUnit) {
            e.getOtherBody().destroy();
            e.getReportingBody().destroy();
        }
        if (e.getOtherBody() instanceof DoubleCoin) {
            e.getOtherBody().destroy();
            e.getReportingBody().destroy();
        }
        if (e.getOtherBody() instanceof MainWalker) {
            e.getReportingBody().removeCollisionListener(this);
        }
        if (e.getOtherBody() instanceof Sting) {
            e.getOtherBody().destroy();
        }

        e.getReportingBody().destroy();

    }

    public void setEye(Eye eye) {
        this.eye = eye;
    }

    public void setPlayer(MainWalker player) {
        this.player = player;
    }

    public void stopTimer() {
        timer.stop();
    }

    public void startTimer() {
        timer.start();
    }

    public void killTimer() {
        if (eye.getHealthPoints() == 0) {
            timer.stop();
        }
    }

}
