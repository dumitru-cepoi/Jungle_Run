package com.company.Panels;

import city.cs.engine.SoundClip;
import com.company.Main.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TheEnd extends JPanel{

    private static SoundClip gameMusic;
    private Game game;
    private Image img;

    private JPanel mainPanel;
    private JLabel congratsLabel;
    private JLabel scoreLabel;
    private JButton quitButton;
    private JButton menuButton;

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, 800, 550, null);
    }

    public TheEnd(Image img, final Game game) {
        this.game = game;
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setLayout(new GridBagLayout());

        try {
            gameMusic = new SoundClip("data/Sounds/Soundtracks/congratulations.wav");
            gameMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        mainPanel.setOpaque(false);
        add(mainPanel);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMusic.stop();
                game.getFrame().dispose();
                new Game();
            }
        });

        if (MainMenu.getTextFromField().equals("Enter Name") || MainMenu.getTextFromField().equals("")) {
            scoreLabel.setText("Your score is: " + game.getLastScore());
        } else {
            scoreLabel.setText(MainMenu.getTextFromField() + ", your score is: " + game.getLastScore());
        }

        //Change the font of the buttons
        try {
            Font myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("data/1980XX.ttf"))).deriveFont(Font.PLAIN, 30);
            quitButton.setFont(myFont);
            menuButton.setFont(myFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Change the font of the score line
        try {
            Font myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("data/1980XX.ttf"))).deriveFont(Font.PLAIN, 50);
            scoreLabel.setFont(myFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Change the font of the title
        try {
            Font myFont2 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("data/1980XX.ttf"))).deriveFont(Font.PLAIN, 100);
            congratsLabel.setFont(myFont2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
