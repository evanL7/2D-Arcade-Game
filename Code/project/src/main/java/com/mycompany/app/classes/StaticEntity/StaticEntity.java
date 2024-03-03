package com.mycompany.app.classes.StaticEntity;

import java.awt.*;
import com.mycompany.app.classes.Helpers.Position;


//import java.util.Timer;

public abstract class StaticEntity 
{

    // Attributes

    protected Position position;
    protected boolean objectDespawns;
    protected int despawnTimer;
    public Image sprite;
    //protected Timer despawnTimer;

    // Constructor for StaticEntities that rely on time like bonus rewards
    public StaticEntity(Position position, boolean objectDespawns, int despawnTimer, Image sprite)
    {
        this.position = position;
        this.objectDespawns = objectDespawns;
        this.despawnTimer = despawnTimer;
        this.sprite = sprite;
    }

    // constructor for StaticEntities that don't despawn on a timer like traps and Regular Rewards
    public StaticEntity(Position position, boolean objectDespawns, Image sprite)
    {
        this.position = position;
        this.objectDespawns = objectDespawns;
        this.sprite = sprite;
        
        // a value of -1 represents how the entity will only despawn if collided with
        despawnTimer = -1;
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
