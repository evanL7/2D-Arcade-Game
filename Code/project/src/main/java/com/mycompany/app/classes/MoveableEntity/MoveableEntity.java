package com.mycompany.app.classes.MoveableEntity;

import java.awt.*;

import com.mycompany.app.classes.Helpers.Position;

public abstract class MoveableEntity {
    // ATTRIBUTES
    protected Image sprite;
    protected Position position;
    protected int speed;

    /**
     * Constructs a MoveableEntity with the given position.
     * 
     * @param position The position of the entity.
     */
    public MoveableEntity(Position position) {
        this.position = position;
    }

}
