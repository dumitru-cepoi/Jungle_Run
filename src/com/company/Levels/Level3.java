package com.company.Levels;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import com.company.AbstractClasses.GameLevel;
import com.company.Collectibles.Coin;
import com.company.Collectibles.DamageUnit;
import com.company.Collectibles.DoubleCoin;
import com.company.Collectibles.PowerUp;
import com.company.Enemies.Eye;
import com.company.Enemies.Yeti;
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

/**
 * Level 3 of the game
 */
public class Level3 extends GameLevel {

    private static final int NUM_COINS = 50;
    private Wasp wasp;
    private DeathPlatform deathPlatform;
    private static Yeti yeti;
    private static Yeti yeti2;
    private static Yeti yeti3;
    private static Eye eye;
    private static Eye eye2;
    private static Eye eye3;
    private static Eye eye4;
    private static SoundClip gameMusic;
    private static PowerUp powerUp;
    private static PowerUp powerUp2;
    private static PowerUp powerUp3;

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        try {
            gameMusic = new SoundClip("data/Sounds/Soundtracks/Level3.wav");   // Open an audio input stream
            gameMusic.loop();  // Set it to continous playback (looping)
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        //WASP

        wasp = new Wasp(this);

        //GROUND

        deathPlatform = new DeathPlatform(this, game, getPlayer(), 400f, 1f, 200f, -20f);

        BodyImage groundBI = new BodyImage("data/Level3/medium-platform-2.png", 2f);
        BodyImage emptyBI = new BodyImage("data/Level3/frz-empty.png", 1f);
        BodyImage emptyBI2 = new BodyImage("data/Level3/frz-slope-bottom1.png", 1f);
        BodyImage rightSide = new BodyImage("data/Level3/frz-right-side.png", 1f);
        BodyImage leftSide = new BodyImage("data/Level3/frz-side.png", 1f);

        for (int i = 0; i < 5; i++) {
            GhostlyImage rightSide1 = new GhostlyImage(this, 0.5f, 0.5f, 0.5f, -8.7f-i-0.2f, rightSide);
        }
        for (int i = 0; i <= 18; i += 6) {
            Platform ground2 = new Platform(this, groundBI, 3f, 1, -20 + i, -8f);
        }
        for (int i = 0; i <= 20; i+= 1) {
            GhostlyImage empty1 = new GhostlyImage(this, 0.5f, 0.5f, -20+i, -9.5f, emptyBI2);
            GhostlyImage empty2 = new GhostlyImage(this, 0.5f, 0.5f, -20+i, -8.5f, emptyBI2);
            for (int j = 0; j < 3; j++) {
                GhostlyImage empty3 = new GhostlyImage(this, 0.5f, 0.5f, -20+i, -10f-j, emptyBI);
            }
        }


        for (int i = 0; i < 5; i++) {
            GhostlyImage rightSide1 = new GhostlyImage(this, 0.5f, 0.5f, 46.5f, -11f-i-0.2f, rightSide);
            GhostlyImage leftSide1 = new GhostlyImage(this, 0.5f, 0.5f, 17.5f, -11f-i-0.2f, leftSide);
        }
        for (int i = 0; i <= 24; i += 6) {
            Platform ground1 = new Platform(this, groundBI, 3f, 1, 20 + i, -10f);
        }
        for (int i = 0; i <= 27; i+= 1) {
            for (int j = 0; j < 2; j++) {
                GhostlyImage empty3 = new GhostlyImage(this, 0.5f, 0.5f, 18.5f+i, -11.5f-j, emptyBI);
            }
        }

        for (int i = 0; i < 8; i++) {
            GhostlyImage rightSide1 = new GhostlyImage(this, 0.5f, 0.5f, 105.5f, -6f-i-0.2f, rightSide);
            GhostlyImage leftSide1 = new GhostlyImage(this, 0.5f, 0.5f, 76.5f, -6f-i-0.2f, leftSide);
        }
        for (int i = 0; i <= 24; i += 6) {
            Platform ground1 = new Platform(this, groundBI, 3f, 1, 79 + i, -5f);
        }
        for (int i = 0; i <= 27; i+= 1) {
            GhostlyImage empty1 = new GhostlyImage(this, 0.5f, 0.5f, 77.5f+i, -6.5f, emptyBI2);
            for (int j = 0; j < 8; j++) {
                GhostlyImage empty3 = new GhostlyImage(this, 0.5f, 0.5f, 77.5f+i, -6f-j, emptyBI);
            }
        }

        for (int i = 0; i < 15; i++) {
            GhostlyImage rightSide1 = new GhostlyImage(this, 0.5f, 0.5f, 163.5f, -3f-i-0.2f, rightSide);
            GhostlyImage leftSide1 = new GhostlyImage(this, 0.5f, 0.5f, 134.5f, -3f-i-0.2f, leftSide);
        }
        for (int i = 0; i <= 24; i += 6) {
            Platform ground1 = new Platform(this, groundBI, 3f, 1, 137 + i, -2f);
        }
        for (int i = 0; i <= 28; i+= 1) {
            GhostlyImage empty1 = new GhostlyImage(this, 0.5f, 0.5f, 135.5f+i, -3.5f, emptyBI2);
            for (int j = 0; j < 20; j++) {
                GhostlyImage empty3 = new GhostlyImage(this, 0.5f, 0.5f, 135.5f+i, -3f-j, emptyBI);
            }
        }


        //PLATFORMS

        BodyImage platform1BI = new BodyImage("data/Level3/small-platform.png", 2f);
        BodyImage platform2BI = new BodyImage("data/Level3/medium-platform.png", 2f);
        BodyImage platform3BI = new BodyImage("data/Level3/medium-platform-2.png", 1.5f);

        Platform platform1 = new Platform(this, platform2BI, 3, 1, 8, -5);
        Platform platform2 = new Platform(this, platform1BI, 0.8f, 1, 14.5f, -7.5f);
        Platform platform3 = new Platform(this, platform1BI, 0.8f, 1, 20, -4.5f);
        Platform platform11 = new Platform(this, platform1BI, 0.8f, 1, 29, 1.5f);
        Platform platform4 = new Platform(this, platform2BI, 3, 1, 42, 4.5f);
        Platform platform5 = new Platform(this, platform1BI, 0.8f, 1, 52, -7.5f);
        Platform platform6 = new Platform(this, platform2BI, 3, 1, 60, -4.5f);
        Platform platform7 = new Platform(this, platform3BI, 2f, 0.75f, 70, -8f);
        Platform platform8 = new Platform(this, platform1BI, 0.8f, 1, 111f, -3f);
        Platform platform9 = new Platform(this, platform3BI, 2f, 0.75f, 118, -7f);
        Platform platform10 = new Platform(this, platform2BI, 3, 1, 128f, 3f);
        for (int i = 0; i < 13; i+= 3) {
            Platform platforms = new Platform(this, platform2BI, 3f, 1f, 91+i, 6f);
        }
        Platform platform13 = new Platform(this, platform1BI, 0.8f, 1, 83f, 1.8f);


        //SPRINGBOARDS

        SpringBoard springBoard1 = new SpringBoard(this, getPlayer(), 70f, -6.5f, 1.1f);
        SpringBoard springBoard2 = new SpringBoard(this, getPlayer(), 118f, -5.5f, 1.3f);


        //ENEMIES

        yeti = new Yeti(this, getPlayer(), 39, -7.4f);
        yeti2 = new Yeti(this, getPlayer(), 80, -2.4f);
        yeti3 = new Yeti(this, getPlayer(), 140, 0.6f);
        eye = new Eye(this, getPlayer(), 17);
        eye2 = new Eye(this, getPlayer(), 45);
        eye3 = new Eye(this, getPlayer(), 65);
        eye4 = new Eye(this, getPlayer(), 79);


        //COLLECTIBLES

        powerUp = new PowerUp(this, getPlayer(), getWasp(), 8, -3.5f);
        powerUp2 = new PowerUp(this, getPlayer(), getWasp(), 60, -3f);
        powerUp3 = new PowerUp(this, getPlayer(), getWasp(), 130, 4.5f);

        DoubleCoin doubleCoin = new DoubleCoin(this, getPlayer(), getWasp(), 42, 6f);

        for (int i = 0; i < 5; i++) {
            Coin coins = new Coin(this, getPlayer(), getWasp(), i*2-10, -6.5f);
        }
        Coin coin = new Coin(this, getPlayer(), getWasp(), 14.5f, -5.5f);
        Coin coin1 = new Coin(this, getPlayer(), getWasp(), 20f, -2.5f);
        Coin coin2 = new Coin(this, getPlayer(), getWasp(), 29f, 3.5f);
        Coin coin3 = new Coin(this, getPlayer(), getWasp(), 40f, 6.5f);
        Coin coin4 = new Coin(this, getPlayer(), getWasp(), 52f, -5.5f);
        Coin coin5 = new Coin(this, getPlayer(), getWasp(), 58f, -2.5f);
        Coin coin6 = new Coin(this, getPlayer(), getWasp(), 62f, -2.5f);
        DamageUnit dunit = new DamageUnit(this, getPlayer(), getWasp(), 44f, 6.5f);
        DamageUnit dunit1 = new DamageUnit(this, getPlayer(), getWasp(), 111f, -1.5f);
        for (int i = 0; i < 5; i++) {
            Coin coins = new Coin(this, getPlayer(), getWasp(), i*2+89, 7.5f);
        }
        for (int i = 0; i < 2; i++) {
            DamageUnit dunits = new DamageUnit(this, getPlayer(), getWasp(), i*2+99, 7.5f);
        }
        DoubleCoin doubleCoin1 = new DoubleCoin(this, getPlayer(), getWasp(), 104, 7.5f);
        DoubleCoin doubleCoin2 = new DoubleCoin(this, getPlayer(), getWasp(), 126, 4.5f);
        DamageUnit dunit2 = new DamageUnit(this, getPlayer(), getWasp(), 128f, 4f);


        //IMAGES

        //Start Arrow
        BodyImage arrowRightImage = new BodyImage("data/Images/sign-right.png", 2);
        GhostlyImage arrowRight = new GhostlyImage(this, 1, 1, -14, -6f, arrowRightImage);

        //Trees
        BodyImage frozenTree1 = new BodyImage("data/Images/frozen-tree1.png", 4);
        BodyImage frozenTree2 = new BodyImage("data/Images/frozen-tree2.png", 4);
        BodyImage frozenPine = new BodyImage("data/Images/pine-snow.png", 7);

        GhostlyImage frozenTree21 = new GhostlyImage(this, 2f, 2f, -4, -5.5f, frozenTree2);
        GhostlyImage frozenTree22 = new GhostlyImage(this, 2f, 2f, 102, 8.5f, frozenTree2);
        GhostlyImage frozenTree23 = new GhostlyImage(this, 2f, 2f, 152, 0.5f, frozenTree2);
        GhostlyImage pineSnow1 = new GhostlyImage(this, 3f, 3.5f, 35, -5.5f, frozenPine);
        GhostlyImage frozenTree11 = new GhostlyImage(this, 2f, 2f, 85f, -2.5f, frozenTree1);


        //Ice
        BodyImage stalactite = new BodyImage("data/Images/stalactite1.png", 1.5f);
        BodyImage crystal = new BodyImage("data/Images/crystal.png", 2);

        GhostlyImage stalactite1 = new GhostlyImage(this, 1.5f, 0.75f, 41.5f, 3, stalactite);
        GhostlyImage stalactite2 = new GhostlyImage(this, 1.5f, 0.75f, 61, -6f, stalactite);
        GhostlyImage stalactite3 = new GhostlyImage(this, 1.5f, 0.75f, 127.5f, 1.5f, stalactite);
        GhostlyImage stalactite4 = new GhostlyImage(this, 1.5f, 0.75f, 129, 1.5f, stalactite);
        GhostlyImage crystal1 = new GhostlyImage(this, 1, 1, 100, -3f, crystal);
        GhostlyImage crystal2 = new GhostlyImage(this, 1, 1, 9.3f, -3f, crystal);

        //Piles
        BodyImage snowPile = new BodyImage("data/Images/snow-pile.png", 1.5f);
        BodyImage iceStone = new BodyImage("data/Images/ice-stone.png", 1.5f);

        GhostlyImage snowPile1 = new GhostlyImage(this, 3, 0.75f, 143, -1.25f, snowPile);
        GhostlyImage snowPile2 = new GhostlyImage(this, 3, 0.75f, 25, -8.5f, snowPile);
        GhostlyImage snowPile3 = new GhostlyImage(this, 3, 0.75f, 36, -8.5f, snowPile);
        GhostlyImage snowPile4 = new GhostlyImage(this, 3, 0.75f, 40, -8.5f, snowPile);
        GhostlyImage snowPile5 = new GhostlyImage(this, 3, 0.75f, -5, -6.5f, snowPile);
        GhostlyImage snowPile6 = new GhostlyImage(this, 3, 0.75f, -12, -6.5f, snowPile);
        GhostlyImage snowPile7 = new GhostlyImage(this, 3, 0.75f, 95, 7.5f, snowPile);
        GhostlyImage snowPile8 = new GhostlyImage(this, 3, 0.75f, 100, 7.5f, snowPile);
        GhostlyImage snowPile9 = new GhostlyImage(this, 3, 0.75f, 90, -3.5f, snowPile);
        GhostlyImage iceStone1 = new GhostlyImage(this, 3, 0.75f, 85, -3.75f, iceStone);
        GhostlyImage iceStone2 = new GhostlyImage(this, 3, 0.75f, 142, -0.75f, iceStone);

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-16, -6);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(160, 1f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCount() >= NUM_COINS && yeti3.getHealthPoints() == 0;
    }

    @Override
    public ImageIcon level1Background() {return null;}

    @Override
    public ImageIcon level2Background() {return null;}

    @Override
    public ImageIcon level3Background() {
        return new ImageIcon("data/Backgrounds/level3-background.gif");
    }

    @Override
    public ImageIcon level4Background() {return null;}

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
        return gameMusic;
    }

    @Override
    public SoundClip level4Sound() {
        return null;
    }

    public static SoundClip getGameMusic() {
        return gameMusic;
    }

    public static Yeti getYeti() {
        return yeti;
    }

    public static PowerUp getPowerUp() {
        return powerUp;
    }

    public static PowerUp getPowerUp2() {
        return powerUp2;
    }

    public static PowerUp getPowerUp3() {
        return powerUp3;
    }

    public static Eye getEye() {
        return eye;
    }

    public static Eye getEye2() {
        return eye2;
    }

    public static Eye getEye3() {
        return eye3;
    }

    public static Eye getEye4() {
        return eye4;
    }

    public static Yeti getYeti2() {
        return yeti2;
    }

    public static Yeti getYeti3() {
        return yeti3;
    }
}
