package com.mycompany.app.classes;

import java.util.Vector;

public class Enemy extends MoveableEntity {
    // CONSTRUCTOR
    public Enemy() {
        // need to determine the players start position and specific sprite
        speed = 1;
    }

    // METHODS
    private Vector<Position> pathToPlayer;

    public void updateShortestPath() {

    }

}
