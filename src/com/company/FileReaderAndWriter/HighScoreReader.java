package com.company.FileReaderAndWriter;

import com.company.Main.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Demonstrates how high-score data can be read from a text
 * file and printed to the terminal.
 */
public class HighScoreReader {

    private String fileName;
    private Game game;

    private String name;
    private int level;
    private int score;
    private int health;
    private float coord1;
    private float coord2;

    /**
     * Initialise a new HighScoreReader
     * @param fileName the name of the high-score file
     */
    public HighScoreReader(String fileName, final Game game) {
        this.fileName = fileName;
        this.game = game;
    }

    /**
     * Read the high-score data from the high-score file and print it to
     * the terminal window.
     */
    public void readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split("\t");
                name = tokens[0];
                level = Integer.parseInt(tokens[1]);
                score = Integer.parseInt(tokens[2]);
                health = Integer.parseInt(tokens[3]);

                String coordString = tokens[4];
                String coordFloats = coordString.replaceAll("\\(", "").replaceAll("\\)", "");
                String[] coordAr = coordFloats.split(",");
                coord1 = Float.parseFloat(coordAr[0]);
                coord2 = Float.parseFloat(coordAr[1]);
                line = reader.readLine();
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getCoord1() {
        return coord1;
    }

    public void setCoord1(float coord1) {
        this.coord1 = coord1;
    }

    public float getCoord2() {
        return coord2;
    }

    public void setCoord2(float coord2) {
        this.coord2 = coord2;
    }

    public String getName() {
        return name;
    }
}
