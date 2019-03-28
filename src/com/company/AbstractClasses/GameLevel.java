package com.company.AbstractClasses;

import city.cs.engine.SoundClip;
import city.cs.engine.World;
import com.company.Collisions.DoorListener;
import com.company.Main.Game;
import com.company.StaticBodies.Door;
import com.company.Walkers.MainWalker;
import com.company.Walkers.Wasp;
import org.jbox2d.common.Vec2;

import javax.swing.*;


public abstract class GameLevel extends World {
    private MainWalker player;
    private Wasp wasp;

    public MainWalker getPlayer() {
        return player;
    }

    public Wasp getWasp() {
        return wasp;
    }

    /**
     * Populate the world of this level.
     * Child classes should this method with additional bodies.
     */
    public void populate(Game game) {
        player = new MainWalker(this, game);
        player.setPosition(startPosition());
        Door door = new Door(this);
        door.setPosition(doorPosition());
        door.addCollisionListener(new DoorListener(game));
    }

    /** The initial position of the player. */
    public abstract Vec2 startPosition();

    /** The position of the exit door. */
    public abstract Vec2 doorPosition();

    /** Is this level complete? */
    public abstract boolean isCompleted();

    /** Background Images */
    public abstract ImageIcon level1Background();
    public abstract ImageIcon level2Background();
    public abstract ImageIcon level3Background();
    public abstract ImageIcon level4Background();

    /** Background Music */
    public abstract SoundClip level1Sound();
    public abstract SoundClip level2Sound();
    public abstract SoundClip level3Sound();
    public abstract SoundClip level4Sound();

}
