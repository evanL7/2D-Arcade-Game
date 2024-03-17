package MoveableEntity;

import java.awt.*;
import java.awt.image.BufferedImage;

import Helpers.ImageUtils;
import Display.Game;
import Gamestates.Playing;
import Helpers.Position;

public abstract class MoveableEntity {
    // ATTRIBUTES
    protected Image sprite;
    public Position position;
    public Playing playing;
    public int speed;
    protected boolean moving = false;

    public Rectangle solidArea;
    public boolean collisionOn = false;
    public boolean onPath = false;

    /**
     * Constructs a MoveableEntity with the given position.
     * 
     * @param position The position of the entity.
     */
    public MoveableEntity(Position position, Playing playing) {
        this.position = position;
        this.playing = playing;
    }

    // gets and returns the entities position
    public Position getPosition() {
        return this.position;
    }

    // Method to get the height of the sprite
    public int getHeight() {
        BufferedImage sprite = ImageUtils.convertToBufferedImage(getSprite());
        if (sprite != null) {
            return sprite.getHeight();
        } else {
            return 0; // or handle null sprite case accordingly
        }
    }

    // Method to get the width of the sprite
    public int getWidth() {
        BufferedImage sprite = ImageUtils.convertToBufferedImage(getSprite());
        if (sprite != null) {
            return sprite.getWidth();
        } else {
            return 0; // or handle null sprite case accordingly
        }
    }

    // Define the getSprite() method to return the sprite
    public abstract Image getSprite();

    /**
     * Gets the bounding box of the movable entity.
     * 
     * @return The bounding box.
     */
    public abstract Rectangle getBoundingBox();

}
