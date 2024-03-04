package com.mycompany.app.classes.MoveableEntity;

import java.util.Vector;

import com.mycompany.app.classes.Helpers.Position;

// enemy sprite https://forums.rpgmakerweb.com/index.php?threads/whtdragons-animals-and-running-horses-now-with-more-dragons.53552/
// 64x57

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
