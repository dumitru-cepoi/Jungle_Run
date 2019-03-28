package com.company.EnemyAttacks;

import city.cs.engine.*;
import com.company.Collectibles.Coin;
import com.company.Collectibles.DamageUnit;
import com.company.Collectibles.DoubleCoin;
import com.company.Enemies.Shadow;
import com.company.EnemyBullets.ShadowBullet;
import com.company.Sting.Sting;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShadowAttack implements CollisionListener {

    private WorldView view;
    private MainWalker player;
    private Shadow shadow;
    private ShadowBullet shadowBullet;
    private Timer timer;
    private int max;
    private int min;
    private static SoundClip slimeAttackSound;

    static {
        try {
            slimeAttackSound = new SoundClip("data/Sounds/FX/hurt-01.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ShadowAttack(WorldView view, Shadow shadow, MainWalker player, int min, int max) {
        this.view = view;
        this.shadow = shadow;
        this.player = player;
        this.max = max;
        this.min = min;

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (player.getPosition().x >= min && player.getPosition().x <= max) {
                    shadowBullet = new ShadowBullet(view.getWorld());

                    if (player.getPosition().x < shadow.getPosition().x) {
                        shadowBullet.removeAllImages();
                        AttachedImage at = new AttachedImage(shadowBullet, ShadowBullet.getImage(), 1, 0, new Vec2(0, 0));
                        shadowBullet.setPosition(new Vec2(shadow.getPosition().x - 2f, shadow.getPosition().y));
                    } else if (player.getPosition().x > shadow.getPosition().x) {
                        shadowBullet.removeAllImages();
                        AttachedImage at = new AttachedImage(shadowBullet, ShadowBullet.getImage(), 1, 0, new Vec2(0, 0));
                        at.flipHorizontal();
                        shadowBullet.setPosition(new Vec2(shadow.getPosition().x + 2f, shadow.getPosition().y));
                    }

                    Vec2 a = shadow.getPosition();
                    Vec2 m = player.getPosition();
                    Vec2 v = m.sub(a);
                    v.normalize();
                    shadowBullet.setLinearVelocity(v.mul(15.0f));

                    shadowBullet.addCollisionListener(ShadowAttack.this::collide);

                }
            }
        };
        timer = new Timer(2000, actionListener);
        timer.setRepeats(true);
        timer.start();

    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof MainWalker || e.getOtherBody() instanceof Wasp) {
            player.reduceLife();
            player.dead();
            slimeAttackSound.play();
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
        if (e.getOtherBody() instanceof Sting) {
            e.getOtherBody().destroy();
        }

        e.getReportingBody().destroy();
    }

    public void setShadow(Shadow shadow) {
        this.shadow = shadow;
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
        if (shadow.getHealthPoints() == 0) {
            timer.stop();
        }
    }
}
