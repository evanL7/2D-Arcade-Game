package com.mycompany.app.classes.StaticEntity;
import java.awt.*;
import com.mycompany.app.classes.Helpers.Position;

public class Trap extends StaticEntity 
{

    private float damage;

    // constructor
    // doesn't need despawnTimer as it would only despawn if collided with
    public Trap(Position position, boolean objectDespawns, Image sprite, float damage)
    {
        super(position, objectDespawns, sprite);
        this.damage = damage;
    }

    // Methods
    public float getDamage() 
    {
        return damage;
    }

}