package com.mycompany.app.classes;

public class Player extends MoveableEntity {
    // ATTRIBUTES
    private int regularRewardsCollected;

    // CONSTRUCTOR
    public Player() {
        // need to determine the players start position and specific sprite
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
