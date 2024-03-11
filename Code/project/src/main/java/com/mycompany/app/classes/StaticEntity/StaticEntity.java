package com.mycompany.app.classes.StaticEntity;

import java.awt.*;
import com.mycompany.app.classes.Helpers.Position;


//import java.util.Timer;

/**
 * An abstract class representing static entities in the game.
 *
 * <p>A static entity is an object that does not move and has defined behavior when interacting with other entities.
 * This class serves as a base for various static entities, defining common attributes and methods.
 */
public abstract class StaticEntity 
{

    // Attributes

    /** The position of the static entity in the game world. */
    protected Position position;

    /** Determines whether the static entity despawns or not. */
    protected boolean objectDespawns;
    
    /** The despawn timer for the entity, measured in milliseconds. A value of -1 indicates despawning only on collision. */
    protected int despawnTimer;

    /** The sprite representing the static entity. */
    public Image sprite;
    
    //protected Timer despawnTimer;

    /**
     * Constructs a new static entity with a despawn timer.
     *
     * @param position        The position of the static entity.
     * @param despawnTimer    The time, in milliseconds, after which the entity will despawn. Use -1 for despawning only on collision.
     * @param sprite          The sprite representing the static entity.
     */
    public StaticEntity(Position position, int despawnTimer, Image sprite)
    {
        // Constructor for StaticEntities that rely on time like bonus rewards
        this.position = position;
        objectDespawns = true;
        this.despawnTimer = despawnTimer;
        this.sprite = sprite;
    }

    /**
     * Constructs a new static entity without a despawn timer.
     *
     * @param position        The position of the static entity.
     * @param sprite          The sprite representing the static entity.
     */
    public StaticEntity(Position position, Image sprite)
    {
        // constructor for StaticEntities that don't despawn on a timer like traps and Regular Rewards
        this.position = position;
        objectDespawns = true;
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
