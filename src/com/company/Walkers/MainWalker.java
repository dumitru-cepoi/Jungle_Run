package com.company.Walkers;

import city.cs.engine.*;
import com.company.Main.Game;

public class MainWalker extends Walker {

    private static final Shape shape = new PolygonShape(
            0.546f,1.24f, 0.691f,0.575f, 0.596f,-0.69f, 0.336f,-1.245f, -0.359f,-1.245f, -0.689f,-0.705f, -0.689f,0.14f, -0.494f,1.24f);

    private static BodyImage idleImage = new BodyImage("data/Player/Player/idle.gif", 2.5f);
    private static BodyImage jumpImage = new BodyImage("data/Player/Player/mid air.gif", 2.5f);
    private static BodyImage runImage = new BodyImage("data/Player/Player/run.gif", 2.5f);

    private int coinCount;
    private int healthPoints;
    private Game game;
    private static boolean gameOver = false;
    private static int saveScore;

    public MainWalker(World world, Game game) {
        super(world, shape);
        addImage(idleImage);
        this.setGravityScale(3.5f);
        this.game = game;
        coinCount = 160;
        healthPoints = 6;
    }

    public void incrementCoinCount() {
        coinCount++;
    }

    public void doubleCoinIncrement() {
        coinCount += 5;
    }

    public void decrementCoinCount() {
        if (coinCount > 0) {
            coinCount--;
        }
    }

    public void reduceLife() {
        healthPoints--;
    }

    public void incrementLife() {
        if (healthPoints < 6) {
            healthPoints++;
        }
    }

    public int getCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public BodyImage getRunImage(){
        return runImage;
    }

    public BodyImage getJumpImage() {
        return jumpImage;
    }

    public BodyImage getIdleImage() {
        return idleImage;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void dead() {
        if (healthPoints == 0) {
            saveScore = coinCount;
            game.save();
            gameOver = true;
            game.getWorld().stop();
            game.stopLevelTimersAndMusic();
            game.getFrame().dispose();
            new Game();
        }
    }

    public static int getSaveScore() {
        return saveScore;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void gameOverFalse() {
        gameOver = false;
    }
}
