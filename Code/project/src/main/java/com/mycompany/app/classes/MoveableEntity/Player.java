package com.mycompany.app.classes.MoveableEntity;

import java.awt.image.BufferedImage;

import com.mycompany.app.classes.Helpers.Position;

public class Player extends MoveableEntity {
    // ATTRIBUTES
    private int regularRewardsCollected;

    // CONSTRUCTOR
    public Player(Position position) {
        // need to determine the players start position and specific sprite
        super(position);
        speed = 1;
    }

    // GETTERS AND SETTERS
    public int getRegularRewardsCollected() {
        return regularRewardsCollected;
    }

    public void setRegularRewardsCollected(int regularRewardsCollected) {
        this.regularRewardsCollected = regularRewardsCollected;
    }

}
