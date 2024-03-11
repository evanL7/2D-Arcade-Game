package com.mycompany.app.classes.MoveableEntity;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.mycompany.app.classes.Helpers.Position;

public abstract class MoveableEntity {
    // ATTRIBUTES
    protected Image sprite;
    public Position position;
    public int speed;
    protected BufferedImage[][] animations; // 2d image array of the images for player movements
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
