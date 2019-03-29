package com.company.Main;

import com.company.AbstractClasses.GameLevel;
import com.company.Controllers.Controller;
import com.company.Controllers.Controller2;
import com.company.EnemyAttacks.EyeAttack;
import com.company.EnemyAttacks.ShadowAttack;
import com.company.EnemyAttacks.SlimeAttack;
import com.company.EnemyAttacks.YetiAttack;
import com.company.FileReaderAndWriter.HighScoreReader;
import com.company.FileReaderAndWriter.HighScoreWriter;
import com.company.Focus.GiveFocus;
import com.company.Levels.Level1;
import com.company.Levels.Level2;
import com.company.Levels.Level3;
import com.company.Levels.Level4;
import com.company.MyView.MyView;
import com.company.Panels.ControlPanel;
import com.company.Panels.GameOver;
import com.company.Panels.MainMenu;
import com.company.Panels.TheEnd;
import com.company.Sting.StingAttack;
import com.company.Trackers.*;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;

public class Game {

    private GameLevel world;
    private MyView view;
    private MainMenu menu;
    private GameOver gameOver;
    private TheEnd theEnd;
    private HighScoreReader reader;
    private int level;
    private Controller controller;
    private Controller2 controller2;
    private StingAttack sa;
    private JFrame frame;
    private EyeAttack ea;
    private EyeAttack ea1;
    private EyeAttack ea2;
    private EyeAttack ea3;
    private SlimeAttack slimeAttack;
    private SlimeAttack slimeAttack2;
    private YetiAttack yetiAttack;
    private YetiAttack yetiAttack2;
    private YetiAttack yetiAttack3;
    private ShadowAttack shadowAttack;
    private ShadowAttack shadowAttack2;
    private int lastScore;
    public final int WIDTH = 800;
    public final int HEIGHT = 480;

    public Game() {

        frame = new JFrame("Jungle Run");

        if (MainWalker.isGameOver()) {
            gameOver = new GameOver(new ImageIcon("data/Backgrounds/menu-background.jpg").getImage(), this);
            frame.add(gameOver);
            frame.setPreferredSize(new Dimension(800, 550));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
            frame.requestFocus();
        } else {
            menu = new MainMenu(new ImageIcon("data/Backgrounds/menu-background.jpg").getImage(), this);
            frame.add(menu);
            frame.setPreferredSize(new Dimension(800, 550));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
            frame.requestFocus();
        }

    }

    /** The player in the current level. */
    public MainWalker getPlayer() {
        return world.getPlayer();
    }

    /** The wasp in the current level. */
    public Wasp getWasp() {
        return world.getWasp();
    }

    /** Is the current level of the game finished? */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    /** Advance to the next level of the game. */
    public void goNextLevel() {

        if (level == 4) {
            save();
            lastScore = getPlayer().getCount();
            getWorld().stop();
            stopLevelTimersAndMusic();
            getFrame().dispose();
            frame = new JFrame("Jungle Run");
            theEnd = new TheEnd(new ImageIcon("data/Backgrounds/menu-background.jpg").getImage(), this);
            frame.add(theEnd);
            frame.setPreferredSize(new Dimension(800, 550));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
            frame.requestFocus();
        } else if (level == 1) {
            level2InGame();
        } else if (level == 2) {
            level3InGame();
        } else if (level == 3) {
            level4InGame();
        }
    }

    public void setFrame() {
        Container buttons = new ControlPanel(this);
        frame.add(buttons, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (MainWalker.isGameOver()) {
            frame.remove(gameOver);
        } else {
            frame.remove(menu);
        }

        frame.add(view);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
        view.addMouseListener(new GiveFocus(frame));
    }

    public void level1InMenu() {
        level = 1;
        world = new Level1();
        world.populate(this);

        view = new MyView(world, MyView.background, WIDTH, HEIGHT, world.getPlayer(), Color.BLACK);
        setFrame();

        //Player Tracker
        world.addStepListener(new Tracker(view, world.getPlayer(), 0, 110f));

        //Enemy Trackers
        world.addStepListener(new EyeTracker(view, Level1.getEye(), 3, -3, -10, 10));
        world.addStepListener(new EyeTracker(view, Level1.getEye1(), 3, -3, 110, 130));

        //Enemy Attacks
        ea = new EyeAttack(view, Level1.getEye(), world.getPlayer(), -16, 10, 2000);
        ea1 = new EyeAttack(view, Level1.getEye1(), world.getPlayer(), 100, 130, 2000);

        //Player Controller
        controller2 = new Controller2(world.getPlayer());
        frame.addKeyListener(controller2);

        Controller2.JUMPING_SPEED = 18;
        Controller2.WALKING_SPEED = 6;
        Controller.JUMPING_SPEED = 18;
        Controller.WALKING_SPEED = 6;

        //Set the background
        MyView.background = world.level1Background().getImage();

        view.setWorld(world);

        world.start();
    }

    public void level1() {
        level = 1;
        world = new Level1();
        world.populate(this);

        setFrame();

        //switch the player in view to the current world player

        //Player Tracker
        world.addStepListener(new Tracker(view, world.getPlayer(), 0, 110f));

        //Enemy Trackers
        world.addStepListener(new EyeTracker(view, Level1.getEye(), 3, -3, -10, 10));
        world.addStepListener(new EyeTracker(view, Level1.getEye1(), 3, -3, 110, 130));

        //Enemy Attacks
        ea = new EyeAttack(view, Level1.getEye(), world.getPlayer(), -16, 10, 2000);
        ea1 = new EyeAttack(view, Level1.getEye1(), world.getPlayer(), 100, 130, 2000);

        //Player Controller
        controller2 = new Controller2(world.getPlayer());
        frame.addKeyListener(controller2);

        Controller2.JUMPING_SPEED = 18;
        Controller2.WALKING_SPEED = 6;
        Controller.JUMPING_SPEED = 18;
        Controller.WALKING_SPEED = 6;

        //Set the background
        MyView.background = world.level1Background().getImage();

        //Change font color
        view.setColor(Color.BLACK);

        // show the new world in the view
        view.setWorld(world);

        world.start();
    }

    public void level2InGame() {
        view.removeMouseListener(sa);
        frame.removeKeyListener(controller);
        frame.removeKeyListener(controller2);
        stopLevelTimersAndMusic();
        world.stop();
        MainWalker oldPlayer = world.getPlayer();
        level2();
        view.setPlayer(world.getPlayer());
        world.getPlayer().setCoinCount(oldPlayer.getCount());
        world.start();
    }

    public void level2InMenu() {
        level = 2;
        world = new Level2();
        world.populate(this);

        view = new MyView(world, MyView.background, WIDTH, HEIGHT, world.getPlayer(), Color.WHITE);
        setFrame();

        //Player and Wasp Trackers
        world.addStepListener(new Tracker(view, world.getPlayer(), 0f, 140f));
        world.addStepListener(new WaspTracker(view, world.getWasp(), world.getPlayer()));

        //Enemy Trackers
        world.addStepListener(new SlimeTracker(view, world.getPlayer(), Level2.getSlime(), 50, 60));
        world.addStepListener(new SlimeTracker(view, world.getPlayer(), Level2.getSlime2(), 136, 150));
        world.addStepListener(new EyeTracker(view, Level2.getEye(), 4, -4, -15, 6));
        world.addStepListener(new EyeTracker(view, Level2.getEye2(), 3, -3, 71, 87));
        world.addStepListener(new EyeTracker(view, Level2.getEye3(), 6, -6, 130, 150));

        //Enemy Attacks
        slimeAttack = new SlimeAttack(view, Level2.getSlime(), world.getPlayer(), 43, 60);
        slimeAttack2 = new SlimeAttack(view, Level2.getSlime2(), world.getPlayer(), 130, 150);
        ea = new EyeAttack(view, Level2.getEye(), world.getPlayer(), -16, 6, 2000);
        ea1 = new EyeAttack(view, Level2.getEye2(), world.getPlayer(), 67, 87, 2000);
        ea2 = new EyeAttack(view, Level2.getEye3(), world.getPlayer(), 125, 155, 3000);

        //Player and Wasp Controller
        controller = new Controller(world.getPlayer(), world.getWasp());
        frame.addKeyListener(controller);

        //Activate Mouse Listener to shoot stings with Wasp
        sa = new StingAttack(view, world.getWasp(), Level2.getSlime(), slimeAttack, Level2.getSlime2(), slimeAttack2, Level2.getEye(), ea,
                Level2.getEye2(), ea1, Level2.getEye3(), ea2, null, null, null, null, null,
                null, null, null, null, null, null, null, world.getPlayer());
        view.addMouseListener(sa);

        Controller.JUMPING_SPEED = 18;
        Controller.WALKING_SPEED = 6;

        //Set the background
        MyView.background = world.level2Background().getImage();

        view.setWorld(world);

        world.start();
    }

    public void level2() {
        level = 2;
        world = new Level2();
        world.populate(this);

        setFrame();

        //Player and Wasp Trackers
        world.addStepListener(new Tracker(view, world.getPlayer(), 0f, 140f));
        world.addStepListener(new WaspTracker(view, world.getWasp(), world.getPlayer()));

        //Enemy Trackers
        world.addStepListener(new SlimeTracker(view, world.getPlayer(), Level2.getSlime(), 51, 60));
        world.addStepListener(new SlimeTracker(view, world.getPlayer(), Level2.getSlime2(), 136, 150));
        world.addStepListener(new EyeTracker(view, Level2.getEye(), 4, -4, -15, 6));
        world.addStepListener(new EyeTracker(view, Level2.getEye2(), 3, -3, 71, 87));
        world.addStepListener(new EyeTracker(view, Level2.getEye3(), 6, -6, 130, 150));

        //Enemy Attacks
        slimeAttack = new SlimeAttack(view, Level2.getSlime(), world.getPlayer(), 44, 60);
        slimeAttack2 = new SlimeAttack(view, Level2.getSlime2(), world.getPlayer(), 130, 150);
        ea = new EyeAttack(view, Level2.getEye(), world.getPlayer(), -16, 6, 2000);
        ea1 = new EyeAttack(view, Level2.getEye2(), world.getPlayer(), 67, 87, 2000);
        ea2 = new EyeAttack(view, Level2.getEye3(), world.getPlayer(), 125, 155, 3000);

        //Player and Wasp Controller
        controller = new Controller(world.getPlayer(), world.getWasp());
        frame.addKeyListener(controller);

        //Activate Mouse Listener to shoot stings with Wasp
        sa = new StingAttack(view, world.getWasp(), Level2.getSlime(), slimeAttack, Level2.getSlime2(), slimeAttack2, Level2.getEye(), ea,
                Level2.getEye2(), ea1, Level2.getEye3(), ea2, null, null, null, null, null,
                null, null, null, null, null, null, null, world.getPlayer());
        view.addMouseListener(sa);

        Controller.JUMPING_SPEED = 18;
        Controller.WALKING_SPEED = 6;

        //Set the background
        MyView.background = world.level2Background().getImage();

        //Change font color
        view.setColor(Color.WHITE);

        view.setWorld(world);
    }

    public void level3InGame() {
        view.removeMouseListener(sa);
        frame.removeKeyListener(controller);
        frame.removeKeyListener(controller2);
        stopLevelTimersAndMusic();
        world.stop();
        MainWalker oldPlayer = world.getPlayer();
        level3();
        view.setPlayer(world.getPlayer());
        world.getPlayer().setCoinCount(oldPlayer.getCount());
    }

    public void level3InMenu() {
        level = 3;
        world = new Level3();
        world.populate(this);

        view = new MyView(world, MyView.background, WIDTH, HEIGHT, world.getPlayer(), Color.RED);
        setFrame();

        //Player and Wasp Trackers
        world.addStepListener(new Tracker(view, world.getPlayer(), 0f, 150f));
        world.addStepListener(new WaspTracker(view, world.getWasp(), world.getPlayer()));

        //Enemy Trackers
        world.addStepListener(new YetiTracker(view, world.getPlayer(), Level3.getYeti(), 25, 39));
        world.addStepListener(new YetiTracker(view, world.getPlayer(), Level3.getYeti2(), 80, 100));
        world.addStepListener(new YetiTracker(view, world.getPlayer(), Level3.getYeti3(), 140, 155));
        world.addStepListener(new EyeTracker(view, Level3.getEye(), 4, -4, 0, 17));
        world.addStepListener(new EyeTracker(view, Level3.getEye2(), 4, -4, 46, 60));
        world.addStepListener(new EyeTracker(view, Level3.getEye3(), 4, -4, 66, 76));
        world.addStepListener(new EyeTracker(view, Level3.getEye4(), 4, -4, 79, 95));

        //Enemy Attacks
        yetiAttack = new YetiAttack(view, Level3.getYeti(), world.getPlayer(), 20, 45);
        yetiAttack2 = new YetiAttack(view, Level3.getYeti2(), world.getPlayer(), 75, 105);
        yetiAttack3 = new YetiAttack(view, Level3.getYeti3(), world.getPlayer(), 130, 160);
        ea = new EyeAttack(view, Level3.getEye(), world.getPlayer(), -17, 20, 1500);
        ea1 = new EyeAttack(view, Level3.getEye2(), world.getPlayer(), 35, 65, 1750);
        ea2 = new EyeAttack(view, Level3.getEye3(), world.getPlayer(), 50, 76, 1750);
        ea3 = new EyeAttack(view, Level3.getEye4(), world.getPlayer(), 70, 95, 1750);

        controller = new Controller(world.getPlayer(), world.getWasp());
        frame.addKeyListener(controller);

        //Activate Mouse Listener to shoot stings with Wasp
        sa = new StingAttack(view, world.getWasp(), null, null, null, null, Level3.getEye(),
                ea, Level3.getEye2(), ea1, Level3.getEye3(), ea2, Level3.getEye4(), ea3, Level3.getYeti(), yetiAttack, Level3.getYeti2(),
                yetiAttack2, Level3.getYeti3(), yetiAttack3, null, null, null, null, world.getPlayer());
        view.addMouseListener(sa);

        Controller.JUMPING_SPEED = 18;
        Controller.WALKING_SPEED = 6;

        //Set the background
        MyView.background = world.level3Background().getImage();

        view.setWorld(world);

        world.start();
    }

    public void level3() {

        level = 3;
        world = new Level3();
        world.populate(this);

        setFrame();

        //switch the player in view to the current world player
        view.setPlayer(world.getPlayer());

        //Player and Wasp Trackers
        world.addStepListener(new Tracker(view, world.getPlayer(), 0f, 150f));
        world.addStepListener(new WaspTracker(view, world.getWasp(), world.getPlayer()));

        //Enemy Trackers
        world.addStepListener(new YetiTracker(view, world.getPlayer(), Level3.getYeti(), 25, 39));
        world.addStepListener(new YetiTracker(view, world.getPlayer(), Level3.getYeti2(), 80, 100));
        world.addStepListener(new YetiTracker(view, world.getPlayer(), Level3.getYeti3(), 140, 155));
        world.addStepListener(new EyeTracker(view, Level3.getEye(), 4, -4, 0, 17));
        world.addStepListener(new EyeTracker(view, Level3.getEye2(), 4, -4, 46, 60));
        world.addStepListener(new EyeTracker(view, Level3.getEye3(), 4, -4, 66, 76));
        world.addStepListener(new EyeTracker(view, Level3.getEye4(), 4, -4, 79, 95));

        //Enemy Attacks
        yetiAttack = new YetiAttack(view, Level3.getYeti(), world.getPlayer(), 20, 45);
        yetiAttack2 = new YetiAttack(view, Level3.getYeti2(), world.getPlayer(), 75, 105);
        yetiAttack3 = new YetiAttack(view, Level3.getYeti3(), world.getPlayer(), 130, 160);
        ea = new EyeAttack(view, Level3.getEye(), world.getPlayer(), -17, 20, 1500);
        ea1 = new EyeAttack(view, Level3.getEye2(), world.getPlayer(), 35, 65, 1750);
        ea2 = new EyeAttack(view, Level3.getEye3(), world.getPlayer(), 50, 76, 1750);
        ea3 = new EyeAttack(view, Level3.getEye4(), world.getPlayer(), 70, 95, 1750);

        controller = new Controller(world.getPlayer(), world.getWasp());
        frame.addKeyListener(controller);

        //Activate Mouse Listener to shoot stings with Wasp
        sa = new StingAttack(view, world.getWasp(), null, null, null, null, Level3.getEye(),
                ea, Level3.getEye2(), ea1, Level3.getEye3(), ea2, Level3.getEye4(), ea3, Level3.getYeti(), yetiAttack, Level3.getYeti2(),
                yetiAttack2, Level3.getYeti3(), yetiAttack3, null, null, null, null, world.getPlayer());
        view.addMouseListener(sa);

        Controller.JUMPING_SPEED = 18;
        Controller.WALKING_SPEED = 6;

        //Set the background
        MyView.background = world.level3Background().getImage();

        //Change font color
        view.setColor(Color.RED);

        view.setWorld(world);

        world.start();
    }

    public void level4InGame() {
        view.removeMouseListener(sa);
        frame.removeKeyListener(controller);
        frame.removeKeyListener(controller2);
        stopLevelTimersAndMusic();
        world.stop();
        MainWalker oldPlayer = world.getPlayer();
        level4();
        view.setPlayer(world.getPlayer());
        world.getPlayer().setCoinCount(oldPlayer.getCount());
    }

    public void level4InMenu() {
        level = 4;
        world = new Level4();
        world.populate(this);

        view = new MyView(world, MyView.background, WIDTH, HEIGHT, world.getPlayer(), Color.YELLOW);
        setFrame();

        //Player and Wasp Trackers
        world.addStepListener(new Tracker(view, world.getPlayer(), 0f, 145f));
        world.addStepListener(new WaspTracker(view, world.getWasp(), world.getPlayer()));

        //Player and Wasp Controller
        controller = new Controller(world.getPlayer(), world.getWasp());
        frame.addKeyListener(controller);

        //Enemy Trackers
        world.addStepListener(new EyeTracker(view, Level4.getEye1(), 4, -4, -5, 15));
        world.addStepListener(new EyeTracker(view, Level4.getEye2(), 4, -4, 55, 70));
        world.addStepListener(new ShadowTracker(view, world.getPlayer(), Level4.getShadow(), 62, 80));
        world.addStepListener(new ShadowTracker(view, world.getPlayer(), Level4.getShadow2(), 132, 148));

        //Enemy Attacks
        ea = new EyeAttack(view, Level4.getEye1(), world.getPlayer(), -15, 20, 1500);
        ea2 = new EyeAttack(view, Level4.getEye2(), world.getPlayer(), 45, 75, 2000);
        shadowAttack = new ShadowAttack(view, Level4.getShadow(), world.getPlayer(), 55, 85);
        shadowAttack2 = new ShadowAttack(view, Level4.getShadow2(), world.getPlayer(), 132, 155);

        //Activate Mouse Listener to shoot stings with Wasp
        sa = new StingAttack(view, world.getWasp(), null, null, null, null, Level4.getEye1(),
                ea, Level4.getEye2(), ea2, null, null, null, null, null, null, null, null,
                null, null, Level4.getShadow(), shadowAttack, Level4.getShadow2(), shadowAttack2, world.getPlayer());
        view.addMouseListener(sa);

        Controller.JUMPING_SPEED = 18;
        Controller.WALKING_SPEED = 6;

        //Set the background
        MyView.background = world.level4Background().getImage();

        view.setWorld(world);

        world.start();
    }

    public void level4() {
        level = 4;
        world = new Level4();
        world.populate(this);

        setFrame();

        //Player and Wasp Trackers
        world.addStepListener(new Tracker(view, world.getPlayer(), 0f, 145f));
        world.addStepListener(new WaspTracker(view, world.getWasp(), world.getPlayer()));

        //Player and Wasp Controller
        controller = new Controller(world.getPlayer(), world.getWasp());
        frame.addKeyListener(controller);

        //Enemy Trackers
        world.addStepListener(new EyeTracker(view, Level4.getEye1(), 4, -4, -5, 15));
        world.addStepListener(new EyeTracker(view, Level4.getEye2(), 4, -4, 55, 70));
        world.addStepListener(new ShadowTracker(view, world.getPlayer(), Level4.getShadow(), 62, 80));
        world.addStepListener(new ShadowTracker(view, world.getPlayer(), Level4.getShadow2(), 132, 148));

        //Enemy Attacks
        ea = new EyeAttack(view, Level4.getEye1(), world.getPlayer(), -15, 20, 1500);
        ea2 = new EyeAttack(view, Level4.getEye2(), world.getPlayer(), 45, 75, 2000);
        shadowAttack = new ShadowAttack(view, Level4.getShadow(), world.getPlayer(), 55, 85);
        shadowAttack2 = new ShadowAttack(view, Level4.getShadow2(), world.getPlayer(), 132, 155);

        //Activate Mouse Listener to shoot stings with Wasp
        sa = new StingAttack(view, world.getWasp(), null, null, null, null, Level4.getEye1(),
                ea, Level4.getEye2(), ea2, null, null, null, null, null, null, null, null,
                null, null, Level4.getShadow(), shadowAttack, Level4.getShadow2(), shadowAttack2, world.getPlayer());
        view.addMouseListener(sa);

        Controller.JUMPING_SPEED = 18;
        Controller.WALKING_SPEED = 6;

        //Set the background
        MyView.background = world.level4Background().getImage();

        //Change font color
        view.setColor(Color.YELLOW);

        view.setWorld(world);

        world.start();
    }

    public void stopLevelTimersAndMusic() {
        if (level == 1) {
            Level1.getGameMusic().stop();
            ea.stopTimer();
            ea1.stopTimer();
            Level1.getPowerUp().stopTimer();
        } else if (level == 2) {
            Level2.getGameMusic().stop();
            slimeAttack.stopTimer();
            slimeAttack2.stopTimer();
            ea.stopTimer();
            ea1.stopTimer();
            ea2.stopTimer();
        } else if (level == 3) {
            Level3.getGameMusic().stop();
            yetiAttack.stopTimer();
            yetiAttack2.stopTimer();
            yetiAttack3.stopTimer();
            ea.stopTimer();
            ea1.stopTimer();
            ea2.stopTimer();
            ea3.stopTimer();
            Level3.getPowerUp().stopTimer();
        } else if (level == 4) {
            ea.stopTimer();
            ea2.stopTimer();
            shadowAttack.stopTimer();
            shadowAttack2.stopTimer();
            Level4.getGameMusic().stop();
        }
    }

    //BUTTONS

    public void restart() {
        world.stop();
        view.removeMouseListener(sa);
        stopLevelTimersAndMusic();
        frame.removeKeyListener(controller);
        frame.removeKeyListener(controller2);
        level1();
        view.setPlayer(world.getPlayer());
        world.start();
    }

    public void restartGame() {
        level1InMenu();
        view.setPlayer(world.getPlayer());
        world.start();
    }

    public void pause() {
        world.stop();
        if (level == 1) {
            ea.stopTimer();
            ea1.stopTimer();
        } else if (level == 2) {
            slimeAttack.stopTimer();
            slimeAttack2.stopTimer();
            ea.stopTimer();
            ea1.stopTimer();
            ea2.stopTimer();
        } else if (level == 3) {
            yetiAttack.stopTimer();
            yetiAttack2.stopTimer();
            yetiAttack3.stopTimer();
            ea.stopTimer();
            ea1.stopTimer();
            ea2.stopTimer();
            ea3.stopTimer();
        } else if (level == 4) {
            ea.stopTimer();
            ea2.stopTimer();
            shadowAttack.stopTimer();
            shadowAttack2.stopTimer();
        }
    }

    public void resume() {
        world.start();
        if (level == 1) {
            if (Level1.getEye().getHealthPoints() != 0) {
                ea.startTimer();
            }
            if (Level1.getEye1().getHealthPoints() != 0) {
                ea1.startTimer();
            }
        } else if (level == 2) {
            if (Level2.getSlime().getHealthPoints() != 0) {
                slimeAttack.startTimer();
            }
            if (Level2.getSlime2().getHealthPoints() != 0) {
                slimeAttack2.startTimer();
            }
            if (Level2.getEye().getHealthPoints() != 0) {
                ea.startTimer();
            }
            if (Level2.getEye2().getHealthPoints() != 0) {
                ea1.startTimer();
            }
            if (Level2.getEye3().getHealthPoints() != 0) {
                ea2.startTimer();
            }
        } else if (level == 3) {
            if (Level3.getYeti().getHealthPoints() != 0) {
                yetiAttack.startTimer();
            }
            if (Level3.getYeti2().getHealthPoints() != 0) {
                yetiAttack2.startTimer();
            }
            if (Level3.getYeti3().getHealthPoints() != 0) {
                yetiAttack3.startTimer();
            }
            if (Level3.getEye().getHealthPoints() != 0) {
                ea.startTimer();
            }
            if (Level3.getEye2().getHealthPoints() != 0) {
                ea1.startTimer();
            }
            if (Level3.getEye3().getHealthPoints() != 0) {
                ea2.startTimer();
            }
            if (Level3.getEye4().getHealthPoints() != 0) {
                ea3.startTimer();
            }
        } else if (level == 4) {
            if (Level4.getEye1().getHealthPoints() != 0) {
                ea.startTimer();
            }
            if (Level4.getEye2().getHealthPoints() != 0) {
                ea2.startTimer();
            }
            if (Level4.getShadow().getHealthPoints() != 0) {
                shadowAttack.startTimer();
            }
            if (Level4.getShadow2().getHealthPoints() != 0) {
                shadowAttack2.startTimer();
            }
        }
    }

    public void mute() {
        if (level == 1) {
            Level1.getGameMusic().stop();
        } else if (level == 2) {
            Level2.getGameMusic().stop();
        } else if (level == 3) {
            Level3.getGameMusic().stop();
        } else if (level == 4) {
            Level4.getGameMusic().stop();
        }
    }

    public void save() {
        try {
            HighScoreWriter writer = new HighScoreWriter("data/save.txt");
            writer.writeHighScore(MainMenu.getTextFromField(), this,  getLevel(), getWorld().getPlayer().getCount(),
                    getWorld().getPlayer().getHealthPoints(), getWorld().getPlayer().getPosition());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            reader = new HighScoreReader("data/save.txt", this);
            reader.readScores();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (reader.getLevel() == 1) {
            level1InMenu();
            setLevel(reader.getLevel());
            getPlayer().setCoinCount(reader.getScore());
            getPlayer().setHealthPoints(reader.getHealth());
            getPlayer().setPosition(new Vec2(reader.getCoord1(), reader.getCoord2()));
        }
        if (reader.getLevel() == 2) {
            level2InMenu();
            setLevel(reader.getLevel());
            getPlayer().setCoinCount(reader.getScore());
            getPlayer().setHealthPoints(reader.getHealth());
            getPlayer().setPosition(new Vec2(reader.getCoord1(), reader.getCoord2()));
        }
        if (reader.getLevel() == 3) {
            level3InMenu();
            setLevel(reader.getLevel());
            getPlayer().setCoinCount(reader.getScore());
            getPlayer().setHealthPoints(reader.getHealth());
            getPlayer().setPosition(new Vec2(reader.getCoord1(), reader.getCoord2()));
        }
        if (reader.getLevel() == 4) {
            level4InMenu();
            setLevel(reader.getLevel());
            getPlayer().setCoinCount(reader.getScore());
            getPlayer().setHealthPoints(reader.getHealth());
            getPlayer().setPosition(new Vec2(reader.getCoord1(), reader.getCoord2()));
        }
        clearSaveFile();
    }

    public void clearSaveFile() {
        try {
            PrintWriter writer = new PrintWriter("data/save.txt");
            writer.print("");
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HighScoreReader getReader() {
        return reader;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public GameLevel getWorld() {
        return world;
    }

    public JFrame getFrame() {
        return frame;
    }

    public MyView getView() {
        return view;
    }

    public int getLastScore() {
        return lastScore;
    }

    public static void main(String[] args) {
        new Game();
    }
}
