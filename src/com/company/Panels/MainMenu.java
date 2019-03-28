package com.company.Panels;

import city.cs.engine.SoundClip;
import com.company.Main.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainMenu extends JPanel {

    private JPanel mainPanel;
    private JButton startButton;
    private JButton quitButton;
    private JLabel jungle;
    private JLabel run;
    private JComboBox comboBox1;
    public JTextField entername;
    private JButton loadButton;
    private static String textFromField;
    private static SoundClip gameMusic;


    private Game game;

    private Image img;

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, 800, 550, null);
    }


    public MainMenu(Image img, final Game game) {
        this.game = game;

        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setSize(size);
        setLayout(new GridBagLayout());

        try {
            gameMusic = new SoundClip("data/Sounds/Soundtracks/menu.wav");
            gameMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        mainPanel.setOpaque(false);

        add(mainPanel);

        ((JLabel)comboBox1.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMusic.stop();
                textFromField = entername.getText();
                if (comboBox1.getSelectedItem() == "CHOOSE LEVEL") {
                    game.level1InMenu();
                } else if (comboBox1.getSelectedItem() == "LEVEL 1") {
                    game.level1InMenu();
                } else if (comboBox1.getSelectedItem() == "LEVEL 2") {
                    game.level2InMenu();
                } else if (comboBox1.getSelectedItem() == "LEVEL 3") {
                    game.level3InMenu();
                } else if (comboBox1.getSelectedItem() == "LEVEL 4") {
                    game.level4InMenu();
                }
                game.clearSaveFile();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMusic.stop();
                game.load();
                game.clearSaveFile();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Change the font of the buttons
        try {
            Font myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("data/1980XX.ttf"))).deriveFont(Font.PLAIN, 30);
            startButton.setFont(myFont);
            quitButton.setFont(myFont);
            comboBox1.setFont(myFont);
            entername.setFont(myFont);
            loadButton.setFont(myFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Change the font of the title
        try {
            Font myFont2 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("data/1980XX.ttf"))).deriveFont(Font.PLAIN, 100);
            jungle.setFont(myFont2);
            run.setFont(myFont2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        entername.setHorizontalAlignment(JTextField.CENTER);
        entername.setForeground(Color.GRAY);
        entername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (entername.getText().equals("Enter Name")) {
                    entername.setText("");
                    entername.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (entername.getText().isEmpty()) {
                    entername.setForeground(Color.GRAY);
                    entername.setText(entername.getText());
                }
            }
        });
    }

    public static String getTextFromField() {
        return textFromField;
    }
}
