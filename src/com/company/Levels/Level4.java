package com.company.Levels;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import com.company.AbstractClasses.GameLevel;
import com.company.Collectibles.Coin;
import com.company.Collectibles.DamageUnit;
import com.company.Collectibles.DoubleCoin;
import com.company.Collectibles.PowerUp;
import com.company.Enemies.Eye;
import com.company.Enemies.Shadow;
import com.company.GhostlyBodies.GhostlyImage;
import com.company.Main.Game;
import com.company.StaticBodies.DeathPlatform;
import com.company.StaticBodies.Platform;
import com.company.StaticBodies.SpringBoard;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Level4 extends GameLevel {

    private static final int NUM_COINS = 50;
    private Wasp wasp;
    private DeathPlatform deathPlatform;
    private static PowerUp powerUp;
    private static SoundClip gameMusic;
    private static Shadow shadow;
    private static Shadow shadow2;
    private static Eye eye1;
    private static Eye eye2;

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        try {
            gameMusic = new SoundClip("data/Sounds/Soundtracks/Level4.wav");
            gameMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        wasp = new Wasp(this);

        //GROUND

        deathPlatform = new DeathPlatform(this, game, getPlayer(), 400f, 1f, 200f, -20f);

        BodyImage groundBI = new BodyImage("data/Level4/ground.png", 1.6f);
        BodyImage emptyBI = new BodyImage("data/Level4/cave-empty.png", 1f);
        BodyImage emptyBI2 = new BodyImage("data/Level4/cave-empty2.png", 1f);
        BodyImage rightSide = new BodyImage("data/Level4/cave-side2.png", 1f);
        BodyImage leftSide = new BodyImage("data/Level4/cave-side.png", 1f);

        for (int i = 0; i < 5; i++) {
            GhostlyImage rightSide1 = new GhostlyImage(this, 0.5f, 0.5f, 17.7f, -9.1f - i, rightSide);
        }
        for (int i = 0; i <= 36; i += 4) {
            Platform ground1 = new Platform(this, groundBI, 1.9f, 0.8f, -20 + i, -8f);
        }
        for (int i = 0; i <= 37; i+= 1) {
            GhostlyImage empty1 = new GhostlyImage(this, 0.5f, 0.5f, -20+i, -9f, emptyBI2);
            for (int j = 0; j < 5; j++) {
                GhostlyImage empty2 = new GhostlyImage(this, 0.5f, 0.5f, -20+i, -10f-j, emptyBI);
            }
        }

        for (int i = 0; i < 7; i++) {
            GhostlyImage leftside1 = new GhostlyImage(this, 0.5f, 0.5f, 52.3f, -6.6f - i, leftSide);
            GhostlyImage rightside1 = new GhostlyImage(this, 0.5f, 0.5f, 87.7f, -6.6f - i, rightSide);
        }
        for (int i = 0; i <= 32; i += 4) {
            Platform ground1 = new Platform(this, groundBI, 1.9f, 0.8f, 54 + i, -5.5f);
        }
        for (int i = 0; i <= 34; i+= 1) {
            GhostlyImage empty1 = new GhostlyImage(this, 0.5f, 0.5f, 53+i, -6.5f, emptyBI2);
            GhostlyImage empty3 = new GhostlyImage(this, 0.5f, 0.5f, 53+i, -7.5f, emptyBI2);
            for (int j = 0; j < 7; j++) {
                GhostlyImage empty2 = new GhostlyImage(this, 0.5f, 0.5f, 53+i, -7.5f-j, emptyBI);
            }
        }

        for (int i = 0; i < 7; i++) {
            GhostlyImage leftside1 = new GhostlyImage(this, 0.5f, 0.5f, 120.3f, -8.1f - i, leftSide);
            GhostlyImage rightside1 = new GhostlyImage(this, 0.5f, 0.5f, 163.7f, -8.1f - i, rightSide);
        }
        for (int i = 0; i <= 40; i += 4) {
            Platform ground1 = new Platform(this, groundBI, 1.9f, 0.8f, 122 + i, -7f);
        }
        for (int i = 0; i <= 42; i+= 1) {
            GhostlyImage empty1 = new GhostlyImage(this, 0.5f, 0.5f, 121+i, -8f, emptyBI2);
            GhostlyImage empty3 = new GhostlyImage(this, 0.5f, 0.5f, 121+i, -9f, emptyBI2);
            for (int j = 0; j < 7; j++) {
                GhostlyImage empty2 = new GhostlyImage(this, 0.5f, 0.5f, 121+i, -9f-j, emptyBI);
            }
        }

        //PLATFORMS

        BodyImage platform1BI = new BodyImage("data/Level4/medium-platform.png", 2f);
        BodyImage platform3BI = new BodyImage("data/Level4/small-platform.png", 2f);

        Platform platform1 = new Platform(this, platform3BI, 0.9f, 1, -5, -4.5f);
        Platform platform2 = new Platform(this, platform1BI, 3f, 1, -12, -0.5f);
        Platform platform3 = new Platform(this, platform1BI, 3f, 1, 23.5f, -5f);
        Platform platform4 = new Platform(this, platform1BI, 3f, 1, 32, -1f);
        Platform platform5 = new Platform(this, platform3BI, 0.9f, 1, 41, -1f);
        Platform platform6 = new Platform(this, platform1BI, 3f, 1, 95, -10f);
        for (int i = 0; i < 15; i+= 3) {
            Platform platforms = new Platform(this, platform1BI, 3f, 1f, 75+i, 5.5f);
        }
        Platform platform7 = new Platform(this, platform1BI, 3, 1, 110, 3);
//        Platform platform8 = new Platform(this, platform3BI, 0.9f, 1, 153, -3f);
        Platform platform9 = new Platform(this, platform1BI, 3, 1, 145, 2);

        //COLLECTIBLES

        for (int i = 0; i < 3; i++) {
            Coin coins = new Coin(this, getPlayer(), getWasp(), i*2-14, 1f);
        }
        for (int i = 0; i < 2; i++) {
            Coin coins = new Coin(this, getPlayer(), getWasp(), i*2+5, -6.5f);
        }
        for (int i = 0; i < 2; i++) {
            DamageUnit dunits = new DamageUnit(this, getPlayer(), getWasp(), i*2+9, -6.5f);
        }
        Coin coin = new Coin(this, getPlayer(), getWasp(), 13, -6.5f);
        for (int i = 0; i < 2; i++) {
            Coin coins = new Coin(this, getPlayer(), getWasp(), i*2+23.5f, -3.5f);
        }
        for (int i = 0; i < 2; i++) {
            Coin coins = new Coin(this, getPlayer(), getWasp(), i*2+30f, 0.5f);
        }

        DoubleCoin doubleCoin1 = new DoubleCoin(this, getPlayer(), getWasp(), -12, -6f);
        DoubleCoin doubleCoin2 = new DoubleCoin(this, getPlayer(), getWasp(), 110, 4.5f);
        for (int i = 0; i < 4; i++) {
            DoubleCoin doubleCoins = new DoubleCoin(this, getPlayer(), getWasp(), i*3+75, 9.5f);
        }
        DoubleCoin doubleCoin3 = new DoubleCoin(this, getPlayer(), getWasp(), 145, 3f);

        DamageUnit dunit = new DamageUnit(this, getPlayer(), getWasp(), 112, 4.5f);
        Coin coin1 = new Coin(this, getPlayer(), getWasp(), 108, 4.5f);

        powerUp = new PowerUp(this, getPlayer(), getWasp(), 41, 0);


        //SPRINGBOARDS

        SpringBoard springBoard = new SpringBoard(this, getPlayer(), 95, -8, 1.43f);
        SpringBoard springBoard1 = new SpringBoard(this, getPlayer(), 127, -5.5f, 1.4f);

        //ENEMIES

        shadow = new Shadow(this, getPlayer(), 80, -3);
        shadow2 = new Shadow(this, getPlayer(), 148, -5);
        eye1 = new Eye(this, getPlayer(), -5);
        eye2 = new Eye(this, getPlayer(), 70);

        //IMAGES

        //Start Arrow
        BodyImage arrowRightImage = new BodyImage("data/Images/sign-right.png", 2);
        GhostlyImage arrowRight = new GhostlyImage(this, 1, 1, 2.5f, -6.3f, arrowRightImage);

        //Skeleton
        BodyImage skeleton = new BodyImage("data/Images/skeleton.png", 3);
        GhostlyImage skeleton1 = new GhostlyImage(this, 3, 1, 34, 1.5f, skeleton);
        GhostlyImage skeleton2 = new GhostlyImage(this, 3, 1, 59, -3.2f, skeleton);
        GhostlyImage skeleton3 = new GhostlyImage(this, 3, 1, 135, -4.6f, skeleton);

        //TREES
        BodyImage deadTree = new BodyImage("data/Images/dead-plant.png", 3);
        GhostlyImage deadTree1 = new GhostlyImage(this, 2, 1.5f, 15, -5.7f, deadTree);
        GhostlyImage deadTree2 = new GhostlyImage(this, 2, 1.5f, 87, 8f, deadTree);

        //STONES
        BodyImage stone1 = new BodyImage("data/Images/stone1.png", 2);
        BodyImage stone2 = new BodyImage("data/Images/stone2.png", 2);

        GhostlyImage stone3 = new GhostlyImage(this, 1, 1, -16, -7, stone2);
        GhostlyImage stone4 = new GhostlyImage(this, 1, 1, 78, -4, stone1);
        GhostlyImage stone5 = new GhostlyImage(this, 1, 1, 147, 3, stone2);
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -6);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(160f, -4.5f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCount() >= NUM_COINS && shadow2.getHealthPoints() == 0;
    }

    @Override
    public ImageIcon level1Background() {return null;}

    @Override
    public ImageIcon level2Background() {return null;}

    @Override
    public ImageIcon level3Background() {return null;}

    @Override
    public ImageIcon level4Background() {
        return new ImageIcon("data/Backgrounds/level4-background.gif");
    }

    @Override
    public Wasp getWasp() {
        return wasp;
    }

    @Override
    public SoundClip level1Sound() {
        return null;
    }

    @Override
    public SoundClip level2Sound() {
        return null;
    }

    @Override
    public SoundClip level3Sound() {
        return null;
    }

    @Override
    public SoundClip level4Sound() {
        return gameMusic;
    }

    public static SoundClip getGameMusic() {
        return gameMusic;
    }

    public static Eye getEye1() {
        return eye1;
    }

    public static Eye getEye2() {
        return eye2;
    }

    public static Shadow getShadow() {
        return shadow;
    }

    public static Shadow getShadow2() {
        return shadow2;
    }
}
