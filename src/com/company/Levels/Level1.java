package com.company.Levels;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import com.company.AbstractClasses.GameLevel;
import com.company.Collectibles.Coin;
import com.company.Collectibles.DamageUnit;
import com.company.Collectibles.DoubleCoin;
import com.company.Collectibles.PowerUp;
import com.company.Enemies.Eye;
import com.company.GhostlyBodies.GhostlyImage;
import com.company.Main.Game;
import com.company.StaticBodies.DeathPlatform;
import com.company.StaticBodies.Platform;
import com.company.StaticBodies.SpringBoard;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

/**
 * Level 1 of the game
 */
public class Level1 extends GameLevel {

    private static final int NUM_COINS = 10;

    private static SoundClip gameMusic;
    private static Eye eye;
    private static Eye eye1;
    private static DeathPlatform deathPlatform;
    private static PowerUp powerUp;

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        //MUSIC

        try {
            gameMusic = new SoundClip("data/Sounds/Soundtracks/Level1.wav");
            gameMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        deathPlatform = new DeathPlatform(this, game, getPlayer(), 400f, 1f, 200f, -20f);


        //GROUND

        BodyImage groundBI = new BodyImage("data/Level1/ground-tile.png", 2f);
        BodyImage undergroundBI = new BodyImage("data/Level1/dark-medium-square.png", 4f);

        for (int i = 0; i <= 20; i += 10) {
            Platform ground1 = new Platform(this, groundBI, 5, 1, -15 + i, -8f);
        }

        for (int i = 0; i <= 28; i+= 3.5) {
            GhostlyImage underground1 = new GhostlyImage(this, 2, 2, -19+i, -10.5f, undergroundBI);
        }

//        Platform ground2 = new Platform(this, groundBI, 5, 1, 25, -11.5f);

        Platform ground4 = new Platform(this, groundBI, 5, 1, 50, -7f);

        for (int i = 0; i <= 7; i+= 2) {
            GhostlyImage underground1 = new GhostlyImage(this, 2, 2, 47+i, -9.5f, undergroundBI);
            GhostlyImage underground2 = new GhostlyImage(this, 2, 2, 47+i, -11.5f, undergroundBI);
        }


        for (int i = 0; i <= 10; i += 10) {
            Platform ground6 = new Platform(this, groundBI, 5, 1, 70 + i, -7);
        }

        for (int i = 0; i <= 16; i+= 2) {
            GhostlyImage underground1 = new GhostlyImage(this, 2, 2, 67+i, -9.5f, undergroundBI);
            GhostlyImage underground2 = new GhostlyImage(this, 2, 2, 67+i, -11.5f, undergroundBI);
        }

        for (int i = 0; i <= 20; i += 10) {
            Platform ground5 = new Platform(this, groundBI, 5, 1, 107.5f + i, -9);
        }

        for (int i = 0; i <= 26; i+= 2) {
            GhostlyImage underground1 = new GhostlyImage(this, 2, 2, 104.5f+i, -10.3f, undergroundBI);
        }


        //PLATFORMS

        BodyImage platform1BI = new BodyImage("data/Level1/small-square-tile.png", 2f);
        BodyImage platform2BI = new BodyImage("data/Level1/horizontal-parallels-tile.png", 3f);
        BodyImage platform3BI = new BodyImage("data/Level1/small-square-2-tile.png", 2.5f);
        BodyImage platform4BI = new BodyImage("data/Level1/big-square-with-hole.png", 4f);
        BodyImage platform45BI = new BodyImage("data/Level1/big-square-with-hole.png", 3.5f);
        BodyImage platform5BI = new BodyImage("data/Level1/Small-horizontal-parallels.png", 2f);
        BodyImage platform6BI = new BodyImage("data/Level1/large-inverted-square.png", 3.5f);
        BodyImage platform7BI = new BodyImage("data/Level1/extra-small-square.png", 1.5f);


        //platform 1
        Platform platform1 = new Platform(this, platform1BI, 1, 1, -10, -4f);

        //platform 2
        Platform platform2 = new Platform(this, platform2BI, 3.5f, 1.5f, -2, -2);

        //platform 3
        Platform platform3 = new Platform(this,platform3BI, 1.25f, 1.25f, 5f, 2f);

        //platform 6
        Platform platform4 = new Platform(this, platform4BI, 2, 2, 14, -3);

        //platform 13
        Platform platform13 = new Platform(this, platform3BI, 1.25f, 1.25f, 24, -9);

        //platform 5
        Platform platform5 = new Platform(this, platform5BI, 2, 1, 34, -3f);

        //platform 6
        Platform platform6 = new Platform(this, platform45BI, 1.75f, 1.75f, 70, -1.5f);

        //platform 7
        Platform platform7 = new Platform(this, platform2BI, 3.5f, 1.5f, 85, 2.5f);

        //platform 8
        Platform platform8 = new Platform(this, platform5BI, 2, 1, 96, 0);

        //platform 9
        Platform platform9 = new Platform(this, platform7BI, 0.75f, 0.75f, 90, -6);

        //platform 10
        Platform platform10 = new Platform(this, platform7BI, 0.75f, 0.75f, 94, -8);

        //platform 11
        Platform platform11 = new Platform(this, platform7BI, 0.75f, 0.75f, 98, -10);

        //platform 12
        for (int i = 0; i < 8; i+=3.9f) {
            Platform platform12 = new Platform(this, platform5BI, 2f, 1f, i+52, 5);
        }

        //platform 14
        Platform platform14 = new Platform(this, platform5BI, 2f, 1f, 120, -3f);

        //platform 15
        Platform platform15 = new Platform(this, platform7BI, 0.75f, 0.75f, 114, -4.5f);

        //platform 16
        Platform platform16 = new Platform(this, platform7BI, 0.75f, 0.75f, 113, 0.5f);


        //SPRINGBOARDS

        //springboard 1
        SpringBoard springBoard1 = new SpringBoard(this, getPlayer(), 24f, -7f, 1.2f);


        //COLLECTIBLES

        for (int i = 0; i < 3; i++) {
            Coin coins1 = new Coin(this, getPlayer(), getWasp(), i*2-5, 0);
        }

        DamageUnit damageUnit1 = new DamageUnit(this, getPlayer(), getWasp(), 1f, 0);

        //PowerUp for platform 5
        powerUp = new PowerUp(this, getPlayer(), getWasp(), 34, -1.5f);

        //DamageUnit
        for (int i = 0; i < 6; i++) {
            DamageUnit damageUnit = new DamageUnit(this, getPlayer(), getWasp(), i*2-5, -9.5f);
        }

        for (int i = 0; i < 6; i++) {
            DamageUnit coins2 = new DamageUnit(this, getPlayer(), getWasp(), i*2-3, -6.5f);
        }

        for (int i = 0; i < 2; i++) {
            Coin coins2 = new Coin(this, getPlayer(), getWasp(), i*2+13, -0.5f);
        }

        for (int i = 0; i < 2; i++) {
            DamageUnit damageUnit2 = new DamageUnit(this, getPlayer(), getWasp(), i*2+86, 5);
        }

        for (int i = 0; i < 4; i++) {
            Coin coins2 = new Coin(this, getPlayer(), getWasp(), i*2.5f+74.5f, -5);
        }

        for (int i = 0; i < 2; i++) {
            DamageUnit dunits = new DamageUnit(this, getPlayer(), getWasp(), i*2.5f+69.5f, -5);
        }

        for (int i = 0; i < 2; i++) {
            Coin coins3 = new Coin(this, getPlayer(), getWasp(), i*2+82, 5);
        }

        DamageUnit damageUnit2 = new DamageUnit(this, getPlayer(), getWasp(), 90, -4);
        DamageUnit damageUnit3 = new DamageUnit(this, getPlayer(), getWasp(), 94, -5);
        DamageUnit damageUnit4 = new DamageUnit(this, getPlayer(), getWasp(), 98, -7);
        DamageUnit damageUnit5 = new DamageUnit(this, getPlayer(), getWasp(), 95, 1);
        DamageUnit damageUnit6 = new DamageUnit(this, getPlayer(), getWasp(), 97, 1);

        for (int i = 0; i < 3; i++) {
            Coin coins2 = new Coin(this, getPlayer(), getWasp(), i*2+55, 6);
        }

        for (int i = 0; i < 2; i++) {
            DamageUnit dunits = new DamageUnit(this, getPlayer(), getWasp(), i*2+51, 6);
        }

        DoubleCoin doubleCoin = new DoubleCoin(this, getPlayer(), getWasp(), 113, 1.5f);
        DoubleCoin doubleCoin1 = new DoubleCoin(this, getPlayer(), getWasp(), 114, -3f);
        DoubleCoin doubleCoin2 = new DoubleCoin(this, getPlayer(), getWasp(), 120, -1.5f);

        //IMAGES

        //START ARROW
        BodyImage arrowRightImage = new BodyImage("data/Images/sign-right.png", 2);
        GhostlyImage arrowRight = new GhostlyImage(this, 1, 1, -14, -6.2f, arrowRightImage);

        //W Key
        BodyImage wKeyImage = new BodyImage("data/Images/WKEY.png", 1f);
        GhostlyImage wKey = new GhostlyImage(this, 0.5f, 0.5f, -16, 2.5f, wKeyImage);

        //A Key
        BodyImage aKeyImage = new BodyImage("data/Images/AKEY.png", 1f);
        GhostlyImage aKey = new GhostlyImage(this, 0.5f, 0.5f, -17.5f, 1f, aKeyImage);

        //D Key
        BodyImage dKeyImage = new BodyImage("data/Images/DKEY.png", 1f);
        GhostlyImage dKey = new GhostlyImage(this, 0.5f, 0.5f, -14.5f, 1f, dKeyImage);

        //Warning
        BodyImage warningImage = new BodyImage("data/Images/warning.png", 1.4f);
        GhostlyImage warning = new GhostlyImage(this, 0.7f, 0.7f, 1f, 2.7f, warningImage);

        //PowerUp
        BodyImage powerupImage = new BodyImage("data/Images/lightning-bolt.png", 1.4f);
        GhostlyImage powerup = new GhostlyImage(this, 0.7f, 0.7f, 34f, 1.5f, powerupImage);

        //ENEMIES

        //Eye
        eye = new Eye(this, getPlayer(), 10);
        eye1 = new Eye(this, getPlayer(), 110);

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-16, -6.5f);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(127f, -6f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCount() >= NUM_COINS;
    }

    @Override
    public ImageIcon level1Background() {
        return new ImageIcon("data/Backgrounds/level1-background.gif");
    }

    @Override
    public ImageIcon level2Background() {return null;}

    @Override
    public ImageIcon level3Background() {return null;}

    @Override
    public ImageIcon level4Background() {return null;}

    @Override
    public SoundClip level1Sound() {
        return gameMusic;
    }

    @Override
    public SoundClip level2Sound() {return null;}

    @Override
    public SoundClip level3Sound() {return null;}

    @Override
    public SoundClip level4Sound() {return null;}

    public static SoundClip getGameMusic() {
        return gameMusic;
    }

    public static Eye getEye() {
        return eye;
    }

    public static Eye getEye1() {
        return eye1;
    }

    public static PowerUp getPowerUp() {
        return powerUp;
    }
}
