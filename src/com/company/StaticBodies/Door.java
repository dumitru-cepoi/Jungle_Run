package com.company.StaticBodies;

import city.cs.engine.*;

/**
 * Doors in a game. When the actor collides with a door, if
 * the current level is complete the game is advanced to the
 * next level.
 */
public class Door extends StaticBody {

    private static final Shape doorShape = new PolygonShape(
            0.99f,2.49f, -0.45f,2.5f, -0.4f,-2.3f, 1.12f,-2.3f, 2.02f,-1.54f, 2.41f,0.56f, 1.62f,1.98f);

    /**
     * Initialise a new door.
     * @param world The world.
     */
    public Door(World world) {
        super(world, doorShape);
        addImage(new BodyImage("data/Images/door.png", 5f));
    }
}