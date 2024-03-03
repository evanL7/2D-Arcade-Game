package com.mycompany.app.classes.StaticEntity;
import java.awt.*;

public class Trap extends StaticEntity 
{

    private float damage;

    // constructor
    public Trap(int x, int y, boolean objectDespawns, int despawnTimer, Image sprite, float damage)
    {
        super(x, y, objectDespawns, despawnTimer, sprite);
        this.damage = damage;
    }

    public float getDamage() 
    {
        return damage;
    }

}