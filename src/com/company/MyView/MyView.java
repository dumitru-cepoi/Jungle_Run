package com.company.MyView;

import city.cs.engine.UserView;
import city.cs.engine.World;
import com.company.Walkers.MainWalker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;


public class MyView extends UserView {

    public static Image background;
    private MainWalker player;
    private Color color;

    //Coin Image
    private static final ImageIcon coinImage = new ImageIcon("data/Collectibles/coin.gif");

    //Life Meter Images
    private static final ImageIcon meter1 = new ImageIcon("data/LifeMeter/meter1.png");
    private static final ImageIcon meter2 = new ImageIcon("data/LifeMeter/meter2.png");
    private static final ImageIcon meter3 = new ImageIcon("data/LifeMeter/meter3.png");
    private static final ImageIcon meter4 = new ImageIcon("data/LifeMeter/meter4.png");
    private static final ImageIcon meter5 = new ImageIcon("data/LifeMeter/meter5.png");
    private static final ImageIcon meter6 = new ImageIcon("data/LifeMeter/meter6.png");

    public MyView(World world, Image background, int width, int height, MainWalker player, Color color) {
        super(world, width, height);
        MyView.background = background;
        this.player = player;
        this.color = color;
    }

    @Override
    public void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, 800, 480, this);
    }

    @Override
    public void paintForeground(Graphics2D g) {

        //Custom Font
        try {
            Font myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("data/1980XX.ttf"))).deriveFont(Font.PLAIN, 35);
            g.setFont(myFont);
        } catch (Exception e) {}
        g.setColor(color);

        //Coin Count
        g.drawImage(coinImage.getImage(), 5, 15, 25, 25, this);
        g.drawString("" + player.getCount(), 40, 35);

        //Life Meter
        if (player.getHealthPoints() == 6) {
            g.drawImage(meter6.getImage(), 95, 17, 80, 20, this);
        } else if (player.getHealthPoints() == 5) {
            g.drawImage(meter5.getImage(), 95, 17, 80, 20, this);
        } else if (player.getHealthPoints() == 4) {
            g.drawImage(meter4.getImage(), 95, 17, 80, 20, this);
        } else if (player.getHealthPoints() == 3) {
            g.drawImage(meter3.getImage(), 95, 17, 80, 20, this);
        } else if (player.getHealthPoints() == 2) {
            g.drawImage(meter2.getImage(), 95, 17, 80, 20, this);
        } else if (player.getHealthPoints() == 1) {
            g.drawImage(meter1.getImage(), 95, 17, 80, 20, this);
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPlayer(MainWalker player) {
        this.player = player;
    }

}
