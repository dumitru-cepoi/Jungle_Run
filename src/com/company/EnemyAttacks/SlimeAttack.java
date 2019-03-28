package com.company.EnemyAttacks;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.WorldView;
import com.company.Collectibles.Coin;
import com.company.Collectibles.DamageUnit;
import com.company.Collectibles.DoubleCoin;
import com.company.Enemies.Slime;
import com.company.EnemyBullets.SlimeBullet;
import com.company.Sting.Sting;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlimeAttack implements CollisionListener {

    private WorldView view;
    private MainWalker player;
    private Slime slime;
    private SlimeBullet slimeBullet;
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

    public SlimeAttack(WorldView view, Slime slime, MainWalker player, int min, int max) {
        this.view = view;
        this.slime = slime;
        this.player = player;
        this.max = max;
        this.min = min;

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (player.getPosition().x >= min && player.getPosition().x <= max) {
                    slimeBullet = new SlimeBullet(view.getWorld());

                    if (player.getPosition().x < slime.getPosition().x) {
                        slimeBullet.setPosition(new Vec2(slime.getPosition().x - 2f, slime.getPosition().y));
                    } else if (player.getPosition().x > slime.getPosition().x) {
                        slimeBullet.setPosition(new Vec2(slime.getPosition().x + 2f, slime.getPosition().y));
                    }

                    Vec2 a = slime.getPosition();
                    Vec2 m = player.getPosition();
                    Vec2 v = m.sub(a);
                    v.normalize();
                    slimeBullet.setLinearVelocity(v.mul(10.0f));

                    slimeBullet.addCollisionListener(SlimeAttack.this::collide);

                }
            }
        };
        timer = new Timer(2500, actionListener);
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

    public void setSlime(Slime slime) {
        this.slime = slime;
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
        if (slime.getHealthPoints() == 0) {
            timer.stop();
        }
    }
}
