package com.company.Levels;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import com.company.AbstractClasses.GameLevel;
import com.company.Collectibles.Coin;
import com.company.Collectibles.DamageUnit;
import com.company.Collectibles.DoubleCoin;
import com.company.Enemies.Eye;
import com.company.Enemies.Slime;
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
 * Level 2 of the game
 */
public class Level2 extends GameLevel {

    private static final int NUM_COINS = 50;
    private Wasp wasp;
    private static Slime slime;
    private static Slime slime2;
    private static Eye eye;
    private static Eye eye2;
    private static Eye eye3;
    private static DeathPlatform deathPlatform;
    private static SoundClip gameMusic;

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        //MUSIC

        try {
            gameMusic = new SoundClip("data/Sounds/Soundtracks/Level2.wav");
            gameMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        //WASP

        wasp = new Wasp(this);

        //GROUND

        deathPlatform = new DeathPlatform(this, game, getPlayer(), 400f, 1f, 200f, -20f);

        BodyImage groundBI = new BodyImage("data/Level2/grass-ground.png", 2f);
        BodyImage emptyBI = new BodyImage("data/Level2/grass-empty.png", 1f);
        BodyImage emptyBI2 = new BodyImage("data/Level2/grass-empty2.png", 1f);
        BodyImage rightSide = new BodyImage("data/Level2/grass-side-right.png", 1f);
        BodyImage leftSide = new BodyImage("data/Level2/grass-side.png", 1f);

        for (int i = 0; i < 5; i++) {
            GhostlyImage rightSide1 = new GhostlyImage(this, 0.5f, 0.5f, 2, -8.7f-i-0.2f, rightSide);
        }
        for (int i = 0; i <= 20; i += 5.5) {
            Platform ground1 = new Platform(this, groundBI, 3, 1, -20 + i, -8f);
        }
        for (int i = 0; i <= 21; i+= 1) {
            GhostlyImage empty1 = new GhostlyImage(this, 0.5f, 0.5f, -20+i, -9.5f, emptyBI2);
            for (int j = 0; j < 3; j++) {
                GhostlyImage empty2 = new GhostlyImage(this, 0.5f, 0.5f, -20+i, -10f-j, emptyBI);
            }
        }


        for (int i = 0; i < 9; i++) {
            GhostlyImage leftSide1 = new GhostlyImage(this, 0.5f, 0.5f, 32, -4f-i-0.2f, leftSide);
            GhostlyImage rightSide2 = new GhostlyImage(this, 0.5f, 0.5f, 66f, -4f-i-0.2f, rightSide);
        }
        for (int i = 0; i <= 30; i += 5.5) {
            Platform ground2 = new Platform(this, groundBI, 3, 1, 34 + i, -3f);
        }
        for (int i = 0; i <= 34; i+= 1) {
            for (int j = 0; j < 2; j++) {
                GhostlyImage empty1 = new GhostlyImage(this, 0.5f, 0.5f, 32f+i, -4.5f-j, emptyBI2);
            }
        }
        for (int i = 0; i <= 34; i+= 1) {
            for (int j = 0; j < 9; j++) {
                GhostlyImage empty3 = new GhostlyImage(this, 0.5f, 0.5f, 32f+i, -4.5f-j, emptyBI);
            }
        }


        for (int i = 0; i < 5; i++) {
            GhostlyImage leftSide1 = new GhostlyImage(this, 0.5f, 0.5f, 123, -8f-i-0.2f, leftSide);
            GhostlyImage rightSide2 = new GhostlyImage(this, 0.5f, 0.5f, 157f, -8f-i-0.2f, rightSide);
        }
        for (int i = 0; i <= 30; i += 5.5) {
            Platform ground3 = new Platform(this, groundBI, 3, 1, 125 + i, -7.5f);
        }
        for (int i = 0; i <= 34; i+= 1) {
            GhostlyImage empty1 = new GhostlyImage(this, 0.5f, 0.5f, 123f+i, -9f, emptyBI2);
        }
        for (int i = 0; i <= 34; i+= 1) {
            for (int j = 0; j < 4; j++) {
                GhostlyImage empty3 = new GhostlyImage(this, 0.5f, 0.5f, 123f+i, -9.5f-j, emptyBI);
            }
        }


        //PLATFORMS

        BodyImage platform1BI = new BodyImage("data/Level2/extra-small-platform.png", 2f);
        BodyImage platform2BI = new BodyImage("data/Level2/medium-platform.png", 2f);
        BodyImage platform3BI = new BodyImage("data/Level2/medium-stone-platform.png", 1.5f);

        Platform platform1 = new Platform(this, platform1BI, 0.8f, 1, 6, -4);
        Platform platform2 = new Platform(this, platform2BI, 2.5f, 1, 13, -1);
        Platform platform3 = new Platform(this, platform3BI, 2, 0.75f, 23, -8.5f);
        Platform platform4 = new Platform(this, platform1BI, 0.8f, 1, 44, 1f);
        for (int i = 0; i < 13; i+= 3) {
            Platform platform5 = new Platform(this, platform3BI, 2f, 0.75f, 50+i, 5f);
        }
        Platform platform6 = new Platform(this, platform2BI, 2.5f, 1, 75, -6);
        Platform platform7 = new Platform(this, platform1BI, 0.8f, 1, 82, -3);
        Platform platform8 = new Platform(this, platform3BI, 2f, 0.75f, 90, -10.5f);
        Platform platform9 = new Platform(this, platform2BI, 2.5f, 1f, 100, 5f);
        Platform platform10 = new Platform(this, platform1BI, 0.8f, 1, 107.5f, 8);
        Platform platform11 = new Platform(this, platform1BI, 0.8f, 1, 67f, 8);
        Platform platform12 = new Platform(this, platform3BI, 2, 0.75f, 115, 3.5f);
        Platform platform13 = new Platform(this, platform1BI, 0.8f, 1f, 132, -3.5f);
        Platform platform14 = new Platform(this, platform1BI, 0.8f, 1f, 137, -1.5f);
        Platform platform15 = new Platform(this, platform1BI, 0.8f, 1f, 143, 1.5f);


        //SPRINGBOARDS

        SpringBoard springBoard1 = new SpringBoard(this, getPlayer(), 23f, -7f, 1.07f);
        SpringBoard springBoard2 = new SpringBoard(this, getPlayer(), 90f, -9f, 1.25f);
        SpringBoard springBoard3 = new SpringBoard(this, getPlayer(), 115f, 5f, 0.8f);


        //ENEMIES

        //Eye
        eye = new Eye(this, getPlayer(), 10);
        eye2 = new Eye(this, getPlayer(), 71);
        eye3 = new Eye(this, getPlayer(), 125);

        //Slime
        slime = new Slime(this, getPlayer(), 50, 6.5f);
        slime2 = new Slime(this, getPlayer(), 150, -5f);


        //COLLECTIBLES

        DoubleCoin doubleCoin1 = new DoubleCoin(this, getPlayer(), getWasp(), 63, 6.5f);
        DoubleCoin doubleCoin2 = new DoubleCoin(this, getPlayer(), getWasp(), 67, 10f);
        DoubleCoin doubleCoin3 = new DoubleCoin(this, getPlayer(), getWasp(), 107.5f, 9.5f);
        DoubleCoin doubleCoin4 = new DoubleCoin(this, getPlayer(), getWasp(), 143f, 5f);

        for (int i = 0; i < 5; i++) {
            Coin coins1 = new Coin(this, getPlayer(), getWasp(), i*2.5f-10, -6);
        }

        for (int i = 0; i < 2; i++) {
            Coin coins2 = new Coin(this, getPlayer(), getWasp(), i*2+12, 1);
        }

        for (int i = 0; i < 2; i++) {
            DamageUnit coins2 = new DamageUnit(this, getPlayer(), getWasp(), i*2+35, -2f);
        }

        for (int i = 0; i < 3; i++) {
            Coin coins2 = new Coin(this, getPlayer(), getWasp(), i*2+51.5f, -2);
            Coin coin3 = new Coin(this, getPlayer(), getWasp(), 59.5f, -2);
        }

        for (int i = 0; i < 2; i++) {
            Coin coins2 = new Coin(this, getPlayer(), getWasp(), i*2+74, -5);
        }

        Coin coins1 = new Coin(this, getPlayer(), getWasp(), 100, 6);

        Coin coins2 = new Coin(this, getPlayer(), getWasp(), 137, 6);

        DamageUnit dunit = new DamageUnit(this, getPlayer(), getWasp(), 57.5f, -2f);


        //IMAGES

        //Start Arrow
        BodyImage arrowRightImage = new BodyImage("data/Images/sign-right.png", 2);
        GhostlyImage arrowRight = new GhostlyImage(this, 1, 1, -14, -6f, arrowRightImage);

        //Clouds
        BodyImage cloudsBI1 = new BodyImage("data/Level2/clouds-1.png", 2);
        BodyImage cloudsBI2 = new BodyImage("data/Level2/clouds-2.png", 2);
        BodyImage cloudsBI3 = new BodyImage("data/Level2/clouds-3.png", 1f);
        BodyImage cloudsBI4 = new BodyImage("data/Level2/clouds-4.png", 1f);

        GhostlyImage clouds1 = new GhostlyImage(this, 4, 1, -2.5f, 10f, cloudsBI1);
        GhostlyImage clouds2 = new GhostlyImage(this, 4, 1, 26f, 10f, cloudsBI4);
        GhostlyImage clouds3 = new GhostlyImage(this, 4, 1, 77f, 9f, cloudsBI2);
        GhostlyImage clouds4 = new GhostlyImage(this, 4, 1, 93f, 8f, cloudsBI4);
        GhostlyImage clouds5 = new GhostlyImage(this, 4, 1, 105f, -2f, cloudsBI4);
        GhostlyImage clouds6 = new GhostlyImage(this, 4, 1, 132.5f, 10f, cloudsBI1);

        //Mouse Icon
        BodyImage mouseImage = new BodyImage("data/Images/left-click.png", 1.5f);
        GhostlyImage mouse = new GhostlyImage(this, 0.75f, 0.75f, -16, 1.5f, mouseImage);

        //Trees
        BodyImage tree1Image = new BodyImage("data/Images/tree1.png", 8f);
        BodyImage tree2Image = new BodyImage("data/Images/tree2.png", 6f);
        GhostlyImage tree1 = new GhostlyImage(this, 3f, 2.5f, 39, 1f, tree1Image);
        GhostlyImage tree2 = new GhostlyImage(this, 2f, 3f, -7, -4f, tree2Image);

        //Stone
        BodyImage stone2Image = new BodyImage("data/Images/stone2.png", 1.5f);
        BodyImage stone1Image = new BodyImage("data/Images/stone1.png", 1.5f);
        GhostlyImage stone1 = new GhostlyImage(this, 2f, 0.75f, 98.5f, 6f, stone2Image);
        GhostlyImage stone2 = new GhostlyImage(this, 2f, 0.75f, 140f, -6f, stone2Image);
        GhostlyImage stone3 = new GhostlyImage(this, 2f, 0.75f, 76.5f, -5f, stone2Image);

        //Bushes
        BodyImage bush1Image = new BodyImage("data/Images/long-bush-1.png", 1.5f);
        BodyImage bush2Image = new BodyImage("data/Images/medium-bush.png", 1.5f);
        GhostlyImage bush1 = new GhostlyImage(this, 3f, 0.75f, 129f, -6.5f, bush1Image);


    }


    @Override
    public Vec2 startPosition() {
        return new Vec2(-16, -6);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(154f, -4.5f);
    }

    @Override
    public boolean isCompleted() {
        if (getPlayer().getCount() >= NUM_COINS && slime2.getHealthPoints() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public ImageIcon level1Background() {return null;}

    @Override
    public ImageIcon level2Background() {
        return new ImageIcon("data/Backgrounds/level2-background.gif");
    }

    @Override
    public ImageIcon level3Background() {return null;}

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
        return gameMusic;
    }

    @Override
    public SoundClip level3Sound() {
        return null;
    }

    @Override
    public SoundClip level4Sound() {
        return null;
    }

    public static SoundClip getGameMusic() {
        return gameMusic;
    }

    public static Slime getSlime() {
        return slime;
    }

    public static Slime getSlime2() {
        return slime2;
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
}
