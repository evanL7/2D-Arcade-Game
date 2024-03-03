package com.mycompany.app.classes.MoveableEntity;

import java.util.Vector;

import com.mycompany.app.classes.Helpers.Position;

public class Enemy extends MoveableEntity {
    // ATTRIBUTES
    private Vector<Position> pathToPlayer;

    // CONSTRUCTOR
    public Enemy(Position position) {
        // need to determine the players start position and specific sprite
        super(position);
        speed = 1;
    }

    // METHOD
    public void updateShortestPath() {

    }

}
