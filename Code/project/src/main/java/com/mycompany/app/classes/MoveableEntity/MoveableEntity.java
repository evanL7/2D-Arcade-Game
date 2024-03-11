package com.mycompany.app.classes.MoveableEntity;

import java.awt.*;

import com.mycompany.app.classes.Helpers.Position;

public abstract class MoveableEntity {
    // ATTRIBUTES
    protected Image sprite;
    public Position position;
    public int speed;
    protected boolean moving = false;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    /**
     * Constructs a MoveableEntity with the given position.
     * 
     * @param position The position of the entity.
     */
    public MoveableEntity(Position position) {
        this.position = position;
    }

    // gets and returns the entities position
    public Position getPosition() {
        return this.position;
    }

}
