package com.mycompany.app.classes;

import java.util.Vector;

public class Enemy extends MoveableEntity {
    // ATTRIBUTES
    private Vector<Position> pathToPlayer;

    // CONSTRUCTOR
    public Enemy() {
        // need to determine the players start position and specific sprite
        speed = 1;
    }

    // METHOD
    public void updateShortestPath() {

    }

}
