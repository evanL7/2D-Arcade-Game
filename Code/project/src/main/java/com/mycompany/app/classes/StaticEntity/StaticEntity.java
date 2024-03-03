package com.mycompany.app.classes.StaticEntity;

import java.awt.*;


//import java.util.Timer;

public abstract class StaticEntity 
{

    // Attributes
    protected int x;
    protected int y;
    protected boolean objectDespawns;
    //protected Timer despawnTimer;
    protected int despawnTimer;
    public Image sprite;

    // Constructor
    public StaticEntity(int x, int y, boolean objectDespawns, int despawnTimer, Image sprite)
    {
        this.x = x;
        this.y = y;
        this.objectDespawns = objectDespawns;
        this.despawnTimer = despawnTimer;
        this.sprite = sprite;
    }

    //Methods

    public void onCollide() 
    {

    }

    // destroys the object
    public void destroy() 
    {

    }

    public void update()
    {

    }


    public void render()
    {

    }

}
