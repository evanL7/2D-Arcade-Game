package com.mycompany.app.classes.StaticEntity;

public class Trap extends StaticEntity {

    private float damage;

    // constructor
    public Trap()
    {
        // Collision with a trap should always reduce the player's score by 1.0
        damage = 1.0f;
    }

    public float getDamage() {
        return damage;
    }

}
