package com.company.Sting;

import city.cs.engine.*;
import com.company.Collectibles.Coin;
import com.company.Enemies.Eye;
import com.company.Enemies.Shadow;
import com.company.Enemies.Slime;
import com.company.Enemies.Yeti;
import com.company.EnemyAttacks.EyeAttack;
import com.company.EnemyAttacks.ShadowAttack;
import com.company.EnemyAttacks.SlimeAttack;
import com.company.EnemyAttacks.YetiAttack;
import com.company.EnemyBullets.EyeBullet;
import com.company.EnemyBullets.ShadowBullet;
import com.company.EnemyBullets.SlimeBullet;
import com.company.EnemyBullets.YetiBullet;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StingAttack extends MouseAdapter implements CollisionListener {

    private Wasp wasp;
    private WorldView view;
    private Sting sting;
    private MainWalker player;
    private Slime slime;
    private Slime slime2;
    private Eye eye;
    private Eye eye2;
    private Eye eye3;
    private Eye eye4;
    private Yeti yeti;
    private Yeti yeti2;
    private Yeti yeti3;
    private Shadow shadow;
    private Shadow shadow1;
    private SlimeAttack slimeAttack;
    private SlimeAttack slimeAttack2;
    private EyeAttack eyeAttack;
    private EyeAttack eyeAttack2;
    private EyeAttack eyeAttack3;
    private EyeAttack eyeAttack4;
    private YetiAttack yetiAttack;
    private YetiAttack yetiAttack2;
    private YetiAttack yetiAttack3;
    private ShadowAttack shadowAttack;
    private ShadowAttack shadowAttack1;
    private static SoundClip stingSound;
    private static SoundClip hitSound;

    static {
        try {
            stingSound = new SoundClip("data/Sounds/FX/stingSound.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            hitSound = new SoundClip("data/Sounds/FX/hitSound.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StingAttack(WorldView view, Wasp wasp, Slime slime, SlimeAttack slimeAttack, Slime slime2, SlimeAttack slimeAttack2,
                       Eye eye, EyeAttack eyeAttack, Eye eye2, EyeAttack eyeAttack2, Eye eye3, EyeAttack eyeAttack3,
                       Eye eye4, EyeAttack eyeAttack4, Yeti yeti, YetiAttack yetiAttack, Yeti yeti2, YetiAttack yetiAttack2,
                       Yeti yeti3, YetiAttack yetiAttack3, Shadow shadow, ShadowAttack shadowAttack, Shadow shadow1,
                       ShadowAttack shadowAttack1, MainWalker player) {

        this.view = view;
        this.wasp = wasp;
        this.player = player;
        this.slime = slime;
        this.slimeAttack = slimeAttack;
        this.slime2 = slime2;
        this.slimeAttack2 = slimeAttack2;
        this.eye = eye;
        this.eyeAttack = eyeAttack;
        this.eye2 = eye2;
        this.eyeAttack2 = eyeAttack2;
        this.eye3 = eye3;
        this.eyeAttack3 = eyeAttack3;
        this.eye4 = eye4;
        this.eyeAttack4 = eyeAttack4;
        this.yeti = yeti;
        this.yetiAttack = yetiAttack;
        this.yeti2 = yeti2;
        this.yetiAttack2 = yetiAttack2;
        this.yeti3 = yeti3;
        this.yetiAttack3 = yetiAttack3;
        this.shadow = shadow;
        this.shadowAttack = shadowAttack;
        this.shadow1 = shadow1;
        this.shadowAttack1 = shadowAttack1;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        sting = new Sting(view.getWorld());

//        sting.setPosition(new Vec2(wasp.getPosition().x+1, wasp.getPosition().y-1.5f));

        if (view.viewToWorld(e.getPoint()).x < wasp.getPosition().x) {
            sting.setPosition(new Vec2(wasp.getPosition().x - 1.5f, wasp.getPosition().y - 1.5f));
            wasp.removeAllImages();
            AttachedImage at = new AttachedImage(wasp, wasp.getWaspAttack(),1,0, new Vec2(0,0));
            sting.removeAllImages();
            AttachedImage atsting = new AttachedImage(sting, sting.getStingImage(), 1, 0, new Vec2(0, 0));
            atsting.flipHorizontal();
        } else if (view.viewToWorld(e.getPoint()).x > wasp.getPosition().x) {
            sting.setPosition(new Vec2(wasp.getPosition().x + 1.5f, wasp.getPosition().y - 1.5f));
            wasp.removeAllImages();
            AttachedImage at = new AttachedImage(wasp, wasp.getWaspAttack(),1,0, new Vec2(0,0));
            at.flipHorizontal();
        }

        sting.addCollisionListener(this);
        stingSound.play();

        //shoot towards the mouse position
        Vec2 a = wasp.getPosition();
        Vec2 m = view.viewToWorld(e.getPoint());
        Vec2 v = m.sub(a);
        v.normalize();
        sting.setLinearVelocity(v.mul(40.0f));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (view.viewToWorld(e.getPoint()).x < wasp.getPosition().x) {
            wasp.removeAllImages();
            AttachedImage at = new AttachedImage(wasp, wasp.getWaspIdle(),1,0, new Vec2(0,0));
        } else if (view.viewToWorld(e.getPoint()).x > wasp.getPosition().x) {
            wasp.removeAllImages();
            AttachedImage at = new AttachedImage(wasp, wasp.getWaspIdle(),1,0, new Vec2(0,0));
            at.flipHorizontal();
        }
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coin) {
            player.incrementCoinCount();
            e.getOtherBody().destroy();
            e.getReportingBody().destroy();
        }
        if (e.getOtherBody() == slime) {
            hitSound.play();
            slime.reduceLife();
            slime.dead();
            slimeAttack.killTimer();
        }
        if (e.getOtherBody() == slime2) {
            hitSound.play();
            slime2.reduceLife();
            slime2.dead();
            slimeAttack2.killTimer();
        }
        if (e.getOtherBody() == eye) {
            hitSound.play();
            eye.reduceLife();
            eye.dead();
            eyeAttack.killTimer();
        }
        if (e.getOtherBody() == eye2) {
            hitSound.play();
            eye2.reduceLife();
            eye2.dead();
            eyeAttack2.killTimer();
        }
        if (e.getOtherBody() == eye3) {
            hitSound.play();
            eye3.reduceLife();
            eye3.dead();
            eyeAttack3.killTimer();
        }
        if (e.getOtherBody() == eye4) {
            hitSound.play();
            eye4.reduceLife();
            eye4.dead();
            eyeAttack4.killTimer();
        }
        if (e.getOtherBody() == yeti) {
            hitSound.play();
            yeti.reduceLife();
            yeti.dead();
            yetiAttack.killTimer();
        }
        if (e.getOtherBody() == yeti2) {
            hitSound.play();
            yeti2.reduceLife();
            yeti2.dead();
            yetiAttack2.killTimer();
        }
        if (e.getOtherBody() == yeti3) {
            hitSound.play();
            yeti3.reduceLife();
            yeti3.dead();
            yetiAttack3.killTimer();
        }
        if (e.getOtherBody() == shadow) {
            hitSound.play();
            shadow.reduceLife();
            shadow.dead();
            shadowAttack.killTimer();
        }
        if (e.getOtherBody() == shadow1) {
            hitSound.play();
            shadow1.reduceLife();
            shadow1.dead();
            shadowAttack1.killTimer();
        }
        if (e.getOtherBody() instanceof EyeBullet) {
            e.getOtherBody().destroy();
        }
        if (e.getOtherBody() instanceof SlimeBullet) {
            e.getOtherBody().destroy();
        }
        if (e.getOtherBody() instanceof YetiBullet) {
            e.getOtherBody().destroy();
        }
        if (e.getOtherBody() instanceof ShadowBullet) {
            e.getOtherBody().destroy();
        }

        e.getReportingBody().destroy();
    }

    public void setWasp(Wasp wasp) {
        this.wasp = wasp;
    }

    public void setPlayer(MainWalker player) {
        this.player = player;
    }
}
