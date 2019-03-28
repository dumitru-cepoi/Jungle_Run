package com.company.Collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import com.company.Main.Game;
import com.company.Walkers.MainWalker;

/**
 * Listener for collision with a door.  When the player collides with a door,
 * if the current level is complete the game is advanced to the next level.
 */
public class DoorListener implements CollisionListener {
    private Game game;
    private static SoundClip nextLevel;

    static {
        try {
            nextLevel = new SoundClip("data/Sounds/FX/theNextLevel.wav");
            nextLevel.setVolume(2.0d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DoorListener(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        MainWalker player = game.getPlayer();
        if (e.getOtherBody() == player && game.isCurrentLevelCompleted()) {
            if (game.getLevel() == 1 || game.getLevel() == 2 || game.getLevel() == 3) {
                nextLevel.play();
            }
            game.goNextLevel();
        }
    }
}
