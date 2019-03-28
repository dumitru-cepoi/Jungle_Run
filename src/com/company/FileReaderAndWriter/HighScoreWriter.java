package com.company.FileReaderAndWriter;

import com.company.Main.Game;
import org.jbox2d.common.Vec2;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Demonstrates how high-score data can be written to a text file.
 */
public class HighScoreWriter {

    private String fileName;
    static Game game;

    public HighScoreWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeHighScore(String name, final Game game,  int level, int score, int hp, Vec2 position) throws IOException {
        this.game = game;
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(name + "\t" + level + "\t" + score + "\t" + hp + "\t" + position +"\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HighScoreWriter hsWriter = new HighScoreWriter("sample.hs");
        for (int i = 0; i < args.length; i += 2) {
            String name = args[i];
            int score = Integer.parseInt(args[i + 1]);
            Vec2 position = game.getWorld().getPlayer().getPosition();
            int level = game.getLevel();
            int hp = game.getWorld().getPlayer().getHealthPoints();
            hsWriter.writeHighScore(name, game, level, score, hp, position);
        }
    }
}
