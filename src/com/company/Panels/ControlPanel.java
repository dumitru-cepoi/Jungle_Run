package com.company.Panels;

import com.company.Levels.Level1;
import com.company.Levels.Level2;
import com.company.Levels.Level3;
import com.company.Levels.Level4;
import com.company.Main.Game;
import com.company.Walkers.MainWalker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

public class ControlPanel extends JPanel {

    private Game game;

    private static final ImageIcon quitIcon = new ImageIcon("data/Images/quit.png");
    private static final ImageIcon restartIcon = new ImageIcon("data/Images/restart.png");
    private static final ImageIcon pauseIcon = new ImageIcon("data/Images/pause.png");
    private static final ImageIcon playIcon = new ImageIcon("data/Images/play.png");
    private static final ImageIcon muteIcon = new ImageIcon("data/Images/mute.png");
    private static final ImageIcon menuIcon = new ImageIcon("data/Images/menu.png");
    private static final ImageIcon saveIcon = new ImageIcon("data/Images/save.png");

    private JButton quitButton;
    private JButton restartButton;
    private JButton pauseButton;
    private JButton resumeButton;
    private JButton muteButton;
    private JButton menuButton;
    private JButton SAVEButton;


    public ControlPanel(final Game game) {
        this.game = game;

        this.add(menuButton);
        this.add(pauseButton);
        this.add(resumeButton);
        this.add(restartButton);
        this.add(muteButton);
        this.add(SAVEButton);
        this.add(quitButton);

        quitButton.setIcon(quitIcon);
        restartButton.setIcon(restartIcon);
        pauseButton.setIcon(pauseIcon);
        resumeButton.setIcon(playIcon);
        muteButton.setIcon(muteIcon);
        menuButton.setIcon(menuIcon);
        SAVEButton.setIcon(saveIcon);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.save();
                System.exit(0);
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restart();
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pause();
                if (game.getLevel() == 1) {
                    Level1.getGameMusic().pause();
                } else if (game.getLevel() == 2) {
                    Level2.getGameMusic().pause();
                } else if (game.getLevel() == 3) {
                    Level3.getGameMusic().pause();
                } else if (game.getLevel() == 4) {
                    Level4.getGameMusic().pause();
                }
            }
        });
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.resume();
                if (game.getLevel() == 1) {
                    Level1.getGameMusic().resume();
                } else if (game.getLevel() == 2) {
                    Level2.getGameMusic().resume();
                } else if (game.getLevel() == 3) {
                    Level3.getGameMusic().resume();
                } else if (game.getLevel() == 4) {
                    Level4.getGameMusic().resume();
                }
            }
        });
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.mute();
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWalker.gameOverFalse();
                game.save();
                game.getWorld().stop();
                game.stopLevelTimersAndMusic();
                game.getFrame().dispose();
                new Game();
            }
        });
        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                game.save();
            }
        });

        //Change the font of the buttons
        try {
            Font myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("data/1980XX.ttf"))).deriveFont(Font.PLAIN, 22);
            quitButton.setFont(myFont);
            resumeButton.setFont(myFont);
            pauseButton.setFont(myFont);
            restartButton.setFont(myFont);
            muteButton.setFont(myFont);
            menuButton.setFont(myFont);
            SAVEButton.setFont(myFont);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
